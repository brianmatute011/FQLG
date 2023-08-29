package cu.datys.drix.backend.filter.simple.parser.listener;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import cu.datys.drix.backend.filter.simple.exception.SimpleFilterException;

/**
 * Esta clase provee una implementación de un listener para los errores en el parser.
 */
public class SimpleFilterParserErrorListener extends BaseErrorListener{
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
           String msg, RecognitionException e) {
            throw new SimpleFilterException("Error en la posición " + charPositionInLine + ": " + msg);
    }
}
