package lab11_05_14.visitors.evaluation;

public class BoolValue extends PrimValue<Boolean> {

	public BoolValue(Boolean value) {
		super(value);
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BoolValue))
			return false;
		return value.equals(((BoolValue) obj).value);
	}

	@Override
	public boolean toBool() {
		return value;
	}

}
