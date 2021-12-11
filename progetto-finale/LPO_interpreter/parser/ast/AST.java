package LPO_interpreter.parser.ast;

import LPO_interpreter.visitors.Visitor;

public interface AST {
	<T> T accept(Visitor<T> visitor);
}
