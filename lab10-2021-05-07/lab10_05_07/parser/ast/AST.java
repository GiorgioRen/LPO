package lab10_05_07.parser.ast;

import lab10_05_07.visitors.Visitor;

public interface AST {
	<T> T accept(Visitor<T> visitor);
}
