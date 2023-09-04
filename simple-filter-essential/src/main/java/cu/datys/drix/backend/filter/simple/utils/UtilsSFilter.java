package cu.datys.drix.backend.filter.simple.utils;
import cu.datys.drix.backend.filter.simple.parser.SimpleFilterLexer;
import cu.datys.drix.backend.filter.simple.parser.SimpleFilterParser;
import cu.datys.drix.backend.filter.simple.parser.listener.SimpleFilterParserErrorListener;
import cu.datys.drix.backend.filter.simple.transpiler.SimpleFilterAST;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;

public class UtilsSFilter {
    public static Object parseQuery(String query) {
        SimpleFilterLexer lexer = new SimpleFilterLexer(CharStreams.fromString(query));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new SimpleFilterParserErrorListener());
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleFilterParser parser = new SimpleFilterParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new SimpleFilterParserErrorListener());

        RuleContext tree = parser.simple_filter();
        SimpleFilterAST visitor = new  SimpleFilterAST();
        return visitor.visit(tree);
    }
}
