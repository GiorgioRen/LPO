package lab11_05_14.parser;

import lab11_05_14.parser.ast.Prog;

public interface Parser extends AutoCloseable {

	Prog parseProg() throws ParserException;

}