package cu.datys.drix.backend.filter.simple.builder;

import cu.datys.drix.backend.filter.simple.executor.SimpleOperatorExecutor;
import cu.datys.drix.backend.filter.simple.executor.SimpleQueryExecutor;
import cu.datys.drix.backend.filter.simple.model.types.Condition;
import cu.datys.drix.backend.filter.simple.model.value.Value;
import lombok.NonNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleCriteriaBuilderTest {
    private static class OperatorTest extends SimpleOperatorExecutor<String> {

        @Override
        public String condition(@NonNull Condition operator, @NonNull String value1, @NonNull String value2) {
            return null;
        }

        @Override
        public String equalTo(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String different(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String startsWith(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String doesNotStartsWith(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String endsWith(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String doesNotEndsWith(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String contains(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String doesNotContains(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String greaterThan(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String greaterThanEquals(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String lessThan(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String lessThanEquals(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String range(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String rangeOut(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String all(List<String> fields, @NonNull Value<?> values, boolean all) {
            return null;
        }

        @Override
        public String any(List<String> fields, @NonNull Value<?> values, boolean all) {
            return null;
        }

        @Override
        public String none(List<String> fields, @NonNull Value<?> values, boolean all) {
            return null;
        }

        @Override
        public String is(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }

        @Override
        public String isNot(List<String> fields, @NonNull Value<?> value, boolean all) {
            return null;
        }
    }

    private static class QueryTest extends OperatorTest implements SimpleQueryExecutor<String> {

        @Override
        public String query(@NonNull String criteria) {
            return criteria;
        }
    }

    private SimpleCriteriaBuilder<String, QueryTest> simpleCriteriaBuilder;

    @Before
    public void setUp() {
        QueryTest queryTest = new QueryTest();
        this.simpleCriteriaBuilder = new SimpleCriteriaBuilder<>(queryTest);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSimpleQueryString() {
        String query = "^[a,b] IS 'bb';";

        String queryString = simpleCriteriaBuilder.fromQuery(query).andQuery("^[a,b] IS 'bb';").orQuery("^[a,b] IS 'bb';").build();

        assertEquals("^[a,b] IS 'bb' AND ( ^[a,b] IS 'bb' ) OR ( ^[a,b] IS 'bb' )", queryString);
    }
}
