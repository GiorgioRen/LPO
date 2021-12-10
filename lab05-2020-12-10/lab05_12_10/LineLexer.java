package lab05_12_10;

import java.util.NoSuchElementException;
import java.util.regex.*;

public class LineLexer implements Lexer {
	private final Pattern pattern;
	private final Matcher matcher;
	private MatchResult result;

	private MatchResult getResult() {
		if (this.result == null)
			throw new IllegalStateException();
		return this.result;
	}

	// imposta la linea con line e il pattern compilando regEx
	private LineLexer(String line, String regEx) {
		// completare
		this.pattern = Pattern.compile(regEx);
		this.matcher = pattern.matcher(line);
		
	}

	// imposta la linea con la stringa vuota e il pattern compilando regEx
	private LineLexer(String regEx) {
		// completare
		this.pattern = Pattern.compile(regEx);
		this.matcher = pattern.matcher("");
	}

	// factory method che usa il costruttore LineLexer(String line, String regEx) 
	public static LineLexer withLineRegex(String line, String regEx) {
		// completare
		return new LineLexer(line, regEx)
	}

	// factory method che usa il costruttore LineLexer(String regEx)
	public static LineLexer withRegex(String regEx) {
		// completare
		return new LineLexer(regEx);
	}

	public void next() {
		// completare
	}

	public String lexemeString() {
	    // completare
	}

	public int lexemeGroup() {
		// completare
	}

	public boolean hasNext() {
		// completare
		return this.matcher.matches();
	}

	public void reset(String line) {
	    // completare
	}
}
