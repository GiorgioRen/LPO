package lab10_05_07.parser.ast;

import lab10_05_07.visitors.Visitor;

public class Sign extends UnaryOp {

	public Sign(Exp exp) {
		super(exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitSign(exp);
	}
}
