package LPO_interpreter.parser.ast;

import LPO_interpreter.visitors.Visitor;

public class Neq extends BinaryOp {
	public Neq(Exp left, Exp right) {
		super(left, right);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitNeq(left, right);
	}
}
