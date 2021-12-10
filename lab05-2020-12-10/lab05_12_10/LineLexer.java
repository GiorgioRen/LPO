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
		this.pattern = Pattern.compile(regEx);
		this.matcher = pattern.matcher(line);
		
	}

	// imposta la linea con la stringa vuota e il pattern compilando regEx
	private LineLexer(String regEx) {
		this("", regEx);
	}

	// factory method che usa il costruttore LineLexer(String line, String regEx) 
	public static LineLexer withLineRegex(String line, String regEx) {
		return new LineLexer(line, regEx)
	}

	// factory method che usa il costruttore LineLexer(String regEx)
	public static LineLexer withRegex(String regEx) {
		return new LineLexer(regEx);
	}

	public void next() {
		this.result = null; // l'eventuale risultato precedente va comunque dimenticato 
		if (!this.hasNext())
			throw new NoSuchElementException();
		if (!this.matcher.lookingAt())
			throw new RuntimeException("Unrecognized lexeme at position " + matcher.regionStart());
		this.result = this.matcher.toMatchResult(); // se questa linea viene raggiunta il match ha avuto successo
		this.matcher.region(this.matcher.end(), this.matcher.regionEnd()); // viene consumato il lessema; nota: implica il reset del matcher
	}

	public String lexemeString() {
		return this.getResult().group();
	}

	public int lexemeGroup() {
		MatchResult res = this.getResult();
		int groups = res.groupCount();
		for (int group = 1; group <= groups; group++) // cerca il gruppo che ha avuto successo
			if (res.group(group) != null)
				return group;
		throw new AssertionError(); // non dovrebbe essere raggiungibile
	}

	public boolean hasNext() {
		return this.matcher.regionStart() < this.matcher.regionEnd();
	}

	public void reset(String line) {
		this.matcher.reset(line);
		this.result = null;
	}
}
