package lab11_05_14.parser.ast;

import lab11_05_14.visitors.Visitor;

public class Mul extends BinaryOp {
	public Mul(Exp left, Exp right) {
		super(left, right);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitMul(left, right);
	}
}
