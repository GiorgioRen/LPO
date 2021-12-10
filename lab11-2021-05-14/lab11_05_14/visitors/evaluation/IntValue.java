package lab11_05_14.visitors.evaluation;

public class IntValue extends PrimValue<Integer> {

	public IntValue(Integer value) {
		super(value);
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof IntValue))
			return false;
		return value.equals(((IntValue) obj).value);
	}

	@Override
	public int toInt() {
		return value;
	}

}
