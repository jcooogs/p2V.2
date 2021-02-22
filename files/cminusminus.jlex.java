import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (literals and IDs) also include the value of the token.
class TokenVal {
  // fields
    int linenum;
    int charnum;
  // constructor
    TokenVal(int line, int ch) {
        linenum = line;
        charnum = ch;
    }
}
class IntLitTokenVal extends TokenVal {
  // new field: the value of the integer literal
    int intVal;
  // constructor
    IntLitTokenVal(int line, int ch, int val) {
        super(line, ch);
        intVal = val;
    }
}
class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int line, int ch, String val) {
        super(line, ch);
    idVal = val;
    }
}
class StrLitTokenVal extends TokenVal {
  // new field: the value of the string literal
    String strVal;
  // constructor
    StrLitTokenVal(int line, int ch, String val) {
        super(line, ch);
        strVal = val;
    }
}
// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
    static int num=1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NOT_ACCEPT,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"45:9,3,2,45:2,42,45:18,3,17,40,44,45:2,18,43,7,8,15,4,10,14,11,16,1:10,45,9" +
",12,20,13,43,45,38:26,45,41,45:2,39,45,33,21,35,28,31,32,38,37,24,38:2,23,3" +
"8,25,22,38:2,29,34,26,30,27,36,38:3,5,19,6,45:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,76,
"0,1,2,1,3,4,1:7,5,6,7,1,8,9,10,11,12,1:6,13,1:4,14,15,16,15,17,18,19,20,21," +
"22,1,23,16,24,25,26,27,28,16,15,29,14,30,31,32,33,34,35,36,37,38,39,40,41,1" +
"4,42,43,44,45,34,46,47,48")[0];

	private int yy_nxt[][] = unpackFromString(49,46,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,37,20,21,67:2,38,67,69,70,6" +
"7,71,67,72,73,67,74,50,75,67:3,40,43,-1,43,46,43,-1:47,2,-1:47,4,-1:46,22,-" +
"1:53,23,-1:7,24,-1:38,25,-1:6,26,-1:39,27,-1:47,28,-1:49,29,-1:43,30,-1:47," +
"32,-1:26,67,-1:19,67,53,67:16,54,-1:7,28,-1,28:39,-1,28:3,-1,67,-1:19,67:18" +
",54,-1:7,36:39,34,42,36:4,-1,36:24,45:2,36:13,51,39,36,45,36:2,-1:19,31,-1:" +
"27,67,-1:19,67:4,41,67:6,33,67:6,54,-1:7,36:24,35,45,36:13,51,39,36,45,36:2" +
",-1,36:39,34,39,36:4,-1,67,-1:19,67:5,33,67:12,54,-1:7,36:24,52,36:14,34,42" +
",36:4,-1,67,-1:19,67:4,33,67:13,54,-1:50,28,-1:2,67,-1:19,67:2,33,67:15,54," +
"-1:7,67,-1:19,67:10,33,67:7,54,-1:7,67,-1:19,67:7,33,67:10,54,-1:7,67,-1:19" +
",67,61,67,44,67:14,54,-1:7,67,-1:19,67,47,67:16,54,-1:7,67,-1:19,67:9,48,67" +
":8,54,-1:7,67,-1:19,67:3,49,67:14,54,-1:7,67,-1:19,67:5,63,67:12,54,-1:7,67" +
",-1:19,67:13,48,67:4,54,-1:7,67,-1:19,67:2,58,67:15,54,-1:7,67,-1:19,67:8,6" +
"8,67:9,54,-1:7,67,-1:19,67:9,41,67:8,54,-1:7,67,-1:19,67:3,64,67:14,54,-1:7" +
",67,-1:19,67:9,65,67:8,54,-1:7,67,-1:19,67:2,48,67:15,54,-1:7,67,-1:19,67:8" +
",44,67:9,54,-1:7,67,-1:19,67:14,41,67:3,54,-1:7,67,-1:19,67:9,66,67:8,54,-1" +
":7,67,-1:19,67:8,55,67:9,54,-1:7,67,-1:19,67,56,67:16,54,-1:7,67,-1:19,67:1" +
"0,57,67:7,54,-1:7,67,-1:19,67:12,59,67:5,54,-1:7,67,-1:19,67:5,60,67:12,54," +
"-1:7,67,-1:19,67:16,62,67,54,-1:6");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ 
            int val = Integer.parseInt(yytext());
            if (val > Integer.MAX_VALUE) {
            	ErrMsg.warn(yyline+1, CharNum.num, "integer literal too large; using max value");
            	Symbol s = new Symbol(sym.INTLITERAL,
                             new IntLitTokenVal(yyline+1, CharNum.num, Integer.MAX_VALUE));
            	CharNum.num += yytext().length();
            	return s;
            }
            Symbol s = new Symbol(sym.INTLITERAL,
                             new IntLitTokenVal(yyline+1, CharNum.num, val));
            CharNum.num += yytext().length();
            return s;
          }
					case -3:
						break;
					case 3:
						{ CharNum.num = 1; }
					case -4:
						break;
					case 4:
						{ CharNum.num += yytext().length(); }
					case -5:
						break;
					case 5:
						{ Symbol s = new Symbol(sym.PLUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;
          }
					case -6:
						break;
					case 6:
						{ 
            Symbol s = new Symbol(sym.LCURLY, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;
          }
					case -7:
						break;
					case 7:
						{ 
            Symbol s = new Symbol(sym.RCURLY, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;
          }
					case -8:
						break;
					case 8:
						{ 
            Symbol s = new Symbol(sym.LPAREN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;
          }
					case -9:
						break;
					case 9:
						{ 
            Symbol s = new Symbol(sym.RPAREN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -10:
						break;
					case 10:
						{ 
            Symbol s = new Symbol(sym.SEMICOLON, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -11:
						break;
					case 11:
						{ 
            Symbol s = new Symbol(sym.COMMA, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -12:
						break;
					case 12:
						{ 
            Symbol s = new Symbol(sym.DOT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -13:
						break;
					case 13:
						{ 
            Symbol s = new Symbol(sym.LESS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -14:
						break;
					case 14:
						{ 
            Symbol s = new Symbol(sym.GREATER, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -15:
						break;
					case 15:
						{ 
            Symbol s = new Symbol(sym.MINUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -16:
						break;
					case 16:
						{ 
            Symbol s = new Symbol(sym.TIMES, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -17:
						break;
					case 17:
						{ 
            Symbol s = new Symbol(sym.DIVIDE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -18:
						break;
					case 18:
						{ 
            Symbol s = new Symbol(sym.NOTEQUALS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -19:
						break;
					case 19:
						{ 
					ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num++;
          }
					case -20:
						break;
					case 20:
						{ 
            Symbol s = new Symbol(sym.ASSIGN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return s;   
          }
					case -21:
						break;
					case 21:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -22:
						break;
					case 22:
						{ 
            Symbol s = new Symbol(sym.PLUSPLUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            CharNum.num++;
            return s;  
          }
					case -23:
						break;
					case 23:
						{ 
            Symbol s = new Symbol(sym.WRITE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            CharNum.num++;
            return s;   
          }
					case -24:
						break;
					case 24:
						{ 
            Symbol s = new Symbol(sym.LESSEQ, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            CharNum.num++;
            return s;   
          }
					case -25:
						break;
					case 25:
						{ 
            Symbol s = new Symbol(sym.READ, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            CharNum.num++;
            return s;   
          }
					case -26:
						break;
					case 26:
						{ 
            Symbol s = new Symbol(sym.GREATEREQ, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            CharNum.num++;
            return s;   
          }
					case -27:
						break;
					case 27:
						{ 
            Symbol s = new Symbol(sym.MINUSMINUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            CharNum.num++;
            return s;   
          }
					case -28:
						break;
					case 28:
						{
					CharNum.num = yytext().length();
				}
					case -29:
						break;
					case 29:
						{ 
            Symbol s = new Symbol(sym.NOTEQUALS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            CharNum.num++;
            return s;   
          }
					case -30:
						break;
					case 30:
						{ 
            Symbol s = new Symbol(sym.AND, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            CharNum.num++;
            return s;   
          }
					case -31:
						break;
					case 31:
						{ 
            Symbol s = new Symbol(sym.OR, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            CharNum.num++;
            return s;   
          }
					case -32:
						break;
					case 32:
						{ 
            Symbol s = new Symbol(sym.EQUALS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            CharNum.num++;
            return s;   
          }
					case -33:
						break;
					case 33:
						{
			switch (yytext()) {
				case ("bool"):
					Symbol s = new Symbol(sym.BOOL, new TokenVal(yyline+1, CharNum.num));
            		CharNum.num += yytext().length();
            		return s;
            	case ("int"):
            		Symbol a = new Symbol(sym.INT, new TokenVal(yyline+1, CharNum.num));
            		CharNum.num += yytext().length();
            		return a;
            	case("void"):
            	    Symbol b = new Symbol(sym.VOID, new TokenVal(yyline+1, CharNum.num));
            	    CharNum.num+=yytext().length();
            	    return b;
            	case("true"):
                     Symbol c = new Symbol(sym.TRUE, new TokenVal(yyline+1, CharNum.num));
                     CharNum.num+=yytext().length();
                     return c;
                case("false"):
                     Symbol d = new Symbol(sym.FALSE, new TokenVal(yyline+1, CharNum.num));
                     CharNum.num+=yytext().length();
                     return d;
                case("struct"):
                     Symbol e = new Symbol(sym.STRUCT, new TokenVal(yyline+1, CharNum.num));
                     CharNum.num+=yytext().length();
                     return e;
                case("cin"):
                     Symbol f = new Symbol(sym.CIN, new TokenVal(yyline+1, CharNum.num));
                     CharNum.num+=yytext().length();
                     return f;
                case("cout"):
                     Symbol g = new Symbol(sym.COUT, new TokenVal(yyline+1, CharNum.num));
                     CharNum.num+=yytext().length();
                     return g;
                case("if"):
                     Symbol h = new Symbol(sym.IF, new TokenVal(yyline+1, CharNum.num));
                     CharNum.num+=yytext().length();
                     return h;
                case("else"):
                     Symbol i = new Symbol(sym.ELSE, new TokenVal(yyline+1, CharNum.num));
                     CharNum.num+=yytext().length();
                     return i;
                case("while"):
                     Symbol j = new Symbol(sym.WHILE, new TokenVal(yyline+1, CharNum.num));
                     CharNum.num+=yytext().length();
                     return j;
                case("return"):
                     Symbol l = new Symbol(sym.RETURN, new TokenVal(yyline+1, CharNum.num));
                     CharNum.num+=yytext().length();
                     return l;
                }
			}
					case -34:
						break;
					case 34:
						{
					Symbol s = new Symbol(sym.STRINGLITERAL, 
						new StrLitTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
				}
					case -35:
						break;
					case 35:
						{
                       ErrMsg.fatal(yyline+1, CharNum.num,
                        "unterminated string literal ignored");
                        CharNum.num+=yytext().length();
                    }
					case -36:
						break;
					case 37:
						{ 
					ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num++;
          }
					case -37:
						break;
					case 38:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -38:
						break;
					case 40:
						{ 
					ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num++;
          }
					case -39:
						break;
					case 41:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -40:
						break;
					case 43:
						{ 
					ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num++;
          }
					case -41:
						break;
					case 44:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -42:
						break;
					case 46:
						{ 
					ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num++;
          }
					case -43:
						break;
					case 47:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -44:
						break;
					case 48:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -45:
						break;
					case 49:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -46:
						break;
					case 50:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -47:
						break;
					case 51:
						{
					Symbol s = new Symbol(sym.STRINGLITERAL, 
						new StrLitTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
				}
					case -48:
						break;
					case 52:
						{
                       ErrMsg.fatal(yyline+1, CharNum.num,
                        "unterminated string literal ignored");
                        CharNum.num+=yytext().length();
                    }
					case -49:
						break;
					case 53:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -50:
						break;
					case 54:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -51:
						break;
					case 55:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -52:
						break;
					case 56:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -53:
						break;
					case 57:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -54:
						break;
					case 58:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -55:
						break;
					case 59:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -56:
						break;
					case 60:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -57:
						break;
					case 61:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -58:
						break;
					case 62:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -59:
						break;
					case 63:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -60:
						break;
					case 64:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -61:
						break;
					case 65:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -62:
						break;
					case 66:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -63:
						break;
					case 67:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -64:
						break;
					case 68:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -65:
						break;
					case 69:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -66:
						break;
					case 70:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -67:
						break;
					case 71:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -68:
						break;
					case 72:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -69:
						break;
					case 73:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -70:
						break;
					case 74:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -71:
						break;
					case 75:
						{ 
                if (!yytext().equals("bool")||!yytext().equals("int")||!yytext().equals("void")||!yytext().equals("true")||!yytext().equals("false")||
                 !yytext().equals("struct")||!yytext().equals("cin")||!yytext().equals("cout")||!yytext().equals("if")||!yytext().equals("else") ||
                 !yytext().equals("while")||!yytext().equals("return")){
                	Symbol s = new Symbol(sym.ID, new IdTokenVal(yyline+1, CharNum.num, yytext()));
                	CharNum.num += yytext().length();
                	return s;
                }else{
                	ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            		CharNum.num += yytext().length();
            	}
             }
					case -72:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
