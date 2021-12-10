package lab08_04_09;

import java.util.Map;
import java.util.TreeMap;

public class WordCounterSorted extends WordCounterUnsorted {

	@Override
	public Map<String, Integer> countSorted(Readable readable) {
		var map = new TreeMap<String, Integer>();
		this.count(readable, map);
		return map;
	}

}
