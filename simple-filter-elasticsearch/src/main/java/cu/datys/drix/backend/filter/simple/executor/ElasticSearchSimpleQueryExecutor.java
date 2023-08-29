package cu.datys.drix.backend.filter.simple.executor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

public class ElasticSearchSimpleQueryExecutor extends ElasticSearchSimpleOperatorExecutor implements SimpleQueryExecutor<Query> {

    @Override
    public Query query(String queryString) {
        return new NativeSearchQueryBuilder().withQuery(this.parseQuery(queryString)).build();
    }
}