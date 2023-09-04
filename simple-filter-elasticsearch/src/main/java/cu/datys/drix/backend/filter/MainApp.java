package cu.datys.drix.backend.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.datys.drix.backend.filter.simple.executor.ElasticSearchSimpleQueryExecutor;
import cu.datys.drix.backend.filter.simple.model.criteria.UnaryFilter;
import cu.datys.drix.backend.filter.simple.utils.UtilsSFilter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
//import org.elasticsearch.core.TimeValue;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.io.File;
import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        // Configura el cliente de Elasticsearch
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("172.16.152.80", 9200, "http"))
        );









        ElasticSearchSimpleQueryExecutor queryExecutor = new ElasticSearchSimpleQueryExecutor();

        String indexName = "amazon_test_meta";

        String queryString = "[group.keyword] == 'Book';";
//        System.out.println(
//                ((UnaryFilter)UtilsSFilter.parseQuery(queryString)).toString()
//        );
        Query query = queryExecutor.query(queryString);


        ObjectMapper objectMapper = new ObjectMapper();

        String queryJson = objectMapper.writeValueAsString(query);
        //System.out.println(query.getIndicesOptions());
        //query.addFields("group.keywords");
        objectMapper.writeValue(new File("test.json"), query);



//                System.out.println(queryJson);
//        String a = "a";
//        System.out.println(new StringBuilder("aaskljd"));





//        ElasticsearchRestTemplate elasticsearchRestTemplate = new ElasticsearchRestTemplate(client);
//
//        SearchHits<Object> searchHits = elasticsearchRestTemplate.search(query, Object.class);
//
//        try {
//            client.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }





    }
}