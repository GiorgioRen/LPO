package LPO_interpreter.parser.ast;

import LPO_interpreter.visitors.Visitor;

public class Not extends UnaryOp {

	public Not(Exp exp) {
		super(exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitNot(exp);
	}
}
