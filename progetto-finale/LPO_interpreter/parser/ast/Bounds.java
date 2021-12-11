package LPO_interpreter.parser.ast;

import LPO_interpreter.visitors.Visitor;

public class Bounds extends UnaryOp {

	public Bounds(Exp exp) {
		super(exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitBounds(exp);
	}
}
