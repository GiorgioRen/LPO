package lab10_05_07.parser.ast;

import lab10_05_07.visitors.Visitor;

public class BoolLiteral extends PrimLiteral<Boolean> {

	public BoolLiteral(boolean b) {
		super(b);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitBoolLiteral(value);
	}

}
