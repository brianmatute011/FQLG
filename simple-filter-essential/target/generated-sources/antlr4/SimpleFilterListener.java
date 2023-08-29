// Generated from SimpleFilter.g4 by ANTLR 4.13.0

package cu.datys.drix.backend.filter.simple.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleFilterParser}.
 */
public interface SimpleFilterListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#simple_filter}.
	 * @param ctx the parse tree
	 */
	void enterSimple_filter(SimpleFilterParser.Simple_filterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#simple_filter}.
	 * @param ctx the parse tree
	 */
	void exitSimple_filter(SimpleFilterParser.Simple_filterContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#parenthesis_expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis_expression(SimpleFilterParser.Parenthesis_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#parenthesis_expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis_expression(SimpleFilterParser.Parenthesis_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#simple_parenthesis}.
	 * @param ctx the parse tree
	 */
	void enterSimple_parenthesis(SimpleFilterParser.Simple_parenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#simple_parenthesis}.
	 * @param ctx the parse tree
	 */
	void exitSimple_parenthesis(SimpleFilterParser.Simple_parenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#simple_expr}.
	 * @param ctx the parse tree
	 */
	void enterSimple_expr(SimpleFilterParser.Simple_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#simple_expr}.
	 * @param ctx the parse tree
	 */
	void exitSimple_expr(SimpleFilterParser.Simple_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#filter_expr}.
	 * @param ctx the parse tree
	 */
	void enterFilter_expr(SimpleFilterParser.Filter_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#filter_expr}.
	 * @param ctx the parse tree
	 */
	void exitFilter_expr(SimpleFilterParser.Filter_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#fields}.
	 * @param ctx the parse tree
	 */
	void enterFields(SimpleFilterParser.FieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#fields}.
	 * @param ctx the parse tree
	 */
	void exitFields(SimpleFilterParser.FieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(SimpleFilterParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(SimpleFilterParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#field_list}.
	 * @param ctx the parse tree
	 */
	void enterField_list(SimpleFilterParser.Field_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#field_list}.
	 * @param ctx the parse tree
	 */
	void exitField_list(SimpleFilterParser.Field_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#range_val}.
	 * @param ctx the parse tree
	 */
	void enterRange_val(SimpleFilterParser.Range_valContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#range_val}.
	 * @param ctx the parse tree
	 */
	void exitRange_val(SimpleFilterParser.Range_valContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#list_val}.
	 * @param ctx the parse tree
	 */
	void enterList_val(SimpleFilterParser.List_valContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#list_val}.
	 * @param ctx the parse tree
	 */
	void exitList_val(SimpleFilterParser.List_valContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#simple_val}.
	 * @param ctx the parse tree
	 */
	void enterSimple_val(SimpleFilterParser.Simple_valContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#simple_val}.
	 * @param ctx the parse tree
	 */
	void exitSimple_val(SimpleFilterParser.Simple_valContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleFilterParser#term_value}.
	 * @param ctx the parse tree
	 */
	void enterTerm_value(SimpleFilterParser.Term_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleFilterParser#term_value}.
	 * @param ctx the parse tree
	 */
	void exitTerm_value(SimpleFilterParser.Term_valueContext ctx);
}