package cu.datys.drix.backend.filter.simple.builder;

import cu.datys.drix.backend.filter.simple.executor.SimpleOperatorExecutor;
import cu.datys.drix.backend.filter.simple.executor.SimpleQueryExecutor;

public final class SimpleCriteriaBuilder<E, T extends SimpleOperatorExecutor<E> & SimpleQueryExecutor<E>> {
    T simpleFilter;
    String query;

    SimpleCriteriaBuilder(T simpleFilter) {
        this.simpleFilter = simpleFilter;
    }

    public SimpleCriteriaBuilder<E, T> fromQuery(String simpleCriteria) {
        this.query = this.sanatizeQuery(simpleCriteria);
        return this;
    }

    public SimpleCriteriaBuilder<E, T> andQuery(String simpleCriteria) {
        this.query += " AND ( " + this.sanatizeQuery(simpleCriteria) + " )";
        return this;
    }

    public SimpleCriteriaBuilder<E, T> orQuery(String simpleCriteria) {
        this.query += " OR ( " + this.sanatizeQuery(simpleCriteria) + " )";
        return this;
    }

    public E build() {
        return this.simpleFilter.query(this.query);
    }

    private String sanatizeQuery(String query) {
            return query.endsWith(";") ? query.substring(0, query.length() - 1) : query;
    }
}
