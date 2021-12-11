package LPO_interpreter.visitors;

import LPO_interpreter.parser.ast.Block;
import LPO_interpreter.parser.ast.Exp;
import LPO_interpreter.parser.ast.Stmt;
import LPO_interpreter.parser.ast.StmtSeq;
import LPO_interpreter.parser.ast.VarIdent;

public interface Visitor<T> {
	T visitAdd(Exp left, Exp right);

	T visitAssignStmt(VarIdent ident, Exp exp);

	T visitIntLiteral(int value);

	T visitEq(Exp left, Exp right);
	
	T visitNeq(Exp left, Exp right); //AGGIUNTO NEQ -> NOT EQUALS

	T visitMoreStmt(Stmt first, StmtSeq rest);

	T visitMul(Exp left, Exp right);

	T visitPrintStmt(Exp exp);

	T visitProg(StmtSeq stmtSeq);

	T visitSign(Exp exp);

	T visitVarIdent(VarIdent id); // the only corner case ...

	T visitSingleStmt(Stmt stmt);

	T visitVarStmt(VarIdent ident, Exp exp);

	T visitNot(Exp exp);

	T visitAnd(Exp left, Exp right);

	T visitBoolLiteral(boolean value);

	T visitIfStmt(Exp exp, Block thenBlock, Block elseBlock);

	T visitBlock(StmtSeq stmtSeq);

	T visitPairLit(Exp left, Exp right);

	T visitRangeLit(Exp left, Exp right);

	T visitFst(Exp exp);

	T visitSnd(Exp exp);
	
	T visitBounds(Exp exp); //Funzione di visita BOUNDS
	
	T visitForStmt(VarIdent id, Exp exp, Block multi_stmt); //Funzione di visita per il FOR
}
