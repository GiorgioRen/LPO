package LPO_interpreter.parser;

public enum TokenType { 
	// symbols
	ASSIGN, MINUS, PLUS, TIMES, NOT, AND, EQ, NEQ, START_PAIR, END_PAIR, STMT_SEP, EXP_SEP, OPEN_PAR, CLOSE_PAR, OPEN_BLOCK, CLOSE_BLOCK, OPEN_RANGE, CLOSE_RANGE, COLON,
	// keywords
	PRINT, VAR, BOOL, IF, ELSE, FST, SND, FOR, IN, BOUNDS,
	// non singleton categories
	SKIP, IDENT, NUM,   
	// end-of-file
	EOF, 	
}
