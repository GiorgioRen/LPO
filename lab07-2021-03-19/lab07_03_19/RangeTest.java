package lab07_03_19;

import java.util.Iterator;

public class RangeTest {

  public static void main(String[] args) {
    Range r = new Range(3);
    Iterator<Integer> it1 = r.iterator();

  /* prints
   * 0 0
   * 0 1
   * 0 2
   * 1 0
   * 1 1
   * 1 2
   * 2 0
   * 2 1
   * 2 2
   */

   for (int x : r)
      for (int y : r)
      System.out.println(x + " " + y);
  }
}
