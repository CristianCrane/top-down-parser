import java.util.Arrays;
import java.util.HashMap;

/**

This class is a lexical analyzer for the tokens defined by the grammar:

<plus> --> +
<minus> --> -
<times> --> *
<div> --> /
<LParen> --> "("
<RParen> --> ")"
<LBrace> --> "{"
<RBrace> --> "}"
<colon> --> ":"
<semicolon> --> ";"
<or> --> "||"
<and> --> "&&"
<int> --> { <digit> }+
<id> --> <letter> { <letter> | <digit> }
<float> --> { <digit> } "." { <digit> }+
<floatE> --> <float> (E|e) [+|-] { <digit> }+
<inv> --> "!"
<neq> --> "!="
<assign> --> "="
<eq> --> "=="
<lt> --> "<"
<le> --> "<="
<gt> --> ">"
<ge> --> ">="
<Keyword_if> --> "if"
<Keyword_else> --> "else"
<Keyword_switch> --> "switch"
<Keyword_case> --> "case"
<Keyword_default> --> "default"
<Keyword_while> --> "while"
<Keyword_do> --> "do"
<Keyword_for> --> "for"
<Keyword_print> --> "print"
<Keyword_false> --> "false"
<Keyword_true> --> "true"

This class implements a DFA that will accept the above tokens.

The DFA states are represented by the Enum type "State".
The DFA has the following 10 final states represented by enum-type literals:

state     token accepted

Id        identifiers
Int       integers
Float     floats without exponentiation part
FloatE    floats with exponentiation part
Plus      +
Minus     -
Times     *
Div       /
LParen    (
RParen    )
LBrace		{
RBrace		}
colon		:
semicolon	;
or				||
and				&&
inv				!
neq				!=
assign			=
eq				==
lt				<
le				<=
gt				>
ge				>=
Keyword_if
Keyword_else
Keyword_switch
Keyword_case
Keyword_default
Keyword_while
Keyword_do
Keyword_for
Keyword_print
Keyword_false
Keyword_true

The DFA also uses the following 6 non-final states:

state      string recognized

Start      the empty string
Period     float parts ending with "."
E          float parts ending with E or e
EPlusMinus float parts ending with + or - in exponentiation part
Bar        a single bar eg. |
ampersand  a single ampersand eg. &

The function "driver" operates the DFA.
The function "nextState" returns the next state given the current state and the input character.

To recognize a different token set, modify the following:

  enum type "State", function "isFinal", and function "isKeyword"
  function "nextState"

The functions "driver", "getToken" remain the same.

**/


public abstract class LexArith extends IO
{
	public enum State
 	{
	  // non-final states     ordinal number

		Start,             // 0
		Period,            // 1
		E,                 // 2
		EPlusMinus,        // 3
		Bar,			   // 4
		Ampersand,		   // 5

	  // final states

		Id,                // 6
		Int,               // 7
		Float,             // 8
		FloatE,            // 9
		Plus,              // 10
		Minus,             // 11
		Times,             // 12
		Div,               // 13
		LParen,            // 14
		RParen,            // 15
		LBrace,			   // 16
		RBrace,			   // 17
		Colon,			   // 18
		Semicolon,		   // 19
		Or,				   // 20
		And,			   // 21
		Inv,			   // 22
		Neq,   			   // 23
		Assign,			   // 24
		Eq,				   // 25
		Lt,				   // 26
		Le,				   // 27
		Gt,				   // 28
		Ge,				   // 29
		Keyword_if,		   // 30
		Keyword_else,	   // 31
		Keyword_switch,	   // 32
		Keyword_case,	   // 33
		Keyword_default,   // 34
		Keyword_while,	   // 35
		Keyword_do,		   // 36
		Keyword_for,	   // 37
		Keyword_print,	   // 38
		Keyword_false,	   // 39
		Keyword_true,	   // 40

		UNDEF;			   // 41

		private boolean isFinal()
		{
			return ( this.compareTo(State.Id) >= 0 );
		}
	}

	// By enumerating the non-final states first and then the final states,
	// test for a final state can be done by testing if the state's ordinal number
	// is greater than or equal to that of Id.

	// The following variables of "IO" class are used:

	//   static int a; the current input character
	//   static char c; used to convert the variable "a" to the char type whenever necessary

	public static String t; // holds an extracted token
	public static State state; // the current state of the FA
	public static HashMap<String,State> keywords = new HashMap<String,State>();
	
	private static int driver()

	// This is the driver of the FA.
	// If a valid token is found, assigns it to "t" and returns 1.
	// If an invalid token is found, assigns it to "t" and returns 0.
	// If end-of-stream is reached without finding any non-whitespace character, returns -1.

	{
		State nextSt; // the next state of the FA

		t = "";
		state = State.Start;

		if ( Character.isWhitespace((char) a) )
			a = getChar(); // get the next non-whitespace character
		if ( a == -1 ) // end-of-stream is reached
			return -1;

		while ( a != -1 ) // do the body if "a" is not end-of-stream
		{
			c = (char) a;
			nextSt = nextState( state, c );
			if ( nextSt == State.UNDEF ) // The FA will halt.
			{
				if ( state.isFinal() )
					return 1; // valid token extracted
				else // "c" is an unexpected character
				{
					t = t+c;
					a = getNextChar();
					return 0; // invalid token found
				}
			}
			else // The FA will go on.
			{
				state = nextSt;
				t = t+c;
				a = getNextChar();
			}
		}

		// end-of-stream is reached while a token is being extracted

		if ( state.isFinal() )
			return 1; // valid token extracted
		else
			return 0; // invalid token found
	} // end driver

	public static void getToken()

	// Extract the next token using the driver of the FA.
	// If an invalid token is found, issue an error message.

	{
		int i = driver();
		if (keywords.containsKey(t))
			state = keywords.get(t);
		else if ( i == 0 )
			displayln( t+" : Lexical Error, invalid token");
		
	}

	private static State nextState(State s, char c)

	// Returns the next state of the FA given the current state and input char;
	// if the next state is undefined, UNDEF is returned.

	{
		switch( state )
		{
		case Start:
			if ( Character.isLetter(c) )
				return State.Id;
			else if ( Character.isDigit(c) )
				return State.Int;
			else if ( c == '+' )
				return State.Plus;
			else if ( c == '-' )
				return State.Minus;
			else if ( c == '*' )
				return State.Times;
			else if ( c == '/' )
				return State.Div;
			else if ( c == '(' )
				return State.LParen;
			else if ( c == ')' )
				return State.RParen;
			else if ( c == '{')
				return State.LBrace;
			else if ( c == '}')
				return State.RBrace;
			else if ( c == ':')
				return State.Colon;
			else if ( c == ';')
				return State.Semicolon;
			else if ( c == '|')
				return State.Bar;
			else if ( c == '&')
				return State.Ampersand;
			else if ( c == '.')
				return State.Period;
			else if ( c == '!')
				return State.Inv;
			else if ( c == '=')
				return State.Assign;
			else if ( c == '<')
				return State.Lt;
			else if ( c == '>')
				return State.Gt;
			else
				return State.UNDEF;
		case Id:
			if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		case Int:
			if ( Character.isDigit(c) )
				return State.Int;
			else if ( c == '.' )
				return State.Float;
			else
				return State.UNDEF;
		case Period:
			if ( Character.isDigit(c) )
				return State.Float;
			else
				return State.UNDEF;
		case Float:
			if ( Character.isDigit(c) )
				return State.Float;
			else if ( c == 'e' || c == 'E' )
				return State.E;
			else
				return State.UNDEF;
		case E:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else if ( c == '+' || c == '-' )
				return State.EPlusMinus;
			else
				return State.UNDEF;
		case EPlusMinus:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		case FloatE:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		case Inv:
			if ( c == '=')
				return State.Neq;
			else
				return State.UNDEF;
		case Assign:
			if ( c == '=')
				return State.Eq;
			else
				return State.UNDEF;
		case Lt:
			if ( c == '=')
				return State.Le;
			else
				return State.UNDEF;
		case Gt:
			if ( c == '=')
				return State.Ge;
			else
				return State.UNDEF;
		case Bar:
			if ( c == '|')
				return State.Or;
			else
				return State.UNDEF;
		case Ampersand:
			if ( c == '&')
				return State.And;
			else
				return State.UNDEF;
		default:
			return State.UNDEF;
		}
	} // end nextState

	// Edit here to recognize more keywords
	public static void setKeywords(){
		keywords.put("if",State.Keyword_if);
		keywords.put("else",State.Keyword_else);
		keywords.put("switch",State.Keyword_switch);
		keywords.put("case",State.Keyword_case);
		keywords.put("default",State.Keyword_default);
		keywords.put("while",State.Keyword_while);
		keywords.put("do",State.Keyword_do);
		keywords.put("for",State.Keyword_for);
		keywords.put("print",State.Keyword_print);
		keywords.put("false",State.Keyword_false);
		keywords.put("true",State.Keyword_true);		
	}
	
	public static void main(String argv[])
	{
		
		// argv[0]: input file containing source code using tokens defined above
		// argv[1]: output file displaying a list of the tokens
		setIO( argv[0], argv[1] );
		setKeywords();
		
		int i;

		while ( a != -1 ) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if ( i == 1 )
			{
				if (keywords.containsKey(t)){
					state = keywords.get(t);
				}
				displayln( t+"   : "+state.toString() );
			}
			else if ( i == 0 )
				displayln( t+" : Lexical Error, invalid token");
		}

		closeIO();

	}
}
