package LPO_interpreter.visitors.evaluation;

import java.io.PrintWriter;

import LPO_interpreter.environments.EnvironmentException;
import LPO_interpreter.environments.GenEnvironment;
import LPO_interpreter.parser.ast.Block;
import LPO_interpreter.parser.ast.Exp;
import LPO_interpreter.parser.ast.VarIdent;
import LPO_interpreter.parser.ast.Stmt;
import LPO_interpreter.parser.ast.StmtSeq;
import LPO_interpreter.visitors.Visitor;

import static java.util.Objects.requireNonNull;

public class Eval implements Visitor<Value> {

	private final GenEnvironment<Value> env = new GenEnvironment<>();
	private final PrintWriter printWriter; // output stream used to print values

	public Eval() {
		printWriter = new PrintWriter(System.out, true);
	}

	public Eval(PrintWriter printWriter) {
		this.printWriter = requireNonNull(printWriter);
	}

	// dynamic semantics for programs; no value returned by the visitor

	@Override
	public Value visitProg(StmtSeq stmtSeq) {
		try {
			stmtSeq.accept(this);
			// possible runtime errors
			// EnvironmentException: undefined variable
		} catch (EnvironmentException e) {
			throw new EvaluatorException(e);
		}
		return null;
	}

	// dynamic semantics for statements; no value returned by the visitor

	@Override
	public Value visitAssignStmt(VarIdent ident, Exp exp) {
		env.update(ident, exp.accept(this)); //Cambio valore associato ad ident
		return null;
	}

	@Override
	public Value visitPrintStmt(Exp exp) {
	   printWriter.println(exp.accept(this)); //Stampo il valore associato ad exp
		return null;
	}

	@Override
	public Value visitVarStmt(VarIdent ident, Exp exp) {
	    env.dec(ident, exp.accept(this)); //Dichiaro nuova variabile ident con valore exp
	    return null;
	}

	@Override
	public Value visitIfStmt(Exp exp, Block thenBlock, Block elseBlock) {
		if (exp.accept(this).toBool()) //Verifica se devo enrare nell'if
			thenBlock.accept(this); //Se si entro nel blocco then
		else if (elseBlock != null) //Altrimenti verifico che esista un else
			elseBlock.accept(this);//Se si entro nel blocco else
		return null;   
	}

	@Override
	public Value visitBlock(StmtSeq stmtSeq) {
		env.enterScope();
		stmtSeq.accept(this);
		env.exitScope();
		return null;
	}

	@Override
	public Value visitForStmt(VarIdent id, Exp exp, Block multi_stmt) { 
		RangeValue range = exp.accept(this).toRange();
		for(int cur : range.toRange()){
			env.enterScope();
			env.dec(id, new IntValue(cur));
			multi_stmt.accept(this);
			env.exitScope();
			}
		return null;
	}
	// dynamic semantics for sequences of statements
	// no value returned by the visitor

	@Override
	public Value visitSingleStmt(Stmt stmt) {
		stmt.accept(this);
		return null;
	}

	@Override
	public Value visitMoreStmt(Stmt first, StmtSeq rest) {
		first.accept(this);
		rest.accept(this);
		return null;
	}

	// dynamic semantics of expressions; a value is returned by the visitor

	@Override
	public IntValue visitAdd(Exp left, Exp right) {
	    return new IntValue(left.accept(this).toInt() + right.accept(this).toInt()); //Restituisce valore della somma
	}

	@Override
	public IntValue visitIntLiteral(int value) {
	    return new IntValue(value); //Valore value restituito come int
	}

	@Override
	public IntValue visitMul(Exp left, Exp right) {
		return new IntValue(left.accept(this).toInt() * right.accept(this).toInt());
	}

	@Override
	public IntValue visitSign(Exp exp) {
	   return new IntValue(-exp.accept(this).toInt());
	}

	@Override
	public Value visitVarIdent(VarIdent id) {
	   return env.lookup(id); //Restituisce valore variabile marchiata con id
	}

	@Override
	public BoolValue visitNot(Exp exp) {
	   return new BoolValue(!exp.accept(this).toBool()); //Simile a visitSign
	}

	@Override
	public BoolValue visitAnd(Exp left, Exp right) {
	   return new BoolValue(left.accept(this).toBool() && right.accept(this).toBool()); //Simile a visitMul
	}

	@Override
	public BoolValue visitBoolLiteral(boolean value) {
	   return new BoolValue(value); //Come visitIntLiteral
	}

	@Override
	public BoolValue visitEq(Exp left, Exp right) {
	   return new BoolValue(left.accept(this).equals(right.accept(this))); //Uso equals perchè con == controllo se sono lo stesso oggetto
	}
	
	@Override
	public BoolValue visitNeq(Exp left, Exp right) {
	   return new BoolValue(!left.accept(this).equals(right.accept(this))); //Uso !equals perchè con != controllo se sono diversi
	}

	@Override
	public PairValue visitPairLit(Exp left, Exp right) {
	   return new PairValue(left.accept(this), right.accept(this));
	}

	@Override
	public RangeValue visitRangeLit(Exp left, Exp right){
		return new RangeValue(new IntValue(left.accept(this).toInt()), new IntValue(right.accept(this).toInt()));
	}
	
	@Override
	public Value visitFst(Exp exp) {
	   return exp.accept(this).toProd().getFstVal();
	}

	@Override
	public Value visitSnd(Exp exp) {
	   return exp.accept(this).toProd().getSndVal();
	}
	
	@Override
	public PairValue visitBounds(Exp exp) {
		return new PairValue(exp.accept(this).toRange().getFstVal(), exp.accept(this).toRange().getSndVal());
	}
	
}
