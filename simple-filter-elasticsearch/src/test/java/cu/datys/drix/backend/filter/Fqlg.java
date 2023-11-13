package cu.datys.drix.backend.filter;

import cu.datys.drix.backend.filter.simple.executor.ElasticSearchSimpleQueryExecutor;
import lombok.var;

import org.elasticsearch.cli.UserException;
import java.io.IOException;
import java.util.Scanner;

import static cu.datys.drix.backend.filter.simple.MainTest2.JsonFormater;


public class Fqlg {

    public static void main(String[] args) throws IOException, UserException {

        String indexName = "amazon_test_meta";
        ElasticSearchSimpleQueryExecutor queryExecutor = new ElasticSearchSimpleQueryExecutor(indexName);

        System.out.println("Welcome to FQLG (Filtering Query Language Generic)!");

        try (Scanner scanner = new Scanner(System.in)) {
            var idQuery = 0;
            while (true){
                System.out.print( "["+ (++idQuery) + "] >> ");
                String userInput = scanner.nextLine().trim();
                if ("exit()".equalsIgnoreCase(userInput)){
                    System.out.println("[?] Exit");
                    break;
                }
                processQuery(queryExecutor, userInput, idQuery);
            }
        }
        catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
    }

    private static void processQuery(ElasticSearchSimpleQueryExecutor queryExecutor, String userInput, int idQuery) throws IOException {
        var responseQuery = queryExecutor.query(userInput);
        var docRecovered = queryExecutor.executeQueryCount(userInput);
        String absolutePathDirectory = System.getProperty("user.dir") + "/target/generated-test-sources/fqlg/";
        try {
            System.out.println("[?] Query Status Response: " + responseQuery.status());
            System.out.println("[?] Recovered Documents: " + docRecovered);
            queryExecutor.writeJsonResponse(
                    queryExecutor.queryToString(responseQuery),
                    absolutePathDirectory + idQuery + ".json"
            );
            JsonFormater(absolutePathDirectory + idQuery + ".json",
                    absolutePathDirectory + idQuery + "_output.json"
            );



        }
        catch (Exception e){
            System.out.println("[!] Error: " + e.getMessage());
        }
    }
}
