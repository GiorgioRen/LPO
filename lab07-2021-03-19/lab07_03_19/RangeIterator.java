package lab07_03_19;

import java.util.Iterator;
import java.util.NoSuchElementException;

class RangeIterator implements Iterator<Integer> {

    // aggiungere i campi e i costruttori necessari
    private int start; //lower bound
    private int end; //upper bound

    //costruttore
    public RangeIterator(int start, int end){
		  this.start = start;
		  this.end = end;
	  }

    @Override
    public boolean hasNext() {
	// completare
      return start<end;
    }

    @Override
    public Integer next() {
	// completare
      return start++; //modifico start
    }

}
