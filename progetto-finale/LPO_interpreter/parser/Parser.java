package LPO_interpreter.parser;

import LPO_interpreter.parser.ast.Prog;

public interface Parser extends AutoCloseable {

	Prog parseProg() throws ParserException;

}