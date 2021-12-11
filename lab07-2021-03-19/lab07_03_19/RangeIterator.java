package lab07_03_19;

import java.util.Iterator;
import java.util.NoSuchElementException;

class RangeIterator implements Iterator<Integer> {

	private int next; //lower bound
	private final int end; //upper bound

	//costruttore
	public RangeIterator(int start, int end){
		this.next = start;
		this.end = end;
	}

	RangeIterator(int end) {
		this(0, end);
	}

	@Override
	public boolean hasNext() {
		return next < end;
	}

	@Override
	public Integer next() {
		if (!hasNext()) throw new NoSuchElementException();
		return next++;
	}

}
