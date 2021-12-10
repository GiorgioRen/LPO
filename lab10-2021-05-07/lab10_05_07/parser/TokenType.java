package lab10_05_07.parser;

public enum TokenType { 
	// symbols
	ASSIGN, MINUS, PLUS, TIMES, NOT, AND, EQ, START_PAIR, END_PAIR, STMT_SEP, EXP_SEP, OPEN_PAR, CLOSE_PAR, OPEN_BLOCK, CLOSE_BLOCK,
	// keywords
	PRINT, VAR, BOOL, IF, ELSE, FST, SND,
	// non singleton categories
	SKIP, IDENT, NUM,   
	// end-of-file
	EOF, 	
}
