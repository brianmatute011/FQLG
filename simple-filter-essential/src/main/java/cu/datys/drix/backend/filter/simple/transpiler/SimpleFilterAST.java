package cu.datys.drix.backend.filter.simple.transpiler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.*;

import cu.datys.drix.backend.filter.simple.model.criteria.BinarySimpleCriteria;
import cu.datys.drix.backend.filter.simple.model.criteria.SimpleCriteria;
import cu.datys.drix.backend.filter.simple.model.criteria.UnaryFilter;
import cu.datys.drix.backend.filter.simple.model.operator.Operator;
import cu.datys.drix.backend.filter.simple.model.types.Condition;
import cu.datys.drix.backend.filter.simple.model.value.*;
import cu.datys.drix.backend.filter.simple.parser.SimpleFilterBaseVisitor;
import cu.datys.drix.backend.filter.simple.parser.SimpleFilterParser;
import cu.datys.drix.backend.filter.simple.model.types.FieldSymbol;
import cu.datys.drix.backend.filter.simple.exception.*;

public class SimpleFilterAST extends SimpleFilterBaseVisitor<Object> {
    private static final String NUMERIC_ERROR = "Valor no permitido para tipo numerico.";
    private static final String DATE_ERROR = "Valor no permitido para tipo fecha.";

    @Override
    public SimpleCriteria visitSimple_filter(SimpleFilterParser.Simple_filterContext ctx) {
        return visitParenthesis_expression(ctx.parenthesis_expression());
    }

    @Override
    public SimpleCriteria visitParenthesis_expression(SimpleFilterParser.Parenthesis_expressionContext ctx) {
        SimpleFilterParser.Simple_exprContext simpleExprContext = ctx.simple_expr();
        SimpleFilterParser.Simple_parenthesisContext parenthesisContext = ctx.simple_parenthesis();

        if (simpleExprContext != null) { // Es una expresion simple
            return visitSimple_expr(simpleExprContext);
        } else if (parenthesisContext != null) { // Parentesis anidados
            return visitSimple_parenthesis(parenthesisContext);
        } else {
            Condition logOp = Condition.valueOf(ctx.LOGICAL_OPERATOR().getText().toUpperCase());

            return new BinarySimpleCriteria(logOp,
                    visitParenthesis_expression(ctx.parenthesis_expression(0)),
                    visitParenthesis_expression(ctx.parenthesis_expression(1)));
        }
    }

    @Override
    public SimpleCriteria visitSimple_parenthesis(SimpleFilterParser.Simple_parenthesisContext ctx) {
        return visitParenthesis_expression(ctx.parenthesis_expression());
    }

    @Override
    public SimpleCriteria visitSimple_expr(SimpleFilterParser.Simple_exprContext ctx) {
        UnaryFilter unaryFilter = (UnaryFilter) visitFilter_expr(ctx.filter_expr());

        if (ctx.LOGICAL_OPERATOR() != null) {
            Condition logOp = Condition.valueOf(ctx.LOGICAL_OPERATOR().getText().toUpperCase());
            return new BinarySimpleCriteria(logOp, unaryFilter, visitParenthesis_expression(ctx.parenthesis_expression()));
        } else {
            return unaryFilter;
        }
    }

    @Override
    public SimpleCriteria visitFilter_expr(SimpleFilterParser.Filter_exprContext ctx) {
        String fieldsString = ctx.fields().getText();

        List<String> fields = new ArrayList<>();
        String fieldSymbol = ctx.FIELD_SYMBOL() != null ? ctx.FIELD_SYMBOL().getText(): FieldSymbol.ALL.getSymbol();

        if (!"*".equals(fieldsString))
            fields = Stream.of(fieldsString.substring(1, fieldsString.length() - 1).split(",")).collect(Collectors.toList());

        Operator operator;

        switch (ctx.OPERATOR().getText()) {
            case "==":
                operator = Operator.EQUAL;
                break;
            case "!=":
                operator = Operator.NOT_EQUAL;
                break;
            case ">":
                operator = Operator.GREATER_THAN;
                break;
            case ">=":
                operator = Operator.GREATER_THAN_EQUALS;
                break;
            case "<":
                operator = Operator.LESS_THAN;
                break;
            case "<=":
                operator = Operator.LESS_THAN_EQUALS;
                break;
            default:
                operator = Operator.valueOf(ctx.OPERATOR().getText());
        }

        Value<?> value = visitValue(ctx.value());
        return new UnaryFilter(operator, value, fields, fieldSymbol.equals(FieldSymbol.ALL.getSymbol()));
    }

    @Override
    public Value<?> visitValue(SimpleFilterParser.ValueContext ctx) {
        String text = ctx.getText();
        System.out.print("OMG\n");

        if (text.startsWith("[")) { // Es una lista de valores
            List<String> valuesList = Stream.of(text.substring(1, text.length() - 1).replace("\"","").split(",")).
                    collect(Collectors.toList());

            return obtainValue(valuesList);
        } else if (text.startsWith("FROM")) { // Es un rango
            String[] value = text.replace("FROM", "").replace("TO", " ").split(" ");
            String from = value[0];
            String to = value[1];

            return obtainValue(from, to);
        }
        else { // Es un valor simple
            return obtainValue(text);
        }
    }

    private Value<?> obtainValue(String valueString) {
        if (isNumber(valueString)) {
            try {
                Double numberValue = Double.parseDouble(valueString);
                return new NumberValue(numberValue);
            } catch (Exception e) {
                throw new SimpleFilterException(NUMERIC_ERROR);
            }
        } else if (isDate(valueString)) {
            try {
                return new DateValue(valueString);
            } catch (Exception e) {
                throw new SimpleFilterException(e.getMessage());
            }
        } else if (isBoolean(valueString)){
            return new BooleanValue(Boolean.parseBoolean(valueString));
        } else {
            return isTerm(valueString) ? new TermValue(valueString.substring(1)
                    .replace("'", "")) : new StringValue(valueString.replace("'", ""));
        }
    }

    private Value<?> obtainValue(String from, String to) {
        if (isNumber(from) && isNumber(to)) {
            try {
                Double numberFrom = Double.parseDouble(from);
                Double numberTo = Double.parseDouble(to);
                return new NumberValue(numberFrom, numberTo);
            } catch (Exception e) {
                throw new SimpleFilterException(NUMERIC_ERROR);
            }
        } else if (isDate(from) && isDate(to)) {
            try {
                return new DateValue(from, to);
            } catch (Exception e) {
                throw new SimpleFilterException(DATE_ERROR);
            }
        } else {
            throw new SimpleFilterException("Error en el formato de los valores del rango.");
        }
    }

    private Value<?> obtainValue(List<String> valuesList) {
        if (valuesList.stream().allMatch(this::isNumber)) {
            try {
                return new NumberValue(valuesList.stream().map(Double::parseDouble).collect(Collectors.toList()));
            } catch (Exception e) {
                throw new SimpleFilterException(NUMERIC_ERROR);
            }
        } else if (valuesList.stream().allMatch(this::isDate)) {
            try {
                return new DateValue(valuesList.stream().map(Long::parseLong).collect(Collectors.toList()));
            } catch (Exception e) {
                throw new SimpleFilterException(DATE_ERROR);
            }
        } else if (valuesList.stream().allMatch(this::isTerm)) {
            return new TermValue(valuesList.stream().map(value -> value.substring(1).replace("'", "")).
                    collect(Collectors.toList()));
        } else { // Todos los elementos son tratados como strings
            return new StringValue(valuesList.stream().map(value -> value.replace("'", "")).
                    collect(Collectors.toList()));
        }
    }

    private boolean isNumber(String valueString) {
        final Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?"); // Expresión regular para matchear números.
        return numberPattern.matcher(valueString).matches();
    }

    private boolean isDate(String valueString) {
        return !(isTerm(valueString) || valueString.startsWith("'")) && valueString.contains("/");
    }

    private boolean isTerm(String valueString) {
        return valueString.startsWith("T");
    }

    private boolean isBoolean(String valueString) {
        return "true".equals(valueString) || "false".equals(valueString);
    }
}