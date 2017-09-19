// <do loop> --> "do" <statement> "while" "(" <expr> ")" ";" 
public class DoWhileStatement extends Statement
{
	Expr expr;
	Statement stmt;
	
	DoWhileStatement(Expr e, Statement s)
	{
		expr = e;
		stmt = s;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		String indent2 = indent1 + " ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <do loop>");
		IO.displayln(indent2 + indent2.length() + " do");
		stmt.printParseTree(indent2);
		IO.displayln(indent2 + indent2.length() + " while");
		expr.printParseTree(indent2);
	}
}
