package cu.datys.drix.backend.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cu.datys.drix.backend.filter.simple.executor.ElasticSearchSimpleQueryExecutor;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest{
    private final Map<String, String> queries = new HashMap<String, String>(){{
        put("test_query1", "[asin.keyword] == '0609804340';");
        put("test_query2", "[title.keyword] STARTS_WITH 'Book';");
        put("test_query3", "[reviews.downloaded] == 13 AND ([group.keyword] == 'Book');");
        put("test_query4", "[reviews.downloaded] == 12;");
        put("test_query5", "[reviews.entries.cutomer.keyword] == 'ATVPDKIKX0DER' AND (^[reviews.entries.helpful, reviews.entries.votes] == 2);");
    }};
    private final ElasticSearchSimpleQueryExecutor queryExecutor = new ElasticSearchSimpleQueryExecutor();


    @Before
    public void setUp() throws Exception {


        for (Map.Entry<String, String> query: queries.entrySet()) {
            queryExecutor.writeJsonResponse(queryExecutor.executeQuerySearch(
                            "amazon_test_meta", query.getValue()),
                    query.getKey() + ".json"
            );
            JsonFormater(query.getKey() + ".json", query.getKey() + "_output.json");
        }
    }

    @Test
    public void simpleQueryString() throws IOException {


    }














    private static void JsonFormater(String inputFileJson, String outputFileJson) {
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
