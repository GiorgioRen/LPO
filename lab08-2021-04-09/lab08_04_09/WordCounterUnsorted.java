package lab08_04_09;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounterUnsorted implements WordCounter {

	protected void count(Readable readable, Map<String, Integer> map) {
		try (var sc = new Scanner(readable)) {
			sc.useDelimiter("[^a-zA-Z]+");
			while (sc.hasNext()) {
				var word = sc.next();
				var n = map.get(word);
				map.put(word, n != null ? n + 1 : 1);
			}
		}
	}

	@Override
	public Map<String, Integer> count(Readable readable) {
		var map = new HashMap<String, Integer>();
		this.count(readable, map);
		return map;
	}

}
