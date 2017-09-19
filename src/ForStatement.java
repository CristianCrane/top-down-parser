// <for loop> --> "for" "(" <assign> ";" <expr> ";" <assign> ")" <statement> 
public class ForStatement extends Statement
{
	Assign init;
	Expr expr;
	Assign incr;
	Statement stmt;
	
	ForStatement(Assign initialize, Expr e, Assign increment, Statement s)
	{
		init = initialize;
		expr = e;
		incr = increment;
		stmt = s;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		String indent2 = indent1 + " ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <for loop>");
		IO.displayln(indent2 + indent2.length() + " for");
		init.printParseTree(indent2);
		expr.printParseTree(indent2);
		incr.printParseTree(indent2);
		stmt.printParseTree(indent2);
	}
}
