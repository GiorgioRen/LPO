package lab11_05_14.parser.ast;

import lab11_05_14.visitors.Visitor;

public class PairLit extends BinaryOp {
	public PairLit(Exp left, Exp right) {
		super(left, right);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitPairLit(left, right);
	}
}
