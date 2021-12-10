package lab11_05_14.visitors.evaluation;

public interface Value {
	/* default conversion methods */
	default int toInt() {
		throw new EvaluatorException("Expecting an integer");
	}

	default boolean toBool() {
		throw new EvaluatorException("Expecting a boolean");
	}

	default PairValue toProd() {
		throw new EvaluatorException("Expecting a pair");
	}
}
