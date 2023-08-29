package cu.datys.drix.backend.filter.simple.executor;

import java.util.List;

import cu.datys.drix.backend.filter.simple.exception.SimpleFilterException;
import cu.datys.drix.backend.filter.simple.model.value.*;
import org.antlr.v4.runtime.*;
import cu.datys.drix.backend.filter.simple.model.criteria.SimpleCriteria;
import cu.datys.drix.backend.filter.simple.model.criteria.BinarySimpleCriteria;
import cu.datys.drix.backend.filter.simple.model.criteria.UnaryFilter;
import cu.datys.drix.backend.filter.simple.model.types.Condition;
import cu.datys.drix.backend.filter.simple.parser.SimpleFilterLexer;
import cu.datys.drix.backend.filter.simple.parser.SimpleFilterParser;
import cu.datys.drix.backend.filter.simple.parser.listener.SimpleFilterParserErrorListener;
import cu.datys.drix.backend.filter.simple.transpiler.SimpleFilterAST;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Esta clase base define el conjunto de operadores soportados por el filtro genérico. Cada clase hija debe
 * proveer una implementación de cada operador específico.
 *
 * @param <T> El tipo de retorno de cada operador.
 */
@NoArgsConstructor
public abstract class SimpleOperatorExecutor<T> {
    private static final String VALUE_ERROR = "Error!! Valor no soportado para el operador ";

    /**
     * Método para formar la condición (and,or) entre dos consultas
     * 
     * @param operator
     *            Condición (and,or) a utilizar
     * @param value1
     *            Primera consulta
     * @param value2
     *            Segunda consulta
     * @return <code>[T]</code> Consulta para la condición (and,or)
     */
    public abstract T condition(@NonNull Condition operator, @NonNull T value1, @NonNull T value2);

    // +++++++++++++++++++++++++++++++
    //
    // Operadores genericos
    //
    // +++++++++++++++++++++++++++++++

    /**
     * Método para formar la consulta para el operador "igual"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * 
     * @param all
     *            Especifica si se debe cumpplir en todas las propiedades o en algunas.  
     * 
     * @return <code>[T]</code> Consulta para el operador "igual"
     */
    public abstract T equalTo(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "diferente"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "diferente"
     */
    public abstract T different(List<String> fields, @NonNull Value<?> value, boolean all);

    // +++++++++++++++++++++++++
    // 
    //  Operadores para cadenas
    //
    // +++++++++++++++++++++++++

    /**
     * Método para formar la consulta para el operador "comienza con"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "comienza con"
     */
    public abstract T startsWith(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "no comienza con"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "no comienza con"
     */
    public abstract T doesNotStartsWith(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "termina con"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "termina con"
     */
    public abstract T endsWith(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "no termina con"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "no termina con"
     */
    public abstract T doesNotEndsWith(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "contiene"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "contiene"
     */
    public abstract T contains(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "no contiene"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "no contiene"
     */
    public abstract T doesNotContains(List<String> fields, @NonNull Value<?> value, boolean all);

    // +++++++++++++++++++++++++
    // 
    //  Operadores para numeros
    //
    // +++++++++++++++++++++++++

    /**
     * Método para formar la consulta para el operador "mayor que"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "mayor que"
     */
    public abstract T greaterThan(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "mayor igual que"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "mayor igual que"
     */
    public abstract T greaterThanEquals(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "menor igual que"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "menor igual que"
     */
    public abstract T lessThan(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "menor igual que"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valor para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "menor igual que"
     */
    public abstract T lessThanEquals(List<String> fields, @NonNull Value<?> value, boolean all);

    
    /**
     * Método para formar la consulta para el operador "en el rango"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Tipo de dato con los valores de inicio y fin
     * @return <code>[T]</code> Consulta para el operador "en el rango"
     */
    public abstract T range(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "fuera del rango"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Tipo de dato con los valores de inicio y fin
     * @return <code>[T]</code> Consulta para el operador "fuera del rango"
     */
    public abstract T rangeOut(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "con todos los valores"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param values
     *            Valores para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "con todos los
     *         valores"
     */
    public abstract T all(List<String> fields, @NonNull Value<?> values, boolean all);

    /**
     * Método para formar la consulta para el operador "con alguno de los valores"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param values
     *            Valores para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "con alguno de los valores"
     */
    public abstract T any(List<String> fields, @NonNull Value<?> values, boolean all);

    /**
     * Método para formar la consulta para el operador "con ninguno de los valores"
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param values
     *            Valores para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "con ninguno de los valores"
     */
    public abstract T none(List<String> fields, @NonNull Value<?> values, boolean all);

    /**
     * Método para formar la consulta para el operador "es igual" para cadenas
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valores para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "es igual" para cadenas
     */
    public abstract T is(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Método para formar la consulta para el operador "no es igual" para cadenas
     * 
     * @param fields
     *            Propiedades del campo a consultar
     * @param value
     *            Valores para el campo a consultar
     * @return <code>[T]</code> Consulta para el operador "no es igual" para cadenas
     */
    public abstract T isNot(List<String> fields, @NonNull Value<?> value, boolean all);

    /**
     * Convierte la consulta genérica a una específica para cada base de datos.
     * @param query La consulta genérica.
     * @return <code>[T]</code> Consulta específica de la base de datos.
     */
    protected final T parseQuery(String query) {
        StringBuilder queryString = new StringBuilder(query);

        if (!query.endsWith(";")) {
            queryString.append(";");
        }

        SimpleFilterLexer lexer = new SimpleFilterLexer(CharStreams.fromString(queryString.toString()));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new SimpleFilterParserErrorListener());
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleFilterParser parser = new SimpleFilterParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new SimpleFilterParserErrorListener());
        
        RuleContext tree = parser.simple_filter(); // Inicio de la regla
        SimpleFilterAST visitor = new  SimpleFilterAST();
        SimpleCriteria filter = (SimpleCriteria) visitor.visit(tree); // Se crea la representación de la query

        return obtainFilter(filter);
    }

    protected List<String> getFields(UnaryFilter filter) {
        return filter.getFields();
    }
    
    /* Obtiene la consulta a partir del filtro genérico. Chequea si es una consulta simple o si tiene condicionales*/
    private T obtainFilter(SimpleCriteria criteria) {
        if (criteria instanceof UnaryFilter) {
            return translate(criteria);
        }
        else {  
            BinarySimpleCriteria simpleFilter = (BinarySimpleCriteria) criteria;
            return condition(simpleFilter.getCondition(), obtainFilter(simpleFilter.getLeft()), obtainFilter(simpleFilter.getRight()
            ));
        } 
    }

    /* Obtiene la consulta específica según el operador correspondiente. */
    private T translate(@NonNull SimpleCriteria filter) {
        UnaryFilter unaryFilter = (UnaryFilter) filter;
        if (unaryFilter.getValue().getValue() != null) {
            return simpleValue(unaryFilter);
        } else if (unaryFilter.getValue().getListValue() != null) {
            return listOperators(unaryFilter);
        } else {
            return rangeOperators(unaryFilter);
        }
    }

    // Operadores para valores simples
    private T simpleValue(@NonNull UnaryFilter filter) {
        Value<?> value = filter.getValue();

        if (value instanceof TermValue || value instanceof StringValue) {
            return stringOperators(filter);
        } else if (value instanceof NumberValue || value instanceof DateValue) {
            return numbersOperators(filter);
        } else {
            return boolOperators(filter);
        }
    }

    // Operadores para los tipos de datos tèrmino y string. La igualdad/diferencia se trata distinto.
    private T stringOperators(@NonNull UnaryFilter filter) {
        Value<?> value = filter.getValue();

        // Igualdad/diferencia
        if (value instanceof TermValue) {
            switch (filter.getOperator()) {
                case IS:
                    return is(filter.getFields(), filter.getValue(), filter.isAll());
                case IS_NOT:
                    return isNot(filter.getFields(), filter.getValue(), filter.isAll());
            }
        } else if (value instanceof StringValue) {
            switch (filter.getOperator().getOperator()) {
                case "==":
                    return equalTo(filter.getFields(), filter.getValue(), filter.isAll());
                case "!=":
                    return different(filter.getFields(), filter.getValue(), filter.isAll());
            }
        }

        return commonStringsOperators(filter);
    }

    // Operadores comunes para los tipos término y string
    private T commonStringsOperators (@NonNull UnaryFilter filter) {
        switch (filter.getOperator()) {
            case STARTS_WITH:
                return startsWith(filter.getFields(), filter.getValue(), filter.isAll());
            case DOES_NOT_STARTS_WITH:
                return doesNotStartsWith(filter.getFields(), filter.getValue(), filter.isAll());
            case ENDS_WITH:
                return endsWith(filter.getFields(), filter.getValue(), filter.isAll());
            case DOES_NOT_END_WITH:
                return doesNotEndsWith(filter.getFields(), filter.getValue(), filter.isAll());
            case CONTAINS:
                return contains(getFields(filter), filter.getValue(), filter.isAll());
            case DOES_NOT_CONTAINS:
                return doesNotContains(filter.getFields(), filter.getValue(), filter.isAll());
            default:
                throw new SimpleFilterException("Valor no soportado para el operador " +
                        filter.getOperator().getOperator());
        }
    }

    // Operadores para los tipos de datos número y fecha.
    private T numbersOperators(@NonNull UnaryFilter filter) {
        Value<?> value = filter.getValue();

        switch (filter.getOperator().getOperator()) {
            case "==":
                return value instanceof DateValue ?
                        range(filter.getFields(), new DateValue(((DateValue) value).getDateStart(), ((DateValue) value).getDateEnd()),
                                filter.isAll()) :
                        equalTo(filter.getFields(), value, filter.isAll());
            case "!=":
                return value instanceof DateValue ?
                        rangeOut(filter.getFields(), new DateValue(((DateValue) value).getDateStart(), ((DateValue) value).getDateEnd()),
                                filter.isAll()) :
                        different(filter.getFields(), value, filter.isAll());
            case ">":
                return value instanceof DateValue ?
                        greaterThan(filter.getFields(),  new NumberValue(((DateValue) value).getDateEnd().doubleValue()), filter.isAll()) :
                        greaterThan(filter.getFields(), value, filter.isAll());
            case ">=":
                return value instanceof DateValue ?
                        greaterThanEquals(filter.getFields(),  new NumberValue(((DateValue) value).getDateStart().doubleValue()), filter.isAll()) :
                        greaterThanEquals(filter.getFields(), value, filter.isAll());
            case "<":
                return value instanceof DateValue ?
                        lessThan(filter.getFields(),  new NumberValue(((DateValue) value).getDateStart().doubleValue()), filter.isAll()) :
                        lessThan(filter.getFields(), value, filter.isAll());
            case "<=":
                return value instanceof DateValue ?
                        lessThanEquals(filter.getFields(),  new NumberValue(((DateValue) value).getDateEnd().doubleValue()), filter.isAll()) :
                        lessThanEquals(filter.getFields(), value, filter.isAll());
            default:
                throw new SimpleFilterException(VALUE_ERROR +
                        filter.getOperator().getOperator());
        }
    }

    // Operadores para los tipos booleanos.
    private T boolOperators(@NonNull UnaryFilter filter) {
        Value<?> value = filter.getValue();

        switch (filter.getOperator().getOperator()) {
            case "==":
                return equalTo(filter.getFields(), value, filter.isAll());
            case "!=":
                return different(filter.getFields(), value, filter.isAll());
            default:
                throw new SimpleFilterException(VALUE_ERROR +
                        filter.getOperator().getOperator());
        }
    }

    // Operadores para rangos
    private T rangeOperators(@NonNull UnaryFilter filter) {
        Value<?> value = filter.getValue();

        switch (filter.getOperator()) {
            case RANGE:
                return range(filter.getFields(), value, filter.isAll());
            case RANGOUT:
                return rangeOut(filter.getFields(), value, filter.isAll());
            default:
                throw new SimpleFilterException(VALUE_ERROR +
                        filter.getOperator().getOperator());
        }
    }

    // Operadores para lista de valores
    private T listOperators(@NonNull UnaryFilter filter) {
        Value<?> value = filter.getValue();
        List<String> fileds = getFields(filter);

        switch (filter.getOperator()) {
            case ALL:
                return all(filter.getFields(), value, filter.isAll());
            case ANY:
                return any(filter.getFields(), value, filter.isAll());
            case NONE:
                return none(filter.getFields(), value, filter.isAll());
            default:
                throw new SimpleFilterException(VALUE_ERROR +
                        filter.getOperator().getOperator());
        }
    }
}