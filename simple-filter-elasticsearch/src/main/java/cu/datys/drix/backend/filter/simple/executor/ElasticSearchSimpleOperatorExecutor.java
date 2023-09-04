package cu.datys.drix.backend.filter.simple.executor;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cu.datys.drix.backend.filter.simple.model.criteria.UnaryFilter;
import cu.datys.drix.backend.filter.simple.model.value.TermValue;
import lombok.NonNull;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import cu.datys.drix.backend.filter.simple.exception.ElasticSearchSimpleFilterException;
import cu.datys.drix.backend.filter.simple.exception.SimpleFilterException;
import cu.datys.drix.backend.filter.simple.model.types.Condition;
import cu.datys.drix.backend.filter.simple.model.value.Value;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public class ElasticSearchSimpleOperatorExecutor extends SimpleOperatorExecutor<QueryBuilder>{

    @Override
    public QueryBuilder condition(Condition condition, QueryBuilder left, QueryBuilder right) {
        System.out.println("Current info... pass by 'condition' method");
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        
        if (condition.equals(Condition.AND)) {
            queryBuilder.must(left);
            queryBuilder.must(right);
            return queryBuilder;
        }

        queryBuilder.should(left);
        queryBuilder.should(right);
        return queryBuilder;
    }

    @Override
    public QueryBuilder contains(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'contains' method");
        if (fields.isEmpty()) {
            String query = Arrays.stream(((String) value.getValue()).split(" ")).map(s -> "*" + s + "*").collect(Collectors.joining(" "));
            return QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery(query));
        } else {
            return wildcardQuery("*" + value.toString() + "*", fields, all);
        }
    }

    @Override
    public QueryBuilder different(List<String> fields, Value<?> value, boolean all) {

        System.out.println("Current info... pass by 'different' method");
        return QueryBuilders.boolQuery().mustNot(equalTo(fields, value, all));
    }

    @Override
    public QueryBuilder doesNotContains(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'doesNotContains' method");
        return QueryBuilders.boolQuery().mustNot(contains(fields, value, all));
    }

    @Override
    public QueryBuilder doesNotEndsWith(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'doesNotEndWith' method");
        return QueryBuilders.boolQuery().mustNot(endsWith(fields, value, all));
    }

    @Override
    public QueryBuilder doesNotStartsWith(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'doesNotStartWith' method");


        return QueryBuilders.boolQuery().mustNot(startsWith(fields, value, all));
    }

    @Override
    public QueryBuilder endsWith(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'endsWith' method");
        if (fields.isEmpty()) {
            String query = Arrays.stream(((String) value.getValue()).split(" ")).map(s -> "*" + s).collect(Collectors.joining(" "));
            return QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery(query));
        }
        return wildcardQuery("*" + value.toString(), fields, all);
    }

    @Override
    public QueryBuilder equalTo(List<String> fields, Value<?> value, boolean all) {
        System.out.println("[?] Current info... pass by 'equalTo' method: ");
        System.out.println("[?] {value: " + value.getValue() + "}");

        //Print fields in format: [?] {fields: [field1, field2... field3]}
        //Use StringBuilder for concat fields and set format to message
        StringBuilder sb = new StringBuilder();
        sb.append("[?] {fields: [");
        for (String field: fields)  sb.append(field + " ");
        sb.append("]}");
        System.out.println(sb);

        System.out.println("[?] {all: " + all + "}");

        Object val = value.getValue();


        if (!fields.isEmpty()) {
            System.out.println("[+] pass by matchQuery");
            QueryBuilder queryBuilder = fields.stream().map(f -> (QueryBuilder) QueryBuilders.matchQuery(f, val)).
                    reduce(QueryBuilders.boolQuery(), (acc, element) -> all ? ((BoolQueryBuilder)acc).must(element): ((BoolQueryBuilder)acc).should(element));
            return queryBuilder;
        } else {
            System.out.println("[+] pass by multiMatchQuery");
            return all 
                ? QueryBuilders.multiMatchQuery(val).operator(Operator.AND) 
                : QueryBuilders.multiMatchQuery(val);
        }
    }

    @Override
    public QueryBuilder greaterThan(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'greaterThan' method");
        Object val = value.getValue();

        if (!fields.isEmpty()) {
            return fields.stream().map(f -> (QueryBuilder) QueryBuilders.rangeQuery(f).gt(val)).
            reduce(QueryBuilders.boolQuery(), (acc, element) -> all ? ((BoolQueryBuilder)acc).must(element): ((BoolQueryBuilder)acc).should(element));
        }
        else {
            throw new ElasticSearchSimpleFilterException("Error. Debe todos los campos para el operador GREATER_THAN.");
        }
    }

    @Override
    public QueryBuilder greaterThanEquals(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'greaterThanEquals' method");
        Object val = value.getValue();
        
        if (!fields.isEmpty()) {
            return fields.stream().map(f -> (QueryBuilder) QueryBuilders.rangeQuery(f).gte(val)).
            reduce(QueryBuilders.boolQuery(), (acc, element) -> all ? ((BoolQueryBuilder)acc).must(element): ((BoolQueryBuilder)acc).should(element));
        }
        else {
            throw new ElasticSearchSimpleFilterException("Error. Debe especificar todos los campos para el operador GREATER_THAN_EQUALS.");
        }
    }

    @Override
    public QueryBuilder is(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'is' method");
        if (!fields.isEmpty()) {
            return fields.stream().map(f -> (QueryBuilder) QueryBuilders.regexpQuery(f, value.toString())).
            reduce(QueryBuilders.boolQuery(), (acc, element) -> all ? ((BoolQueryBuilder)acc).must(element): ((BoolQueryBuilder)acc).should(element));
        }
        else {
            throw new ElasticSearchSimpleFilterException("Error. Debe todos los campos para el operador IS.");
        }
    }

    @Override
    public QueryBuilder isNot(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'isNot' method");
        if (fields.isEmpty()) {
            throw new ElasticSearchSimpleFilterException("Error. Debe especificar todos los campos a filtrar para el operador IS_NOT");
        }
        return QueryBuilders.boolQuery().mustNot(is(fields, value, all));
    }

    @Override
    public QueryBuilder lessThan(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'lessThan' method");
        Object val = value.getValue();

        if (!fields.isEmpty()) {
            return fields.stream().map(f -> (QueryBuilder) QueryBuilders.rangeQuery(f).lt(val)).
            reduce(QueryBuilders.boolQuery(), (acc, element) -> all ? ((BoolQueryBuilder)acc).must(element): ((BoolQueryBuilder)acc).should(element));
        }
        else {
            throw new ElasticSearchSimpleFilterException("Error. Debe todos los campos para el operador LESS_THAN.");
        }
    }

    @Override
    public QueryBuilder lessThanEquals(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'lessThaneEquals' method");
        Object val = value.getValue();

        if (!fields.isEmpty()) {
            return fields.stream().map(f -> (QueryBuilder) QueryBuilders.rangeQuery(f).lte(val)).
            reduce(QueryBuilders.boolQuery(), (acc, element) -> all ? ((BoolQueryBuilder)acc).must(element): ((BoolQueryBuilder)acc).should(element));
        }
        else {
            throw new ElasticSearchSimpleFilterException("Error. Debe todos los campos para el operador LESS_THAN_EQUALS.");
        }
    }

    @Override
    public QueryBuilder range(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'range' method");
        Object fromVal = value.getFrom();
        Object toVal = value.getTo();

        if (!fields.isEmpty()) {
            return fields.stream().map(f -> (QueryBuilder) QueryBuilders.rangeQuery(f).from(fromVal).includeLower(true).to(toVal).includeUpper(true)).
            reduce(QueryBuilders.boolQuery(), (acc, element) -> all ? ((BoolQueryBuilder)acc).must(element): ((BoolQueryBuilder)acc).should(element));
        }
        else {
            throw new ElasticSearchSimpleFilterException("Error. Debe todos los campos a consultar para el operador RANGE.");
        }
    }

    @Override
    public QueryBuilder rangeOut(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'rangeOut' method");

        if (fields.isEmpty()) {
            throw new ElasticSearchSimpleFilterException("Error. Debe especificar todos los campos a filtrar para el operador RANGE_OUT");
        }
        return QueryBuilders.boolQuery().mustNot(range(fields, value, all));
    }

    @Override
    public QueryBuilder all(List<String> fields, @NonNull Value<?> values, boolean all) {
        System.out.println("Current info... pass by 'all' method");
        BoolQueryBuilder queryBuilderFields = QueryBuilders.boolQuery();
        BoolQueryBuilder queryBuilderValues = QueryBuilders.boolQuery();
        List<?> listValues = values.getListValue();

        if (!listValues.isEmpty()) {
            if (all) {
                for (String field : fields) {
                    for (Object value : listValues) {
                        if (value instanceof TermValue) {
                            queryBuilderValues = queryBuilderValues
                                    .must(QueryBuilders.termQuery(field, value.toString()));
                        } else {
                            queryBuilderValues = queryBuilderValues
                                    .must(QueryBuilders.matchQuery(field, value.toString()));
                        }
                    }

                    queryBuilderFields = queryBuilderFields.must(queryBuilderValues);
                }

                return queryBuilderFields;
            } else {
                for (Object value : listValues)
                    if (value instanceof TermValue) {
                        queryBuilderValues = queryBuilderValues.must(QueryBuilders.termQuery("*", value.toString()));
                    } else {
                        queryBuilderValues = queryBuilderValues.should(QueryBuilders.multiMatchQuery(value.toString()));
                    }

                return queryBuilderValues;
            }
        } else {
            throw new SimpleFilterException("Error! Debe especificar todos los campos para el operador ALL");
        }
    }

    @Override
    public QueryBuilder any(List<String> fields, @NonNull Value<?> values, boolean all) {
        System.out.println("Current info... pass by 'any' method");
        BoolQueryBuilder queryBuilderFields = QueryBuilders.boolQuery();
        BoolQueryBuilder queryBuilderValues = QueryBuilders.boolQuery();
        List<?> listValues = values.getListValue();

        if (!listValues.isEmpty()) {
            if (all) {
                for (String field : fields) {
                    for (Object value : listValues)
                        queryBuilderValues = queryBuilderValues
                                .should(QueryBuilders.matchQuery(field, value.toString()));

                    queryBuilderFields = queryBuilderFields.must(queryBuilderValues);
                }

                return queryBuilderFields;
            } else {
                for (String field : fields) {
                    for (Object value : listValues)
                        queryBuilderValues = queryBuilderValues
                                .should(QueryBuilders.matchQuery(field, value.toString()));

                    queryBuilderFields = queryBuilderFields.should(queryBuilderValues);
                }

                return queryBuilderFields;
            }
        } else {
            throw new SimpleFilterException("Error! Debe especificar todos los campos para el operador ANY");
        }
    }

    @Override
    public QueryBuilder none(List<String> fields, @NonNull Value<?> values, boolean all) {
        System.out.println("Current info... pass by 'none' method");
        if (!fields.isEmpty()) {
            return QueryBuilders.boolQuery().mustNot(any(fields, values, all));
        } else {
            throw new SimpleFilterException("Error! Debe especificar todos los campos para el operador NONE");
        }
        
    }

    @Override
    public QueryBuilder startsWith(List<String> fields, Value<?> value, boolean all) {
        System.out.println("Current info... pass by 'startWith' method");
        if (fields.isEmpty()) {
            String query = Arrays.stream(((String) value.getValue()).split(" ")).map(s -> "*" + s + "*").collect(Collectors.joining(" "));
            return QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery(query));
        } else {
            return wildcardQuery(value.toString() + "*", fields, all);
        }
    }
    
    protected QueryBuilder wildcardQuery(String query, List<String> fields, boolean all) {
        System.out.println("Current info... pass by 'wildcardQuery' method");
        if (all) {
            return fields.stream().map(f -> 
                (QueryBuilder) QueryBuilders.wildcardQuery(f, query)).reduce(QueryBuilders.boolQuery(), (acc, element) -> ((BoolQueryBuilder)acc).must(element));
        } else {
            return fields.stream().map(f ->
                (QueryBuilder) QueryBuilders.wildcardQuery(f, query)).reduce(QueryBuilders.boolQuery(), (acc, element) -> ((BoolQueryBuilder)acc).should(element));
        }
    }
}