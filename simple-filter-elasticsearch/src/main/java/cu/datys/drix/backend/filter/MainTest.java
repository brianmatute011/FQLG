package cu.datys.drix.backend.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cu.datys.drix.backend.filter.simple.executor.ElasticSearchSimpleQueryExecutor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {
    public static void main(String[] args) throws IOException {
        ElasticSearchSimpleQueryExecutor queryExecutor = new ElasticSearchSimpleQueryExecutor();
        //Get Result Queries
        Map<String, String> queries = new HashMap<String, String>(){{
            put("query1", "[reviews.downloaded] == 13 AND ([group.keyword] == 'Book');");
            put("query2", "[reviews.downloaded] == 12;");
            put("query3", "[reviews.entries.cutomer.keyword] == 'ATVPDKIKX0DER' AND (^[reviews.entries.helpful, reviews.entries.votes] == 2);");
        }};

        for (Map.Entry<String, String> query: queries.entrySet()) {
            queryExecutor.writeJsonResponse(queryExecutor.executeQuerySearch(
                            "amazon_test_meta", query.getValue()),
                               query.getKey() + ".json"
            );
            JsonFormater(query.getKey() + ".json", query.getKey() + "_output.json");
        }



        //Get Total Hits
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("queries_count.log", true);

            for (Map.Entry<String, String> query : queries.entrySet()) {
                String result = query.getKey() + " : " + queryExecutor.executeQueryCount("amazon_test_meta", query.getValue()) + "\n";
                fileWriter.write(result);
                System.out.println("[?] Writing in file: " + result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close(); // Asegúrate de cerrar el archivo al finalizar
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        queryExecutor.killElasticSearchService();
    }

    public static void JsonFormater(String inputFileJson, String outputFileJson) {
        try {
            File inputFile = new File(inputFileJson);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(inputFile);

            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();


            File outputFile = new File(outputFileJson);
            objectWriter.writeValue(outputFile, jsonNode);

            System.out.println("[?] JSON formatted and saved in " + outputFileJson + " successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
