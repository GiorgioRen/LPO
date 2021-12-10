package lab08_04_09;

import java.util.Map;

public interface WordCounter {

	Map<String, Integer> count(Readable readable);

	default Map<String, Integer> countSorted(Readable readable) {
		throw new UnsupportedOperationException();
	}
}
