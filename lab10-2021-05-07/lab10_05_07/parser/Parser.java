package lab10_05_07.parser;

import lab10_05_07.parser.ast.Prog;

public interface Parser extends AutoCloseable {

	Prog parseProg() throws ParserException;

}