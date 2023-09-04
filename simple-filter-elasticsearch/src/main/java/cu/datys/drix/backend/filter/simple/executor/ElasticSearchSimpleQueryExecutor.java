package cu.datys.drix.backend.filter.simple.executor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.bytes.BytesReference;
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

import java.io.FileWriter;
import java.io.IOException;

public class ElasticSearchSimpleQueryExecutor extends ElasticSearchSimpleOperatorExecutor implements SimpleQueryExecutor<Query> {
    private final RestHighLevelClient client;

    public ElasticSearchSimpleQueryExecutor() {
        this.client = this.initElasticSearchServive();
    }

    public RestHighLevelClient initElasticSearchServive () {
        // Configura el cliente de Elasticsearch
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("172.16.152.80", 9200, "http"))
        );
    }

    @Override
    public Query query(String queryString) {
        NativeSearchQuery searchQuery =  new NativeSearchQueryBuilder().withQuery(this.parseQuery(queryString)).build();
        System.out.println("[+] Query Method (fields): ");
        for (String field: searchQuery.getFields()) {
            System.out.println(" " + field);
        }

//        searchQuery.setIndicesOptions(this.getIndicesOptions());
        return searchQuery;
    }

    public String executeQuery(String indexName, String queryString) throws IOException {
        StringBuilder jsonResponse = new StringBuilder();
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(this.parseQuery(queryString));
            sourceBuilder.timeout(TimeValue.timeValueSeconds(5));

            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(sourceBuilder);

            SearchResponse searchResponse = this.client.search(searchRequest, RequestOptions.DEFAULT);
            jsonResponse.append("[");

            for (SearchHit hit : searchResponse.getHits().getHits()) {
                jsonResponse.append(hit.getSourceAsString());
                jsonResponse.append(",");
            }

            // Elimina la última coma
            if (jsonResponse.length() > 1) {
                jsonResponse.deleteCharAt(jsonResponse.length() - 1);
            }

            jsonResponse.append("]");

            return jsonResponse.toString();
        }
        catch (IOException e){
            System.out.println("[!] Error: " + e.getMessage());
            jsonResponse.append("not result");
        }
        finally {
            this.client.close();
        }
        return jsonResponse.toString();
    }

    public void writeJsonResponse(String jsonResponse, String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonResponse);
        }
        catch (IOException e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
    }
}