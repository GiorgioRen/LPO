package lab09_04_23.parser;

import lab09_04_23.parser.ast.Prog;

public interface Parser extends AutoCloseable {

	Prog parseProg() throws ParserException;

}