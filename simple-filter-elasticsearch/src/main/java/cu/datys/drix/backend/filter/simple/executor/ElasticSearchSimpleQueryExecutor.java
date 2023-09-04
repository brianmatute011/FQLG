package cu.datys.drix.backend.filter.simple.executor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

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

    public SearchResponse querySearch(Query queryParam){
        SearchResponse searchResponse = null;
        return searchResponse;
    }
}