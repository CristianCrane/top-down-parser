// <print> --> "print" <expr> ";" 
public class PrintStatement extends Statement
{
	Expr expr;
	
	PrintStatement(Expr e)
	{
		expr = e;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		String indent2 = indent1 + " ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <print>");
		IO.displayln(indent2 + indent2.length() + " print");
		expr.printParseTree(indent2);
	}
}
