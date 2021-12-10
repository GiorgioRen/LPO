package lab11_05_14.parser.ast;

import lab11_05_14.visitors.Visitor;

public interface AST {
	<T> T accept(Visitor<T> visitor);
}
