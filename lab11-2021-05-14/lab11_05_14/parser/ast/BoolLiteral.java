package lab11_05_14.parser.ast;

import lab11_05_14.visitors.Visitor;

public class BoolLiteral extends PrimLiteral<Boolean> {

	public BoolLiteral(boolean b) {
		super(b);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitBoolLiteral(value);
	}

}
