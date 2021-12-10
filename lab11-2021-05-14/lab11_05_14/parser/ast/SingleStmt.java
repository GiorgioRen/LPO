package lab11_05_14.parser.ast;

import lab11_05_14.visitors.Visitor;

public class SingleStmt extends Single<Stmt> implements StmtSeq {

	public SingleStmt(Stmt single) {
		super(single);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitSingleStmt(single);
	}
}
