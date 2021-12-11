package LPO_interpreter.visitors.evaluation;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.hash;

public class RangeValue implements Value, Iterator<Integer>, Iterable<Integer> {

	private final Value fstVal;
	private final Value sndVal;
	private int current;

	public RangeValue(Value fstVal, Value sndVal) {
		this.fstVal = requireNonNull(fstVal);
		this.sndVal = requireNonNull(sndVal);
		current = fstVal.toInt();
	}
	
	public Value getFstVal() {
		return fstVal;
	}

	public Value getSndVal() {
		return sndVal;
	}
	
	@Override
	public boolean hasNext() {
		return current != sndVal.toInt();
	}

	@Override
	public Integer next() {
		if (!hasNext())
			throw new NoSuchElementException();
		if(fstVal.toInt() < sndVal.toInt()){
			int res = current;
			current = current+1;
			return res;
			}
		int res = current;
		current = current-1;
		return res;
	}

   //Restituisce se stesso	
	@Override
	public Iterator<Integer> iterator() {
		return this;
	}

	@Override
	public RangeValue toRange() {
		return this;
	}

	@Override
	public String toString() {
		return "[" + fstVal + ":" + sndVal + "]";
	}

	@Override
	public int hashCode() {
		return hash(fstVal,sndVal);
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof RangeValue))
			return false;
		var op = (RangeValue) obj;
		return (fstVal.equals(op.fstVal) && sndVal.equals(op.sndVal)) || (fstVal.equals(sndVal) && op.fstVal.equals(op.sndVal)) ;
	}
}
