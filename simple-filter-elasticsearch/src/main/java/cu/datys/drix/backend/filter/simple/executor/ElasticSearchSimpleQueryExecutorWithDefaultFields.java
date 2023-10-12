package cu.datys.drix.backend.filter.simple.executor;

import cu.datys.drix.backend.filter.simple.model.criteria.UnaryFilter;
import java.util.List;

public class ElasticSearchSimpleQueryExecutorWithDefaultFields extends ElasticSearchSimpleQueryExecutor{
    private List<String> defaultFields;

    public ElasticSearchSimpleQueryExecutorWithDefaultFields(List<String> defaultFields) {
        super("amazon_test_meta");
        this.defaultFields = defaultFields;
    }

    @Override
    protected List<String> getFields(UnaryFilter filter) {
        return filter.getFields().isEmpty() ? this.defaultFields : filter.getFields();
    }
}
