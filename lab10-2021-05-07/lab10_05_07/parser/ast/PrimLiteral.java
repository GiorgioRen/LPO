package lab10_05_07.parser.ast;

public abstract class PrimLiteral<T> implements Exp {

	protected final T value;

	public PrimLiteral(T n) {
		this.value = n;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + value + ")";
	}
}
