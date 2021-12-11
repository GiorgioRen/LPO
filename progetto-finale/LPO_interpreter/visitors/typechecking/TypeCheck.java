package LPO_interpreter.visitors.typechecking;

import static LPO_interpreter.visitors.typechecking.PrimType.*;

import LPO_interpreter.environments.EnvironmentException;
import LPO_interpreter.environments.GenEnvironment;
import LPO_interpreter.parser.ast.*;
import LPO_interpreter.visitors.Visitor;

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
		Type found = env.lookup(ident);
		found.checkEqual(exp.accept(this));
		return null;
	}

	@Override
	public Type visitPrintStmt(Exp exp) {
		exp.accept(this);
		return null;
	}
	
	@Override
	public Type visitVarStmt(VarIdent ident, Exp exp) {
		env.dec(ident, exp.accept(this));
		return null;
	}

	@Override
	public Type visitIfStmt(Exp exp, Block thenBlock, Block elseBlock) {
		BOOL.checkEqual(exp.accept(this));
		thenBlock.accept(this);
		if (elseBlock == null)
			return null;
		elseBlock.accept(this);
		return null;
	}

	@Override
	public Type visitBlock(StmtSeq stmtSeq) {
		env.enterScope();
		stmtSeq.accept(this);
		env.exitScope();
		return null;
	}

	@Override
	public Type visitForStmt(VarIdent id, Exp exp, Block multi_stmt) { 
		exp.accept(this).checkIsRangeType();
		env.enterScope();
		env.dec(id, INT);
		env.enterScope();
		multi_stmt.accept(this);
		env.exitScope();
		env.exitScope();
		return null;
	}

	// static semantics for sequences of statements
	// no value returned by the visitor

	@Override
	public Type visitSingleStmt(Stmt stmt) {
		stmt.accept(this);
		return null;
	}

	@Override
	public Type visitMoreStmt(Stmt first, StmtSeq rest) {
		first.accept(this);
		rest.accept(this);
		return null;
	}

	// static semantics of expressions; a type is returned by the visitor

	@Override
	public PrimType visitAdd(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public PrimType visitIntLiteral(int value) {
		return INT;
	}

	@Override
	public PrimType visitMul(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public Type visitSign(Exp exp) {
		return INT.checkEqual(exp.accept(this));
	}

	@Override
	public Type visitVarIdent(VarIdent id) {
		return env.lookup(id);
	}

	@Override
	public Type visitNot(Exp exp) {
		return BOOL.checkEqual(exp.accept(this));
	}

	@Override
	public PrimType visitAnd(Exp left, Exp right) {
		checkBinOp(left, right, BOOL);
		return BOOL;
	}

	@Override
	public PrimType visitBoolLiteral(boolean value) {
		return BOOL;
	}

	@Override
	public PrimType visitEq(Exp left, Exp right) {
		left.accept(this).checkEqual(right.accept(this));
		return BOOL;
	}

	@Override
	public PrimType visitNeq(Exp left, Exp right) {
		left.accept(this).checkEqual(right.accept(this));
		return BOOL;
	}
	
	@Override
	public ProdType visitPairLit(Exp left, Exp right) {
		return new ProdType(left.accept(this), right.accept(this));
	}

	@Override
	public RangeType visitRangeLit(Exp left, Exp right){
		return new RangeType(INT.checkEqual(left.accept(this)), INT.checkEqual(right.accept(this)));
	}

	@Override
	public Type visitFst(Exp exp) {
		return exp.accept(this).getFstProdType();
	}

	@Override
	public Type visitSnd(Exp exp) {
		return exp.accept(this).getSndProdType();
	}
	
	@Override
	public ProdType visitBounds(Exp exp) {
		return new ProdType(exp.accept(this).getFstRangeType(), exp.accept(this).getSndRangeType());
	}
	
}
