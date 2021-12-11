package LPO_interpreter.visitors.typechecking;

import static java.util.Objects.requireNonNull;

public class RangeType implements Type {

	private final Type fstType;
	private final Type sndType;

	public static final String TYPE_NAME = "RANGE";

	public RangeType(Type fstType, Type sndType) {
		this.fstType = requireNonNull(fstType);
		this.sndType = requireNonNull(sndType);
	}

	public Type getFstType() {
		return fstType;
	}

	public Type getSndType() {
		return sndType;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof RangeType))
			return false;
		RangeType pt = (RangeType) obj;
		return fstType.equals(pt.fstType) && sndType.equals(pt.sndType);
	}

	@Override
	public int hashCode() {
		return fstType.hashCode() + 31 * sndType.hashCode();
	}

	@Override
	public String toString() {
		return "RANGE";
	}

}
