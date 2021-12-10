package lab09_04_23.parser.ast;

import static java.util.Objects.requireNonNull;

public abstract class AbstractAssignStmt implements Stmt {
	protected final Ident ident;
	protected final Exp exp;

	protected AbstractAssignStmt(Ident ident, Exp exp) {
		this.ident = requireNonNull(ident);
		this.exp = requireNonNull(exp);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + ident + "," + exp + ")";
	}
}
