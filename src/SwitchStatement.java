// <switch> --> "switch" "(" <expr> ")" "{" <case list> "}" 

public class SwitchStatement extends Statement
{
	Expr expr;
	CaseList cList;
	
	public SwitchStatement(Expr e, CaseList c)
	{
		expr = e;
		cList = c;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		String indent2 = indent1 + " ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <switch>");
		IO.displayln(indent2 + indent2.length() + " switch");
		expr.printParseTree(indent2);
		cList.printParseTree(indent2);		
	}
}
