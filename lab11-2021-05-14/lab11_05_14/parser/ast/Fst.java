package lab11_05_14.parser.ast;

import lab11_05_14.visitors.Visitor;

public class Fst extends UnaryOp {

	public Fst(Exp exp) {
		super(exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitFst(exp);
	}
}
