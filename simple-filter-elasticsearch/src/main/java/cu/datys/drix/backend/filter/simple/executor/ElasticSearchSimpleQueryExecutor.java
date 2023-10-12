package cu.datys.drix.backend.filter.simple.executor;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentHelper;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElasticSearchSimpleQueryExecutor extends ElasticSearchSimpleOperatorExecutor implements SimpleQueryExecutor<SearchResponse> {
    private final RestHighLevelClient client;
    private final String indexName;


    public ElasticSearchSimpleQueryExecutor(String indexName) {
        this.client = this.initElasticSearchServive();
        this.indexName = indexName;
    }

    public RestHighLevelClient initElasticSearchServive () {
        // Elastic Client Configuration
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("172.24.11.80", 9200, "http"))
        );
    }

    @Override
    public SearchResponse query(String queryString) {
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(this.parseQuery(queryString));
            sourceBuilder.timeout(TimeValue.timeValueSeconds(5));

            SearchRequest searchRequest = new SearchRequest(this.indexName);
            searchRequest.source(sourceBuilder);
            return this.client.search(searchRequest, RequestOptions.DEFAULT);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String queryToString(SearchResponse searchResponse){
        StringBuilder jsonResponse = new StringBuilder();
        try {
            Stream<String> jsonResults = Stream.of(searchResponse.getHits().getHits())
                    .map(SearchHit::getSourceAsString);

            jsonResponse.append("[").append(jsonResults.collect(Collectors.joining(","))).append("]");

            return jsonResponse.toString();
        }
        catch (Exception e){
            System.out.println("[!] Error: " + e.getMessage());
            jsonResponse.append("not result");
        }
        return jsonResponse.toString();
    }

    public String executeQuerySearch(String iindexName, String queryString) throws IOException {
        StringBuilder jsonResponse = new StringBuilder();
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(this.parseQuery(queryString));
            sourceBuilder.timeout(TimeValue.timeValueSeconds(5));

            SearchRequest searchRequest = new SearchRequest(iindexName);
            searchRequest.source(sourceBuilder);


            SearchResponse searchResponse = this.client.search(searchRequest, RequestOptions.DEFAULT);


            Stream<String> jsonResults = Stream.of(searchResponse.getHits().getHits())
                    .map(SearchHit::getSourceAsString);


            jsonResponse.append("[").append(jsonResults.collect(Collectors.joining(","))).append("]");

            return jsonResponse.toString();
        }
        catch (IOException e){
            System.out.println("[!] Error: " + e.getMessage());
            jsonResponse.append("not result");
        }
        return jsonResponse.toString();
    }

    public long executeQueryCount(String queryString) throws IOException {

        try {
            CountRequest countRequest = new CountRequest(this.indexName);
            countRequest.query(this.parseQuery(queryString));
            CountResponse countResponse = this.client.count(countRequest, RequestOptions.DEFAULT);
            return countResponse.getCount();
        }
        catch (IOException e){
            System.out.println("[!] Error: " + e.getMessage());
        }
        return 0;
    }

    public void writeJsonResponse(String jsonResponse, String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonResponse);
        }
        catch (IOException e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
    }

    public void killElasticSearchService() throws IOException {
        System.out.println("[+] Killing ElasticSearch Service...");
        this.client.close();
    }
}