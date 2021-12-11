package LPO_interpreter.parser.ast;

import static java.util.Objects.requireNonNull;

import LPO_interpreter.visitors.Visitor;

public class VarIdentAST implements VarIdent {
	private final String name;

	public VarIdentAST(String name) {
		this.name = requireNonNull(name);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof VarIdentAST))
			return false;
		return name.equals(((VarIdentAST) obj).name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + name + ")";
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitVarIdent(this);
	}

}
