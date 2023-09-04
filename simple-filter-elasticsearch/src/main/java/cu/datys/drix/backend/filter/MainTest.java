package cu.datys.drix.backend.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cu.datys.drix.backend.filter.simple.executor.ElasticSearchSimpleQueryExecutor;

import java.io.File;
import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException {
        ElasticSearchSimpleQueryExecutor queryExecutor = new ElasticSearchSimpleQueryExecutor();
        queryExecutor.writeJsonResponse(queryExecutor.executeQuery("amazon_test_meta", "[group.keyword] == 'Book';"), "test.json");
        JsonFormater("test.json", "output.json");
    }

    public static void JsonFormater(String inputFileJson, String outputFileJson) {
        try {
            File inputFile = new File(inputFileJson);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(inputFile);

            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();


            File outputFile = new File(outputFileJson);
            objectWriter.writeValue(outputFile, jsonNode);

            System.out.println("JSON formateado y guardado en output.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
