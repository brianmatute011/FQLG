package cu.datys.drix.backend.filter.simple;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cu.datys.drix.backend.filter.simple.executor.ElasticSearchSimpleQueryExecutor;
import lombok.var;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest2 {
    public static void main(String[] args) throws IOException {
        ElasticSearchSimpleQueryExecutor queryExecutor = new ElasticSearchSimpleQueryExecutor("amazon_test_meta");
        String abolutePathTestQuery = System.getProperty("user.dir") + "/target/generated-sources/test-query-dataset/dataset-validate-query";
        Map<String, Map<String, SearchResponse>> queries = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(abolutePathTestQuery))) {
            String line;
            while ((line = br.readLine()) != null){
                String finalLine = line;
                queries.put("query" + (queries.size() + 1), new HashMap<String, SearchResponse>() {{
                    put(finalLine.trim(), queryExecutor.query(finalLine.trim()));
                }});

            }
                //queries.put("query" + (queries.size() + 1), queryExecutor.query(line.trim()));

        }
        String outputFilePath = System.getProperty("user.dir") + "/target/generated-sources/test-query-dataset/query-count-output.txt";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))){
            for (var hashQueryResponse: queries.entrySet())
                for (var queryResponse: hashQueryResponse.getValue().entrySet()){
                    var output = hashQueryResponse.getKey() + ": " + queryExecutor.executeQueryCount(queryResponse.getKey());
                    writer.write(output);
                    writer.newLine();
                    System.out.println(output);
                }

        }





        queryExecutor.killElasticSearchService();


    }

//        ElasticSearchSimpleQueryExecutor queryExecutor = new ElasticSearchSimpleQueryExecutor("amazon_test_meta");
//        //Get Result Queries
//        Map<String, String> queries = new HashMap<String, String>(){{
//            put("query1", "[reviews.downloaded] == 13 AND ([group.keyword] == 'Book');");
//            put("query2", "[reviews.downloaded] == 12;");
//            put("query3", "[reviews.entries.cutomer.keyword] == 'ATVPDKIKX0DER' AND (^[reviews.entries.helpful, reviews.entries.votes] == 2);");
//            put("query4", "[reviews.entries.date] RANGOUT FROM 04/04/2004 TO 05/08/2005;");
//        }};
//
//        for (Map.Entry<String, String> query: queries.entrySet()) {
//            SearchResponse searchResponse = queryExecutor.query(query.getValue());
//            queryExecutor.writeJsonResponse(
//                    queryExecutor.queryToString(searchResponse), query.getKey() + ".json"
//            );
//
//            JsonFormater(query.getKey() + ".json", query.getKey() + "_output.json");
//        }
//
//        queryExecutor.killElasticSearchService();
//    }

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
