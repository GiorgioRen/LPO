package lab07_03_19;

import java.util.Iterator;

public class Range implements Iterable<Integer> {

  // aggiungere i campi necessari
  private int start; //lower bound
  private int end; //upper bound

  // ranges from start (inclusive) to end (exclusive)
  public Range(int start, int end) {
    // completare
    this.start=start;
    this.end=end;
  }

  // ranges from 0 (inclusive) to end (exclusive)
  public Range(int end) {
    // completare
    start=0;
    this.end=end;
  }

  @Override
  public RangeIterator iterator() {
    // completare
    return new RangeIterator(start,end);
  }

}
