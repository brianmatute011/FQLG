// Generated from SimpleFilter.g4 by ANTLR 4.13.0

package cu.datys.drix.backend.filter.simple.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpleFilterParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpleFilterVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#simple_filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_filter(SimpleFilterParser.Simple_filterContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#parenthesis_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis_expression(SimpleFilterParser.Parenthesis_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#simple_parenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_parenthesis(SimpleFilterParser.Simple_parenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#simple_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_expr(SimpleFilterParser.Simple_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#filter_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_expr(SimpleFilterParser.Filter_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#fields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFields(SimpleFilterParser.FieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(SimpleFilterParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#field_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_list(SimpleFilterParser.Field_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#range_val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange_val(SimpleFilterParser.Range_valContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#list_val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_val(SimpleFilterParser.List_valContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#simple_val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_val(SimpleFilterParser.Simple_valContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleFilterParser#term_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_value(SimpleFilterParser.Term_valueContext ctx);
}