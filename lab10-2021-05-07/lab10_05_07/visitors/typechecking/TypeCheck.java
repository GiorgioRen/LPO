package lab10_05_07.visitors.typechecking;

import static lab10_05_07.visitors.typechecking.PrimType.*;

import lab10_05_07.environments.EnvironmentException;
import lab10_05_07.environments.GenEnvironment;
import lab10_05_07.parser.ast.*;
import lab10_05_07.visitors.Visitor;

public class TypeCheck implements Visitor<Type> {

	private final GenEnvironment<Type> env = new GenEnvironment<>();

        // useful to typecheck binary operations where operands must have the same type 
        private void checkBinOp(Exp left, Exp right, Type type) {
		type.checkEqual(left.accept(this));
		type.checkEqual(right.accept(this));
	}

	// static semantics for programs; no value returned by the visitor

	@Override
	public Type visitProg(StmtSeq stmtSeq) {
		try {
			stmtSeq.accept(this);
		} catch (EnvironmentException e) { // undeclared variable
			throw new TypecheckerException(e);
		}
		return null;
	}

	// static semantics for statements; no value returned by the visitor

	@Override
	public Type visitAssignStmt(VarIdent ident, Exp exp) {
	    // completare
	}

	@Override
	public Type visitPrintStmt(Exp exp) {
	    // completare
	}

	@Override
	public Type visitVarStmt(VarIdent ident, Exp exp) {
	    // completare
	}

	@Override
	public Type visitIfStmt(Exp exp, Block thenBlock, Block elseBlock) {
	    // completare
	}

	@Override
	public Type visitBlock(StmtSeq stmtSeq) {
	    // completare
	}

	// static semantics for sequences of statements
	// no value returned by the visitor

	@Override
	public Type visitSingleStmt(Stmt stmt) {
	    // completare
	}

	@Override
	public Type visitMoreStmt(Stmt first, StmtSeq rest) {
	    // completare
	}

	// static semantics of expressions; a type is returned by the visitor

	@Override
	public PrimType visitAdd(Exp left, Exp right) {
	    // completare
	}

	@Override
	public PrimType visitIntLiteral(int value) {
	    // completare
	}

	@Override
	public PrimType visitMul(Exp left, Exp right) {
	    // completare
	}

	@Override
	public Type visitSign(Exp exp) {
	    // completare
	}

	@Override
	public Type visitVarIdent(VarIdent id) {
	    // completare
	}

	@Override
	public Type visitNot(Exp exp) {
	    // completare
	}

	@Override
	public PrimType visitAnd(Exp left, Exp right) {
	    // completare
	}

	@Override
	public PrimType visitBoolLiteral(boolean value) {
	    // completare
	}

	@Override
	public PrimType visitEq(Exp left, Exp right) {
	    // completare
	}

	@Override
	public ProdType visitPairLit(Exp left, Exp right) {
	    // completare
	}

	@Override
	public Type visitFst(Exp exp) {
	    // completare
	}

	@Override
	public Type visitSnd(Exp exp) {
	    // completare
	}

}
