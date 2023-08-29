package cu.datys.drix.backend.filter.simple;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import cu.datys.drix.backend.filter.simple.exception.SimpleFilterException;
import cu.datys.drix.backend.filter.simple.model.criteria.BinarySimpleCriteria;
import cu.datys.drix.backend.filter.simple.model.criteria.UnaryFilter;
import cu.datys.drix.backend.filter.simple.model.operator.Operator;
import cu.datys.drix.backend.filter.simple.model.types.Condition;
import cu.datys.drix.backend.filter.simple.model.value.*;
import cu.datys.drix.backend.filter.simple.parser.SimpleFilterLexer;
import cu.datys.drix.backend.filter.simple.parser.SimpleFilterParser;
import cu.datys.drix.backend.filter.simple.parser.listener.SimpleFilterParserErrorListener;
import cu.datys.drix.backend.filter.simple.transpiler.SimpleFilterAST;
import static org.junit.Assert.*;
import org.antlr.v4.runtime.*;
import java.util.ArrayList;
import java.util.stream.*;

/**
 * Unit test for simple App.
 */
public class SimpleFilterTest
{
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // Valores simples
    @Test(expected = SimpleFilterException.class)
    public void testErrorQueryString() {
        String query = "^[a,b];";

        UnaryFilter expected = new UnaryFilter(Operator.IS, new StringValue("aaa"), Stream.of("a", "b").collect(Collectors.toList()), true);
        Object filter = parseQuery(query);
        checkUnaryFilter(expected, ((UnaryFilter)filter));
    }

    @Test
    public void testSimpleQueryString() {
        String query = "^[a,b] IS 'bb';";

        UnaryFilter expected = new UnaryFilter(Operator.IS, new StringValue("bb"), Stream.of("a", "b").collect(Collectors.toList()), true);
        Object filter = parseQuery(query);
        checkUnaryFilter(expected, ((UnaryFilter)filter));
    }

    @Test
    public void testSimpleQueryStringWithoutSymbol() {
        String query = "[a,b] IS 'bb';";

        UnaryFilter expected = new UnaryFilter(Operator.IS, new StringValue("bb"), Stream.of("a", "b").collect(Collectors.toList()), true);
        Object filter = parseQuery(query);
        checkUnaryFilter(expected, ((UnaryFilter)filter));
    }

    @Test
    public void testSimpleQueryNumber() {
        //Custom Query
        String query = "^[a,b] IS 123456;";


        //Translate to expected query
        UnaryFilter expected = new UnaryFilter(Operator.IS, new NumberValue(123456D), Stream.of("a", "b").collect(Collectors.toList()), true);
        Object filter = parseQuery(query);


        //Verified Query
        checkUnaryFilter(expected, ((UnaryFilter)filter));
    }

    @Test
    public void testSimpleQueryBoolean() {
        //Custom query
        String query = "^[a,b] IS false;";


        //Translate to expected query
        UnaryFilter expected = new UnaryFilter(Operator.IS, new BooleanValue(false),
                Stream.of("a", "b").collect(Collectors.toList()), true);

        Object filter = parseQuery(query);

        //Check expected query vs custom query
        checkUnaryFilter(expected, ((UnaryFilter)filter));
    }

    @Test
    public void testSimpleQueryDate() {
        String query = "^[a,b] IS 29/09/1994-08:00:00;";

        UnaryFilter expected = new UnaryFilter(Operator.IS, new DateValue("29/09/1994-08:00:00"),
                Stream.of("a", "b").collect(Collectors.toList()), true);
        Object filter = parseQuery(query);

        assertEquals(expected.getOperator(), ((UnaryFilter)filter).getOperator());
        assertEquals(expected.getFields(), ((UnaryFilter)filter).getFields());
        assertEquals(expected.getValue().getValue(), ((DateValue)((UnaryFilter)filter).getValue()).getValue());
        assertEquals((expected.getValue()).getType(), (((UnaryFilter)filter).getValue()).getType());
        assertNull(((DateValue)((UnaryFilter)filter).getValue()).getFrom());
        assertNull(((DateValue)((UnaryFilter)filter).getValue()).getTo());
        assertEquals(expected.isAll(), ((UnaryFilter)filter).isAll());
        assertNotNull(((DateValue)((UnaryFilter)filter).getValue()).getValue());
    }

    @Test
    public void testSimpleQueryTerm() {
        String query = "^* IS T'bb';";

        UnaryFilter expected = new UnaryFilter(Operator.IS, new TermValue("bb"), new ArrayList<>(), true);
        Object filter = parseQuery(query);
        checkUnaryFilter(expected, ((UnaryFilter)filter));
    }

    @Test
    public void testAllFieldsQuery() {
        String query = "^* IS 'bb';";

        UnaryFilter expected = new UnaryFilter(Operator.IS, new StringValue("bb"), new ArrayList<>(), true);
        Object filter = parseQuery(query);
        checkUnaryFilter(expected, ((UnaryFilter)filter));
    }

    // Lista de valores
    @Test
    public void testListOfStrings() {
        String query = "^* ALL ['bb', 222, 122121];";

        UnaryFilter expected = new UnaryFilter(Operator.ALL, new StringValue(Stream.of("bb", "222", "122121").
                collect(Collectors.toList())), new ArrayList<>(), true);
        Object filter = parseQuery(query);
        assertNotNull(((UnaryFilter)filter).getValue().getListValue());
        assertEquals(((UnaryFilter)filter).getValue().getListValue(), expected.getValue().getListValue());
    }

    @Test
    public void testListOfNumbers() {
        String query = "^* ALL [222, 122121];";

        UnaryFilter expected = new UnaryFilter(Operator.ALL, new NumberValue(Stream.of(222d, 122121d).
                collect(Collectors.toList())), new ArrayList<>(), true);
        Object filter = parseQuery(query);
        assertNotNull(((UnaryFilter)filter).getValue().getListValue());
        assertEquals(((UnaryFilter)filter).getValue().getListValue(), expected.getValue().getListValue());
    }

    @Test
    public void testListOfTerms() {
        String query = "^* ALL [T'aa', T'bb'];";

        UnaryFilter expected = new UnaryFilter(Operator.ALL, new TermValue(Stream.of("aa", "bb").
                collect(Collectors.toList())), new ArrayList<>(), true);
        Object filter = parseQuery(query);
        assertNotNull(((UnaryFilter)filter).getValue().getListValue());
        assertEquals(((UnaryFilter)filter).getValue().getListValue(), expected.getValue().getListValue());
    }

    // Rangos
    @Test
    public void testSimpleQueryNumberRange() {
        String query = "^[a,b] IS FROM 12 TO 14;";

        UnaryFilter expected = new UnaryFilter(Operator.IS, new NumberValue(12D, 14D), Stream.of("a", "b").collect(Collectors.toList()), true);
        Object filter = parseQuery(query);

        assertEquals(expected.getOperator(), ((UnaryFilter)filter).getOperator());
        assertEquals(expected.getFields(), ((UnaryFilter)filter).getFields());
        assertNull(((NumberValue)((UnaryFilter)filter).getValue()).getValue());
        assertEquals((expected.getValue()).getType(), (((UnaryFilter)filter).getValue()).getType());
        assertNotNull(((NumberValue)((UnaryFilter)filter).getValue()).getFrom());
        assertNotNull(((NumberValue)((UnaryFilter)filter).getValue()).getTo());
        assertEquals(((NumberValue)expected.getValue()).getFrom(), ((NumberValue)((UnaryFilter)filter).getValue()).getFrom());
        assertEquals(((NumberValue)expected.getValue()).getTo(), ((NumberValue)((UnaryFilter)filter).getValue()).getTo());
        assertEquals(expected.isAll(), ((UnaryFilter)filter).isAll());
    }

    @Test
    public void testSimpleQueryDateRange() {
        String query = "^[a,b] IS FROM 31/08/1995 TO 29/09/1995;";

        UnaryFilter expected = new UnaryFilter(Operator.IS, new DateValue("31/08/1995", "29/09/1995"), Stream.of("a", "b").collect(Collectors.toList()), true);
        Object filter = parseQuery(query);

        assertEquals(expected.getOperator(), ((UnaryFilter)filter).getOperator());
        assertEquals(expected.getFields(), ((UnaryFilter)filter).getFields());
        assertNull(((DateValue)((UnaryFilter)filter).getValue()).getValue());
        assertEquals((expected.getValue()).getType(), (((UnaryFilter)filter).getValue()).getType());
        assertNotNull(((DateValue)((UnaryFilter)filter).getValue()).getFrom());
        assertNotNull(((DateValue)((UnaryFilter)filter).getValue()).getTo());
        assertEquals(((DateValue)expected.getValue()).getFrom(), ((DateValue)((UnaryFilter)filter).getValue()).getFrom());
        assertEquals(((DateValue)expected.getValue()).getTo(), ((DateValue)((UnaryFilter)filter).getValue()).getTo());
        assertEquals(expected.isAll(), ((UnaryFilter)filter).isAll());
    }

    // Condiciones
    @Test
    public void testOneConditionQuery() {
        String query = "^[a,b] IS 'str' AND ^[c] IS 'str';";

        BinarySimpleCriteria expected = new BinarySimpleCriteria(Condition.AND, new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("a", "b").collect(Collectors.toList()), true), 
            new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("c").collect(Collectors.toList()), true)); 
        Object filter = parseQuery(query);

        assertEquals(expected.getCondition(), ((BinarySimpleCriteria)filter).getCondition());
        checkUnaryFilter((UnaryFilter) expected.getLeft(), (UnaryFilter) (((BinarySimpleCriteria)filter).getLeft()));
        checkUnaryFilter((UnaryFilter) expected.getRight(), (UnaryFilter)((BinarySimpleCriteria)filter).getRight());
    }

    @Test
    public void testManyConditionQuery() {
        String query = "^[a,b] IS 'str' AND ^[c] IS 'str' OR |[d] IS 123 AND |[e] IS 'str';";

        UnaryFilter val1 = new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("a", "b").collect(Collectors.toList()), true);
        UnaryFilter val2 =  new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("c").collect(Collectors.toList()), true);
        UnaryFilter val3 = new UnaryFilter(Operator.IS, new NumberValue(123D), Stream.of("d").collect(Collectors.toList()), false);
        UnaryFilter val4 =  new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("e").collect(Collectors.toList()), false);
        

        BinarySimpleCriteria expected = new BinarySimpleCriteria(Condition.AND,
                new BinarySimpleCriteria(Condition.OR,
                        new BinarySimpleCriteria(Condition.AND,
                                val1,
                                val2),
                        val3),
                val4);
        BinarySimpleCriteria filter = (BinarySimpleCriteria) parseQuery(query);

        UnaryFilter rightAnd2 = (UnaryFilter) expected.getRight();
        BinarySimpleCriteria leftAnd2 = (BinarySimpleCriteria) expected.getLeft();

        UnaryFilter rightOr = (UnaryFilter) leftAnd2.getRight();
        BinarySimpleCriteria leftOr = (BinarySimpleCriteria) leftAnd2.getLeft();

        UnaryFilter rightAnd1 = (UnaryFilter) leftOr.getRight();
        UnaryFilter leftAnd1 = (UnaryFilter) leftOr.getLeft();

        UnaryFilter rightAnd2Filter = (UnaryFilter) filter.getRight();
        BinarySimpleCriteria leftAnd2Filter = (BinarySimpleCriteria) filter.getLeft();

        UnaryFilter rightOrFilter = (UnaryFilter) leftAnd2Filter.getRight();
        BinarySimpleCriteria leftOrFilter = (BinarySimpleCriteria) leftAnd2Filter.getLeft();

        UnaryFilter rightAnd1Filter = (UnaryFilter) leftOrFilter.getRight();
        UnaryFilter leftAnd1Filter = (UnaryFilter) leftOrFilter.getLeft();

        assertEquals(leftAnd2.getCondition(), leftAnd2Filter.getCondition());
        assertEquals(leftOr.getCondition(), leftOrFilter.getCondition());
        assertEquals(expected.getCondition(), filter.getCondition());

        checkUnaryFilter(rightAnd2, rightAnd2Filter);
        checkUnaryFilter(rightOr, rightOrFilter);
        checkUnaryFilter(rightAnd1, rightAnd1Filter);
        checkUnaryFilter(leftAnd1, leftAnd1Filter);
    }

    @Test
    public void testLeftParenthesisQuery() {
        String query = "(^[a,b] IS 'str' AND ^[c] IS 'str') OR |[d] IS 123;";

        UnaryFilter val1 = new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("a", "b").collect(Collectors.toList()), true);
        UnaryFilter val2 =  new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("c").collect(Collectors.toList()), true);
        UnaryFilter val3 = new UnaryFilter(Operator.IS, new NumberValue(123D), Stream.of("d").collect(Collectors.toList()), false);

        BinarySimpleCriteria expectedLeft = new BinarySimpleCriteria(Condition.AND, val1, val2);

        BinarySimpleCriteria expected = new BinarySimpleCriteria(Condition.OR, expectedLeft, val3);

        BinarySimpleCriteria filter = (BinarySimpleCriteria) parseQuery(query);
        BinarySimpleCriteria filterLeft = (BinarySimpleCriteria) filter.getLeft();
        UnaryFilter filterRight = (UnaryFilter) filter.getRight();

        assertEquals(expected.getCondition(), filter.getCondition());
        assertEquals(expectedLeft.getCondition(), filterLeft.getCondition());
        checkUnaryFilter(val3, filterRight);
    }

    @Test
    public void testRightParenthesisQuery() {
        //Custom Query
        String query = "^[a,b] IS 'str' AND (^[c] IS 'str' OR |[d] IS 123);";

        UnaryFilter val1 = new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("a", "b").collect(Collectors.toList()), true);
        UnaryFilter val2 =  new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("c").collect(Collectors.toList()), true);
        UnaryFilter val3 = new UnaryFilter(Operator.IS, new NumberValue(123D), Stream.of("d").collect(Collectors.toList()), false);

        BinarySimpleCriteria expectedRight = new BinarySimpleCriteria(Condition.OR, val2, val3);

        BinarySimpleCriteria expected = new BinarySimpleCriteria(Condition.AND, val1, expectedRight);

        BinarySimpleCriteria filter = (BinarySimpleCriteria) parseQuery(query);
        BinarySimpleCriteria filterRight = (BinarySimpleCriteria) filter.getRight();
        UnaryFilter filterLeft = (UnaryFilter) filter.getLeft();

        assertEquals(expected.getCondition(), filter.getCondition());
        assertEquals(expectedRight.getCondition(), filterRight.getCondition());
        checkUnaryFilter(val1, filterLeft);
    }

    @Test
    public void testParenthesisQuery() {
        String query = "(^[a,b] IS 'str' AND ^[c] IS 'str') OR (|[d] IS 123 AND |[c] IS 'str');";

        UnaryFilter val1 = new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("a", "b").collect(Collectors.toList()), true);
        UnaryFilter val2 =  new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("c").collect(Collectors.toList()), true);
        UnaryFilter val3 = new UnaryFilter(Operator.IS, new NumberValue(123D), Stream.of("d").collect(Collectors.toList()), false);
        UnaryFilter val4 =  new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("c").collect(Collectors.toList()), false);

        BinarySimpleCriteria expectedLeft = new BinarySimpleCriteria(Condition.AND, val1, val2);
        BinarySimpleCriteria expectedRight = new BinarySimpleCriteria(Condition.AND, val3, val4);

        BinarySimpleCriteria expected = new BinarySimpleCriteria(Condition.OR, expectedLeft, expectedRight);

        BinarySimpleCriteria filter = (BinarySimpleCriteria) parseQuery(query);
        BinarySimpleCriteria filterLeft = (BinarySimpleCriteria) filter.getLeft();
        BinarySimpleCriteria filterRight = (BinarySimpleCriteria) filter.getRight();

        assertEquals(expected.getCondition(), filter.getCondition());
        assertEquals(expectedLeft.getCondition(), filterLeft.getCondition());
        assertEquals(expectedRight.getCondition(), filterRight.getCondition());
    }


    public void testNestedParenthesisQuery() {
        String query = "(^[a,b] IS 'str' AND (^[c] IS 'str')) OR 192168431  AND |[c] IS 'str');";

        UnaryFilter val1 = new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("a", "b").collect(Collectors.toList()), true);
        UnaryFilter val2 =  new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("c").collect(Collectors.toList()), true);
        UnaryFilter val3 = new UnaryFilter(Operator.IS, new NumberValue(123D), Stream.of("d").collect(Collectors.toList()), false);
        UnaryFilter val4 =  new UnaryFilter(Operator.IS, new StringValue("str"), Stream.of("c").collect(Collectors.toList()), false);

        BinarySimpleCriteria expectedLeft = new BinarySimpleCriteria(Condition.AND, val1, val2);
        BinarySimpleCriteria expectedRight = new BinarySimpleCriteria(Condition.AND, val3, val4);

        BinarySimpleCriteria expected = new BinarySimpleCriteria(Condition.OR, expectedLeft, expectedRight);

        BinarySimpleCriteria filter = (BinarySimpleCriteria)parseQuery(query);
        BinarySimpleCriteria filterLeft = (BinarySimpleCriteria) filter.getLeft();
        BinarySimpleCriteria filterRight = (BinarySimpleCriteria) filter.getRight();

        assertEquals(expected.getCondition(), filter.getCondition());
        assertEquals(expectedLeft.getCondition(), filterLeft.getCondition());
        assertEquals(expectedRight.getCondition(), filterRight.getCondition());
    }

    private void checkUnaryFilter(UnaryFilter expected, UnaryFilter result) {
        assertEquals(expected.getOperator(), result.getOperator());
        assertEquals(expected.getValue().getValue(), result.getValue().getValue());
        assertEquals(expected.getFields(), result.getFields());
        assertEquals(expected.isAll(), result.isAll());
    }

    private Object parseQuery(String query) {
        SimpleFilterLexer lexer = new SimpleFilterLexer(CharStreams.fromString(query));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new SimpleFilterParserErrorListener());
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleFilterParser parser = new SimpleFilterParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new SimpleFilterParserErrorListener());
        
        RuleContext tree = parser.simple_filter(); // Inicio de la regla
        SimpleFilterAST visitor = new  SimpleFilterAST();
        return visitor.visit(tree); // Se crea la representación de la query
    }
}