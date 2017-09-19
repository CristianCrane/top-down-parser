/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<assignment list> --> { <assignment> }+
<assignment> --> <id> = <E> ";"
<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"

Note: The binary operators +, -, *, / associate to left.

The definitions of the tokens are given in the lexical analyzer class file "LexArithArray.java". 

The following variables/functions of "IO"/"LexArithArray" classes are used:

static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

static void setLex()
static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token

An explicit parse tree is constructed in the form of nested class objects.
 
The main function will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks.

All iterations of the form { ... } and { ... }+ are implemented by java.util.LinkedList.
Function call list.add(x) appends element x to the end of list. 

**/

import java.util.*;

public abstract class Parser extends LexArith
{
	static boolean errorFound = false;

	public static Statement statement()
	// <statement> --> <assignStatement> | <conditionalStatement> | <switchStatement> | <while statement> | <do while> | <for statement> | <print statement> | <block>
	{
		Statement s = null;
		
		// <assignStatement> --> <id> "=" <Expr> ";"
		if ( state == State.Id )
		{
			Id id = new Id(t);
			getToken();
			if ( state == State.Assign )
			{
				getToken();
				Expr expr = expr();
				if (state == State.Semicolon)
				{
					getToken();
					return new AssignStatement(id,expr);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		
		// <conditionalStatement> --> "if" "(" <expr> ")" <statement> [ "else" <statement> ]
		else if (state == State.Keyword_if) 
		{
			getToken();
			if (state == State.LParen)
			{
				getToken();
				Expr expr = expr();
				if (state == State.RParen)
				{
					getToken();
					Statement ifStmt = statement();
					if (state == State.Keyword_else)
					{
						getToken();
						Statement elseStmt = statement();
						return new IfElseStatement(expr,ifStmt,elseStmt);
					}
					return new IfStatement(expr,ifStmt);
				}
				else
					errorMsg(1);
			}
			else
				errorMsg(2);
		}
		
		// <switch> --> "switch" "(" <expr> ")" "{" <case list> "}" 
		else if (state == State.Keyword_switch)
		{
			getToken();
			if (state == State.LParen)
			{
				getToken();
				Expr expr = expr();
				if (state == State.RParen)
				{
					getToken();
					if (state == State.LBrace)
					{
						getToken();
						CaseList caseList = caseList();
						if (state == State.RBrace)
						{
							getToken();
							return new SwitchStatement(expr, caseList);
						}
						else{
							errorMsg(7);
						}
					}
					else
						errorMsg(6);
				}
				else
					errorMsg(1);
			}
			else
				errorMsg(2);
		}
		
		// <while loop> --> "while" "(" <expr> ")" <statement> 
		else if (state == State.Keyword_while)
		{
			getToken();
			if (state == State.LParen)
			{
				getToken();
				Expr exp = expr();
				if (state == State.RParen)
				{
					getToken();
					Statement stmt = statement();
					return new WhileStatement(exp,stmt);
				}
				else
					errorMsg(1);
			}
			else
				errorMsg(2);
		}
		
		// <do loop> --> "do" <statement> "while" "(" <expr> ")" ";" 
		else if (state == State.Keyword_do)
		{
			getToken();
			Statement stmt = statement();
			if (state == State.Keyword_while)
			{
				getToken();
				if (state == State.LParen)
				{
					getToken();
					Expr expr = expr();
					if (state == State.RParen)
					{
						getToken();
						if (state == State.Semicolon)
						{
							getToken();
							return new DoWhileStatement(expr,stmt);
						}
						else
							errorMsg(4);
					}
					else
						errorMsg(1);
				}
				else
					errorMsg(2);
			}
			else
				errorMsg(11);
		}
		
		// <for loop> --> "for" "(" <assign> ";" <expr> ";" <assign> ")" <statement> 
		else if (state == State.Keyword_for)
		{
			getToken();
			if (state == State.LParen)
			{
				getToken();
				Assign init = assign();
				if (state == State.Semicolon)
				{
					getToken();
					Expr expr = expr();
					if (state == State.Semicolon)
					{
						getToken();
						Assign incr = assign();
						if (state == State.RParen)
						{
							getToken();
							Statement stmt = statement();
							return new ForStatement(init,expr,incr,stmt);
						}
						else
							errorMsg(1);
					}
					else
						errorMsg(4);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(2);
		}
		// <print> --> "print" <expr> ";" 
		else if (state == State.Keyword_print)
		{
			getToken();
			Expr expr = expr();
			if (state == State.Semicolon)
			{
				getToken();
				return new PrintStatement(expr);
			}
			else
				errorMsg(4);
		}
		// <block> --> "{" <s list> "}" 
		else if (state == State.LBrace)
		{
			getToken();
			StatementList slist = statementList();
			if (state == State.RBrace)
			{
				getToken();
				return new BlockStatement(slist);
			}
			else
				errorMsg(7);
		}
		else
			errorMsg(5);
		
		return s;
	}

	public static Expr expr()
	// <expr> --> <boolTerm> { "||" <boolTerm> }
	{
		LinkedList<BoolTermItem> boolTermItems = new LinkedList<BoolTermItem>();
		
		BoolTerm boolterm = boolTerm();
		boolTermItems.add(new SingleBoolTermItem(boolterm));
		
		while (state == State.Or)
		{
			getToken();
			boolterm = boolTerm();
			boolTermItems.add(new OrBoolTermItem(boolterm));
		}
		
		return new Expr(boolTermItems);
	}

	public static BoolTerm boolTerm()
	// <boolTerm> --> <boolPrimary> { "&&" <boolPrimary> } 
	{
		LinkedList<BoolPrimaryItem> pItemList = new LinkedList<BoolPrimaryItem>();
		
		BoolPrimary boolprimary = boolPrimary();
		pItemList.add(new SingleBoolPrimaryItem(boolprimary));
		
		while (state == State.And)
		{
			getToken();
			boolprimary = boolPrimary();
			pItemList.add(new AndBoolPrimaryItem(boolprimary));
		}
		
		return new BoolTerm(pItemList);
	}

	public static BoolPrimary boolPrimary()
	// <boolPrimary> --> <E> [ <rel op> <E> ] 
	{
		E e1 = E();
		E e2; // may not need if only single bool primary
		
		switch (state)
		{
			case Lt:
				getToken();
				e2 = E();
				return new LtBoolPrimary(e1,e2); 
			case Le: 
				getToken();
				e2 = E();
				return new LteBoolPrimary(e1,e2);
			case Gt:
				getToken();
				e2 = E();
				return new GtBoolPrimary(e1,e2);
			case Ge:
				getToken();
				e2 = E();
				return new GteBoolPrimary(e1,e2);
			case Eq:
				getToken();
				e2 = E();
				return new EqBoolPrimary(e1,e2);
			case Neq: 
				getToken();
				e2 = E();
				return new NotEqBoolPrimary(e1,e2);
			default:
				return new SingleBoolPrimary(e1);
		}
	}
	
	public static CaseList caseList()
	// <case list> --> { <case> }+ 
	{
		LinkedList<SwitchCase> cList = new LinkedList<SwitchCase>();
		SwitchCase sc = switchCase();
		cList.add(sc);
		
		while (state == State.Keyword_case || state == State.Keyword_default)
		{
			sc = switchCase();
			cList.add(sc);
		}
		
		return new CaseList(cList);
	}
	
	public static SwitchCase switchCase()
	// <case> --> "case" <label> ":" <s list> | "default" ":" <s list> 
	{
		if (state == State.Keyword_case)
		{
			getToken();
			Label l = label();
			if (state == State.Colon)
			{
				getToken();
				StatementList sList = statementList();
				return new LabeledCase(l,sList);
			}
			else
				errorMsg(10);
		}
		else if (state == State.Keyword_default)
		{
			getToken();
			if (state == State.Colon)
			{
				getToken();
				StatementList sList = statementList();
				return new DefaultCase(sList);
			}
			else
				errorMsg(10);
		}
		else
			errorMsg(8);
		return null;
	}
	
	public static Label label()
	{
		if (state == State.Int)
		{
			int num = Integer.parseInt(t);
			getToken();
			return new Label(num);
		}
		else
		{
			errorMsg(9);
			return null;
		}
	}

	public static StatementList statementList()
	// <s list> --> { <statement> } 
	{
		LinkedList<Statement> slist = new LinkedList<Statement>();
		while ( state == State.Id ||
				state == State.Keyword_if ||
				state == State.Keyword_switch || 
				state == State.Keyword_while ||
				state == State.Keyword_for ||
				state == State.Keyword_do ||
				state == State.Keyword_print ||
				state == State.LBrace)
		{
			Statement s = statement();
			slist.add(s);
		}
		
		
		return new StatementList(slist);
	}
	
	public static Assign assign()
	// <assign> --> <id> "=" <expr>
	{
		if (state == State.Id)
		{
			Id id = new Id(t);
			getToken();
			if (state == State.Assign)
			{
				getToken();
				Expr expr = expr();
				return new Assign(id,expr);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(2);
		return null;
	}

	public static E E()

	// <E> --> <term> { (+|-) <term> }

	{
		LinkedList<TermItem> termItemList = new LinkedList<TermItem>();

		Term term = term();
		termItemList.add(new SingleTermItem(term));
		while ( state == State.Plus | state == State.Minus )
		{
			State op = state;
			getToken();
			term = term();
			if ( op == State.Plus )
				termItemList.add(new AddTermItem(term));
			else // op == State.Minus
				termItemList.add(new SubTermItem(term));
		}
		return new E(termItemList);
	}

	public static Term term()

	// <term> --> <primary> { (*|/) <primary> }

	{
		LinkedList<PrimaryItem> primaryItemList = new LinkedList<PrimaryItem>();

		Primary primary = primary();
		primaryItemList.add(new SinglePrimaryItem(primary));
		while ( state == State.Times | state == State.Div )
		{
			State op = state;
			getToken();
			primary = primary();
			if ( op == State.Times )
				primaryItemList.add(new MulPrimaryItem(primary));
			else // op == State.Div
				primaryItemList.add(new DivPrimaryItem(primary));
		}
		return new Term(primaryItemList);
	}

	public static Primary primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")" | <bool literal> | <negative> | <negation>

	{
		switch ( state )
		{
			case Id:
										
				Id id = new Id(t);
				getToken();
				return id;
				
			case Int:

				Int intElem = new Int(Integer.parseInt(t));
				getToken();
				return intElem;

			case Float: case FloatE:

				Floatp floatElem = new Floatp(Float.parseFloat(t));
				getToken();
				return floatElem;

			case LParen:
				
				getToken();
				//E e = E(); changed e to expr and then changed parenthesized to take an expr instead of e
				Expr e = expr();
				if ( state == State.RParen )
				{
					getToken();
					Parenthesized paren = new Parenthesized(e);
					return paren;
				}
				else
				{
					errorMsg(1);
					return null;
				}

			case Keyword_true:
				
				getToken();
				return new TrueBoolLiteral();
				
			case Keyword_false:
				
				getToken();
				return new FalseBoolLiteral();
				
			case Minus:
				
				getToken();
				Primary negP = primary();
				return new Negative(negP);
				
			case Inv:
				
				getToken();
				Primary notP = primary();
				return new Negation(notP);
				
			default:

				errorMsg(2);
				return null;
		}
	}
	
	public static void errorMsg(int i)
	{
		errorFound = true;
		
		display(t + " : Syntax Error, unexpected symbol where");

		switch( i )
		{
		case 1:	displayln(" arith op or ) expected"); return;
		case 2: displayln(" id, int, float, or ( expected"); return;
		case 3:	displayln(" = expected"); return;
		case 4:	displayln(" ; expected"); return;
		case 5:	displayln(" id, conditional, loop, print, or block statement expected"); return;	
		case 6: displayln(" opening brace { expected"); return;
		case 7: displayln(" closing brace } expected"); return;
		case 8: displayln(" Keyword_case or Keyword_default expected"); return;
		case 9: displayln(" int label expected"); return;
		case 10: displayln(" : expected"); return;
		case 11: displayln(" Keyword_while expected"); return;
		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );
		setKeywords();

		getToken();

		Statement s = statement();
		if ( ! t.isEmpty() )
			errorMsg(5);
		else if ( ! errorFound )
			s.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

		closeIO();
	}
}