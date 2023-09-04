package cu.datys.drix.backend.filter;

import cu.datys.drix.backend.filter.simple.model.criteria.BinarySimpleCriteria;
import cu.datys.drix.backend.filter.simple.model.criteria.SimpleCriteria;
import cu.datys.drix.backend.filter.simple.executor.SimpleOperatorExecutor;
import cu.datys.drix.backend.filter.simple.model.criteria.UnaryFilter;
import cu.datys.drix.backend.filter.simple.parser.SimpleFilterLexer;
import cu.datys.drix.backend.filter.simple.parser.SimpleFilterParser;
import cu.datys.drix.backend.filter.simple.parser.listener.SimpleFilterParserErrorListener;
import cu.datys.drix.backend.filter.simple.transpiler.SimpleFilterAST;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import cu.datys.drix.backend.filter.simple.utils.UtilsSFilter;


/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        System.out.println(
                ((UnaryFilter)UtilsSFilter.parseQuery("[a, b] == 'a';")).toString()
        );

        System.out.println(
                ((BinarySimpleCriteria)UtilsSFilter.parseQuery("([a, b] == 'a') AND ([z, c] == 'c' OR [w, w] == 'w');")).toString()
        );

    }




}
