package cu.datys.drix.backend.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cu.datys.drix.backend.filter.simple.executor.ElasticSearchSimpleQueryExecutor;
import lombok.var;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Unit test for simple App.
 */
public class AppTest{
    String stringQuery1 = "[asin.keyword] == '0609804340'";
    String stringQuery2 = "[title.keyword] STARTS_WITH 'Book';";
    String stringQuery3 = "[reviews.downloaded] == 13 AND [group.keyword] == 'Book';";
    String stringQuery4 = "[reviews.downloaded] == 12;";
    String stringQuery5 = "[reviews.entries.cutomer.keyword] == 'ATVPDKIKX0DER' " +
                          "AND (|[reviews.entries.helpful, reviews.entries.votes] == 2);";
    String stringQuery6 = "[reviews.downloaded, reviews.total] ANY ['5', '4', '1']";
    String stringQuery7 = "[reviews.entries.date] RANGOUT FROM 04/04/2004 TO 05/08/2005;";
    String stringQuery8 = "[title.keyword] IS T'Love in the Time of Science';";
    String stringQuery9 = "([reviews.entries.date] RANGOUT FROM 04/04/2004 TO 05/08/2005 " +
                          "AND [title.keyword] ENDS_WITH 'say')" +
                          "OR  [title.keyword] CONTAINS 'something';";

    private final ElasticSearchSimpleQueryExecutor queryExecutor = new ElasticSearchSimpleQueryExecutor("amazon_test_meta");

    private final Map<String, Map<String, SearchResponse>> queriesMap = new HashMap<String, Map<String, SearchResponse>>(){{
        put("test_query1", new HashMap<String, SearchResponse>() {{
            put(stringQuery1, queryExecutor.query(stringQuery1));
        }});
        put("test_query2", new HashMap<String, SearchResponse>() {{
            put(stringQuery2, queryExecutor.query(stringQuery2));
        }});
        put("test_query3", new HashMap<String, SearchResponse>() {{
            put(stringQuery3, queryExecutor.query(stringQuery3));
        }});
        put("test_query4", new HashMap<String, SearchResponse>() {{
            put(stringQuery4, queryExecutor.query(stringQuery4));
        }});
        put("test_query5", new HashMap<String, SearchResponse>() {{
            put(stringQuery5, queryExecutor.query(stringQuery5));
        }});
        put("test_query6", new HashMap<String, SearchResponse>() {{
            put(stringQuery6, queryExecutor.query(stringQuery6));
        }});
        put("test_query7", new HashMap<String, SearchResponse>() {{
            put(stringQuery7, queryExecutor.query(stringQuery7));
        }});
        put("test_query8", new HashMap<String, SearchResponse>() {{
            put(stringQuery8, queryExecutor.query(stringQuery8));
        }});
        put("test_query9", new HashMap<String, SearchResponse>() {{
            put(stringQuery9, queryExecutor.query(stringQuery9));
        }});

    }};


    @Before
    public void setUp() throws Exception {
        String absolutePathDirectory = System.getProperty("user.dir") + "/target/generated-test-sources/";

        for (var queryMap : queriesMap.entrySet()) {
            for (var query : queryMap.getValue().entrySet()) {
                queryExecutor.writeJsonResponse(
                        queryExecutor.queryToString(query.getValue()),
                        absolutePathDirectory + queryMap.getKey() + ".json"
                );
                JsonFormater(absolutePathDirectory + queryMap.getKey() + ".json",
                        absolutePathDirectory + queryMap.getKey() + "_output.json"
                );
            }
        }
    }

    @Test
    public void simpleQueryString() throws IOException {
        var responseQueryMap = queriesMap.get("test_query1").get(stringQuery1);
        assertEquals(responseQueryMap.status(), RestStatus.OK);
        assertNotNull(responseQueryMap.getHits());
        assertEquals(1, responseQueryMap.getHits().getTotalHits().value);
    }

    @Test
    public void simpleQueryStartsWith() throws IOException {
        var responseQueryMap = queriesMap.get("test_query2").get(stringQuery2);
        assertEquals(responseQueryMap.status(), RestStatus.OK);
        assertNotNull(responseQueryMap.getHits());
        assertEquals(297, responseQueryMap.getHits().getTotalHits().value);
    }

    @Test
    public void simpleQueryAnd() throws IOException {
        var responseQueryMap = queriesMap.get("test_query3").get(stringQuery3);
        assertEquals(responseQueryMap.status(), RestStatus.OK);
        assertNotNull(responseQueryMap.getHits());
        assertEquals(3574, responseQueryMap.getHits().getTotalHits().value);
    }

    @Test
    public void simpleQueryInt() throws IOException {
        var responseQueryMap = queriesMap.get("test_query4").get(stringQuery4);
        assertEquals(responseQueryMap.status(), RestStatus.OK);
        assertNotNull(responseQueryMap.getHits());
        assertEquals(6317, responseQueryMap.getHits().getTotalHits().value);
    }

    @Test
    public void simpleQueryAndParenthesisOptionalFields() throws IOException {
        var responseQueryMap = queriesMap.get("test_query5").get(stringQuery5);
        assertEquals(responseQueryMap.status(), RestStatus.OK);
        assertNotNull(responseQueryMap.getHits());
        assertEquals(91651, queryExecutor.executeQueryCount(stringQuery5));
    }
    @Test
    public void simpleQueryAny() throws IOException {
        var responseQueryMap = queriesMap.get("test_query6").get(stringQuery6);
        assertEquals(responseQueryMap.status(), RestStatus.OK);
        assertNotNull(responseQueryMap.getHits());
        assertEquals(137611, queryExecutor.executeQueryCount(stringQuery6));
    }

    @Test
    public void simpleQueryRange() throws IOException {
        var responseQueryMap = queriesMap.get("test_query7").get(stringQuery7);
        assertEquals(responseQueryMap.status(), RestStatus.OK);
        assertNotNull(responseQueryMap.getHits());
        assertEquals(363542, queryExecutor.executeQueryCount(stringQuery7));
    }

    @Test
    public void simpleQueryIs() throws IOException {
        var responseQueryMap = queriesMap.get("test_query8").get(stringQuery8);
        assertEquals(responseQueryMap.status(), RestStatus.OK);
        assertNotNull(responseQueryMap.getHits());
        assertEquals(2, queryExecutor.executeQueryCount(stringQuery8));
    }

    @Test
    public void simpleQueryMix() throws IOException {
        var responseQueryMap = queriesMap.get("test_query9").get(stringQuery9);
        assertEquals(responseQueryMap.status(), RestStatus.OK);
        assertNotNull(responseQueryMap.getHits());
        assertEquals(35, queryExecutor.executeQueryCount(stringQuery9));

    }

    @Test
    public void simpleBulkQueryVariation() throws IOException{
        String absolutePathTestQuery = System.getProperty("user.dir") + "/target/generated-sources/test-query-dataset";
        Map<String, Map<String, SearchResponse>> queries = getQueriesMapFromFile(absolutePathTestQuery);

        try (BufferedReader logsSupportPreprocessResultsElastic = new BufferedReader(
                new FileReader(absolutePathTestQuery + "/support-vector-expected"))){
            String lineLogs;
            while ((lineLogs = logsSupportPreprocessResultsElastic.readLine()) != null){
                String[] lineSplit = lineLogs.split(" ");
                var linkQueryDSlCount = queries.get("query" + lineSplit[0].trim());
                var queryExpectedApiCounterDocs = Integer.parseInt(lineSplit[2]);
                AtomicLong queryDSLCounterDocs = new AtomicLong(-1);
                linkQueryDSlCount.forEach((subQueryDSL, value) -> {
                    try {
                        queryDSLCounterDocs.set(queryExecutor.executeQueryCount(subQueryDSL));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                assertEquals(queryExpectedApiCounterDocs, queryDSLCounterDocs.get());
            }
        }
    }



    private Map<String, Map<String, SearchResponse>> getQueriesMapFromFile(String abolutePathTestFileQuery) throws IOException{
        Map<String, Map<String, SearchResponse>> queries = new HashMap<>();

        try (BufferedReader datasetBulkQueries = new BufferedReader(new FileReader(abolutePathTestFileQuery + "/dataset-validate-query"))) {
            String line;
            while ((line = datasetBulkQueries.readLine()) != null){
                String finalLine = line;
                queries.put("query" + (queries.size() + 1), new HashMap<String, SearchResponse>() {{
                    put(finalLine.trim(), queryExecutor.query(finalLine.trim()));
                }});
            }
        }

        return queries;
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
