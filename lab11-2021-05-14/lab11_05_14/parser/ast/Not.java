package lab11_05_14.parser.ast;

import lab11_05_14.visitors.Visitor;

public class Not extends UnaryOp {

	public Not(Exp exp) {
		super(exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitNot(exp);
	}
}
