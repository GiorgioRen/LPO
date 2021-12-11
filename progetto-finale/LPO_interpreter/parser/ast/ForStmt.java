package LPO_interpreter.parser.ast;

import LPO_interpreter.visitors.Visitor;

import static java.util.Objects.requireNonNull;


public class ForStmt implements Stmt {
	private final VarIdent id;
	private final Exp exp;
	private final Block multi_stmt;
	
	public ForStmt(VarIdent id, Exp exp, Block multi_stmt) {
		this.id = requireNonNull(id);
		this.exp = requireNonNull(exp);
		this.multi_stmt = requireNonNull(multi_stmt);
	}

	/*@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + id + "," + exp + "," + stmts + ")";
	}*/

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitForStmt(id, exp, multi_stmt);
	}
}
