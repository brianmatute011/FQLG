// Generated from SimpleFilter.g4 by ANTLR 4.13.0

package cu.datys.drix.backend.filter.simple.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SimpleFilterParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, FIELD_SYMBOL=11, LOGICAL_OPERATOR=12, OPERATOR=13, BOOLEAN_VALUE=14, 
		DATE_VALUE=15, NUMBER_VALUE=16, STRING_VALUE=17, IDENT=18, WHITESPACE=19;
	public static final int
		RULE_simple_filter = 0, RULE_parenthesis_expression = 1, RULE_simple_parenthesis = 2, 
		RULE_simple_expr = 3, RULE_filter_expr = 4, RULE_fields = 5, RULE_value = 6, 
		RULE_field_list = 7, RULE_range_val = 8, RULE_list_val = 9, RULE_simple_val = 10, 
		RULE_term_value = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"simple_filter", "parenthesis_expression", "simple_parenthesis", "simple_expr", 
			"filter_expr", "fields", "value", "field_list", "range_val", "list_val", 
			"simple_val", "term_value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "')'", "'*'", "'['", "']'", "','", "'FROM'", "'TO'", 
			"'T'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "FIELD_SYMBOL", 
			"LOGICAL_OPERATOR", "OPERATOR", "BOOLEAN_VALUE", "DATE_VALUE", "NUMBER_VALUE", 
			"STRING_VALUE", "IDENT", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SimpleFilter.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimpleFilterParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_filterContext extends ParserRuleContext {
		public Parenthesis_expressionContext parenthesis_expression() {
			return getRuleContext(Parenthesis_expressionContext.class,0);
		}
		public Simple_filterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterSimple_filter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitSimple_filter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitSimple_filter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_filterContext simple_filter() throws RecognitionException {
		Simple_filterContext _localctx = new Simple_filterContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_simple_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			parenthesis_expression(0);
			setState(25);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Parenthesis_expressionContext extends ParserRuleContext {
		public Simple_parenthesisContext simple_parenthesis() {
			return getRuleContext(Simple_parenthesisContext.class,0);
		}
		public Simple_exprContext simple_expr() {
			return getRuleContext(Simple_exprContext.class,0);
		}
		public List<Parenthesis_expressionContext> parenthesis_expression() {
			return getRuleContexts(Parenthesis_expressionContext.class);
		}
		public Parenthesis_expressionContext parenthesis_expression(int i) {
			return getRuleContext(Parenthesis_expressionContext.class,i);
		}
		public TerminalNode LOGICAL_OPERATOR() { return getToken(SimpleFilterParser.LOGICAL_OPERATOR, 0); }
		public Parenthesis_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesis_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterParenthesis_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitParenthesis_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitParenthesis_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parenthesis_expressionContext parenthesis_expression() throws RecognitionException {
		return parenthesis_expression(0);
	}

	private Parenthesis_expressionContext parenthesis_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Parenthesis_expressionContext _localctx = new Parenthesis_expressionContext(_ctx, _parentState);
		Parenthesis_expressionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_parenthesis_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				setState(28);
				simple_parenthesis();
				}
				break;
			case T__3:
			case T__4:
			case FIELD_SYMBOL:
				{
				setState(29);
				simple_expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(37);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Parenthesis_expressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_parenthesis_expression);
					setState(32);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(33);
					match(LOGICAL_OPERATOR);
					setState(34);
					parenthesis_expression(3);
					}
					} 
				}
				setState(39);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_parenthesisContext extends ParserRuleContext {
		public Parenthesis_expressionContext parenthesis_expression() {
			return getRuleContext(Parenthesis_expressionContext.class,0);
		}
		public Simple_parenthesisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_parenthesis; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterSimple_parenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitSimple_parenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitSimple_parenthesis(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_parenthesisContext simple_parenthesis() throws RecognitionException {
		Simple_parenthesisContext _localctx = new Simple_parenthesisContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_simple_parenthesis);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__1);
			setState(41);
			parenthesis_expression(0);
			setState(42);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_exprContext extends ParserRuleContext {
		public Filter_exprContext filter_expr() {
			return getRuleContext(Filter_exprContext.class,0);
		}
		public TerminalNode LOGICAL_OPERATOR() { return getToken(SimpleFilterParser.LOGICAL_OPERATOR, 0); }
		public Parenthesis_expressionContext parenthesis_expression() {
			return getRuleContext(Parenthesis_expressionContext.class,0);
		}
		public Simple_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterSimple_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitSimple_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitSimple_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_exprContext simple_expr() throws RecognitionException {
		Simple_exprContext _localctx = new Simple_exprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_simple_expr);
		try {
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				filter_expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				filter_expr();
				setState(46);
				match(LOGICAL_OPERATOR);
				setState(47);
				parenthesis_expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Filter_exprContext extends ParserRuleContext {
		public FieldsContext fields() {
			return getRuleContext(FieldsContext.class,0);
		}
		public TerminalNode OPERATOR() { return getToken(SimpleFilterParser.OPERATOR, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode FIELD_SYMBOL() { return getToken(SimpleFilterParser.FIELD_SYMBOL, 0); }
		public Filter_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterFilter_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitFilter_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitFilter_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Filter_exprContext filter_expr() throws RecognitionException {
		Filter_exprContext _localctx = new Filter_exprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_filter_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FIELD_SYMBOL) {
				{
				setState(51);
				match(FIELD_SYMBOL);
				}
			}

			setState(54);
			fields();
			setState(55);
			match(OPERATOR);
			setState(56);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldsContext extends ParserRuleContext {
		public Field_listContext field_list() {
			return getRuleContext(Field_listContext.class,0);
		}
		public FieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterFields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitFields(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitFields(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldsContext fields() throws RecognitionException {
		FieldsContext _localctx = new FieldsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fields);
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				match(T__3);
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				match(T__4);
				setState(60);
				field_list();
				setState(61);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public Range_valContext range_val() {
			return getRuleContext(Range_valContext.class,0);
		}
		public List_valContext list_val() {
			return getRuleContext(List_valContext.class,0);
		}
		public Simple_valContext simple_val() {
			return getRuleContext(Simple_valContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				range_val();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				list_val();
				}
				break;
			case T__9:
			case BOOLEAN_VALUE:
			case DATE_VALUE:
			case NUMBER_VALUE:
			case STRING_VALUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				simple_val();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Field_listContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(SimpleFilterParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(SimpleFilterParser.IDENT, i);
		}
		public Field_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterField_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitField_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitField_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_listContext field_list() throws RecognitionException {
		Field_listContext _localctx = new Field_listContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_field_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(IDENT);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(71);
				match(T__6);
				setState(72);
				match(IDENT);
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Range_valContext extends ParserRuleContext {
		public List<TerminalNode> DATE_VALUE() { return getTokens(SimpleFilterParser.DATE_VALUE); }
		public TerminalNode DATE_VALUE(int i) {
			return getToken(SimpleFilterParser.DATE_VALUE, i);
		}
		public List<TerminalNode> NUMBER_VALUE() { return getTokens(SimpleFilterParser.NUMBER_VALUE); }
		public TerminalNode NUMBER_VALUE(int i) {
			return getToken(SimpleFilterParser.NUMBER_VALUE, i);
		}
		public Range_valContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range_val; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterRange_val(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitRange_val(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitRange_val(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Range_valContext range_val() throws RecognitionException {
		Range_valContext _localctx = new Range_valContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_range_val);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(T__7);
			setState(79);
			_la = _input.LA(1);
			if ( !(_la==DATE_VALUE || _la==NUMBER_VALUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(80);
			match(T__8);
			setState(81);
			_la = _input.LA(1);
			if ( !(_la==DATE_VALUE || _la==NUMBER_VALUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class List_valContext extends ParserRuleContext {
		public List<Term_valueContext> term_value() {
			return getRuleContexts(Term_valueContext.class);
		}
		public Term_valueContext term_value(int i) {
			return getRuleContext(Term_valueContext.class,i);
		}
		public List<TerminalNode> NUMBER_VALUE() { return getTokens(SimpleFilterParser.NUMBER_VALUE); }
		public TerminalNode NUMBER_VALUE(int i) {
			return getToken(SimpleFilterParser.NUMBER_VALUE, i);
		}
		public List<TerminalNode> STRING_VALUE() { return getTokens(SimpleFilterParser.STRING_VALUE); }
		public TerminalNode STRING_VALUE(int i) {
			return getToken(SimpleFilterParser.STRING_VALUE, i);
		}
		public List_valContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_val; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterList_val(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitList_val(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitList_val(this);
			else return visitor.visitChildren(this);
		}
	}

	public final List_valContext list_val() throws RecognitionException {
		List_valContext _localctx = new List_valContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_list_val);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__4);
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				{
				setState(84);
				term_value();
				}
				break;
			case NUMBER_VALUE:
				{
				setState(85);
				match(NUMBER_VALUE);
				}
				break;
			case STRING_VALUE:
				{
				setState(86);
				match(STRING_VALUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(89);
				match(T__6);
				setState(93);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__9:
					{
					setState(90);
					term_value();
					}
					break;
				case NUMBER_VALUE:
					{
					setState(91);
					match(NUMBER_VALUE);
					}
					break;
				case STRING_VALUE:
					{
					setState(92);
					match(STRING_VALUE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_valContext extends ParserRuleContext {
		public Term_valueContext term_value() {
			return getRuleContext(Term_valueContext.class,0);
		}
		public TerminalNode DATE_VALUE() { return getToken(SimpleFilterParser.DATE_VALUE, 0); }
		public TerminalNode NUMBER_VALUE() { return getToken(SimpleFilterParser.NUMBER_VALUE, 0); }
		public TerminalNode BOOLEAN_VALUE() { return getToken(SimpleFilterParser.BOOLEAN_VALUE, 0); }
		public TerminalNode STRING_VALUE() { return getToken(SimpleFilterParser.STRING_VALUE, 0); }
		public Simple_valContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_val; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterSimple_val(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitSimple_val(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitSimple_val(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_valContext simple_val() throws RecognitionException {
		Simple_valContext _localctx = new Simple_valContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_simple_val);
		try {
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				term_value();
				}
				break;
			case DATE_VALUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(DATE_VALUE);
				}
				break;
			case NUMBER_VALUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				match(NUMBER_VALUE);
				}
				break;
			case BOOLEAN_VALUE:
				enterOuterAlt(_localctx, 4);
				{
				setState(105);
				match(BOOLEAN_VALUE);
				}
				break;
			case STRING_VALUE:
				enterOuterAlt(_localctx, 5);
				{
				setState(106);
				match(STRING_VALUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Term_valueContext extends ParserRuleContext {
		public TerminalNode STRING_VALUE() { return getToken(SimpleFilterParser.STRING_VALUE, 0); }
		public Term_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).enterTerm_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleFilterListener ) ((SimpleFilterListener)listener).exitTerm_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleFilterVisitor ) return ((SimpleFilterVisitor<? extends T>)visitor).visitTerm_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term_valueContext term_value() throws RecognitionException {
		Term_valueContext _localctx = new Term_valueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_term_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__9);
			setState(110);
			match(STRING_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return parenthesis_expression_sempred((Parenthesis_expressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean parenthesis_expression_sempred(Parenthesis_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0013q\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001\u001f\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001$"+
		"\b\u0001\n\u0001\f\u0001\'\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u00032\b\u0003\u0001\u0004\u0003\u00045\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005@\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006E\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005"+
		"\u0007J\b\u0007\n\u0007\f\u0007M\t\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tX\b\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t^\b\t\u0005\t`\b\t\n\t\f\tc\t\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\nl\b\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0000\u0001\u0002\f\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000\u0001\u0001\u0000\u000f\u0010"+
		"u\u0000\u0018\u0001\u0000\u0000\u0000\u0002\u001e\u0001\u0000\u0000\u0000"+
		"\u0004(\u0001\u0000\u0000\u0000\u00061\u0001\u0000\u0000\u0000\b4\u0001"+
		"\u0000\u0000\u0000\n?\u0001\u0000\u0000\u0000\fD\u0001\u0000\u0000\u0000"+
		"\u000eF\u0001\u0000\u0000\u0000\u0010N\u0001\u0000\u0000\u0000\u0012S"+
		"\u0001\u0000\u0000\u0000\u0014k\u0001\u0000\u0000\u0000\u0016m\u0001\u0000"+
		"\u0000\u0000\u0018\u0019\u0003\u0002\u0001\u0000\u0019\u001a\u0005\u0001"+
		"\u0000\u0000\u001a\u0001\u0001\u0000\u0000\u0000\u001b\u001c\u0006\u0001"+
		"\uffff\uffff\u0000\u001c\u001f\u0003\u0004\u0002\u0000\u001d\u001f\u0003"+
		"\u0006\u0003\u0000\u001e\u001b\u0001\u0000\u0000\u0000\u001e\u001d\u0001"+
		"\u0000\u0000\u0000\u001f%\u0001\u0000\u0000\u0000 !\n\u0002\u0000\u0000"+
		"!\"\u0005\f\u0000\u0000\"$\u0003\u0002\u0001\u0003# \u0001\u0000\u0000"+
		"\u0000$\'\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000%&\u0001\u0000"+
		"\u0000\u0000&\u0003\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000"+
		"()\u0005\u0002\u0000\u0000)*\u0003\u0002\u0001\u0000*+\u0005\u0003\u0000"+
		"\u0000+\u0005\u0001\u0000\u0000\u0000,2\u0003\b\u0004\u0000-.\u0003\b"+
		"\u0004\u0000./\u0005\f\u0000\u0000/0\u0003\u0002\u0001\u000002\u0001\u0000"+
		"\u0000\u00001,\u0001\u0000\u0000\u00001-\u0001\u0000\u0000\u00002\u0007"+
		"\u0001\u0000\u0000\u000035\u0005\u000b\u0000\u000043\u0001\u0000\u0000"+
		"\u000045\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u000067\u0003\n\u0005"+
		"\u000078\u0005\r\u0000\u000089\u0003\f\u0006\u00009\t\u0001\u0000\u0000"+
		"\u0000:@\u0005\u0004\u0000\u0000;<\u0005\u0005\u0000\u0000<=\u0003\u000e"+
		"\u0007\u0000=>\u0005\u0006\u0000\u0000>@\u0001\u0000\u0000\u0000?:\u0001"+
		"\u0000\u0000\u0000?;\u0001\u0000\u0000\u0000@\u000b\u0001\u0000\u0000"+
		"\u0000AE\u0003\u0010\b\u0000BE\u0003\u0012\t\u0000CE\u0003\u0014\n\u0000"+
		"DA\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000DC\u0001\u0000\u0000"+
		"\u0000E\r\u0001\u0000\u0000\u0000FK\u0005\u0012\u0000\u0000GH\u0005\u0007"+
		"\u0000\u0000HJ\u0005\u0012\u0000\u0000IG\u0001\u0000\u0000\u0000JM\u0001"+
		"\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000"+
		"L\u000f\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000NO\u0005\b\u0000"+
		"\u0000OP\u0007\u0000\u0000\u0000PQ\u0005\t\u0000\u0000QR\u0007\u0000\u0000"+
		"\u0000R\u0011\u0001\u0000\u0000\u0000SW\u0005\u0005\u0000\u0000TX\u0003"+
		"\u0016\u000b\u0000UX\u0005\u0010\u0000\u0000VX\u0005\u0011\u0000\u0000"+
		"WT\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000WV\u0001\u0000\u0000"+
		"\u0000Xa\u0001\u0000\u0000\u0000Y]\u0005\u0007\u0000\u0000Z^\u0003\u0016"+
		"\u000b\u0000[^\u0005\u0010\u0000\u0000\\^\u0005\u0011\u0000\u0000]Z\u0001"+
		"\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]\\\u0001\u0000\u0000\u0000"+
		"^`\u0001\u0000\u0000\u0000_Y\u0001\u0000\u0000\u0000`c\u0001\u0000\u0000"+
		"\u0000a_\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bd\u0001\u0000"+
		"\u0000\u0000ca\u0001\u0000\u0000\u0000de\u0005\u0006\u0000\u0000e\u0013"+
		"\u0001\u0000\u0000\u0000fl\u0003\u0016\u000b\u0000gl\u0005\u000f\u0000"+
		"\u0000hl\u0005\u0010\u0000\u0000il\u0005\u000e\u0000\u0000jl\u0005\u0011"+
		"\u0000\u0000kf\u0001\u0000\u0000\u0000kg\u0001\u0000\u0000\u0000kh\u0001"+
		"\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kj\u0001\u0000\u0000\u0000"+
		"l\u0015\u0001\u0000\u0000\u0000mn\u0005\n\u0000\u0000no\u0005\u0011\u0000"+
		"\u0000o\u0017\u0001\u0000\u0000\u0000\u000b\u001e%14?DKW]ak";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}