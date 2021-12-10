package lab09_04_23.parser.ast;

public class VarStmt extends AbstractAssignStmt {

	public VarStmt(Ident ident, Exp exp) {
		super(ident, exp);
	}

}
