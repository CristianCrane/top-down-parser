
public class IfStatement extends ConditionalStatement
{
	
	// ⟨cond⟩ → "if" "(" ⟨expr⟩ ")" ⟨statement⟩
	
	// Expr expr from ConditionalStatement
	// Statement s from ConditionalStatement
	
	IfStatement(Expr e, Statement stmt)
	{
		expr = e;
		s = stmt;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		String indent2 = indent1 + " ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <cond>");
		IO.displayln(indent2 + indent2.length() + " if");
		expr.printParseTree(indent2);
		s.printParseTree(indent2);
	}
	
}
