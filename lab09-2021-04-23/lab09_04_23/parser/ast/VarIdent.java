package lab09_04_23.parser.ast;

import static java.util.Objects.requireNonNull;

public class VarIdent implements Ident {
	private final String name;

	public VarIdent(String name) {
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
		if (!(obj instanceof VarIdent))
			return false;
		return name.equals(((VarIdent) obj).name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + name + ")";
	}
}
