// <while loop> --> "while" "(" <expr> ")" <statement> 
public class WhileStatement extends Statement
{
	Expr expr;
	Statement stmt;
	
	WhileStatement(Expr e, Statement s)
	{
		expr = e;
		stmt = s;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		String indent2 = indent1 + " ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <while loop>");
		IO.displayln(indent2 + indent2.length() + " while");
		expr.printParseTree(indent2);
		stmt.printParseTree(indent2);
		
	}
}
