
public abstract class ConditionalStatement extends Statement
{

	// ⟨cond⟩ → "if" "(" ⟨expr⟩ ")" ⟨statement⟩ [ "else" ⟨statement⟩ ] 
	Expr expr;
	Statement s;
	
	
	void printParseTree(String indent)
	{		
		super.printParseTree(indent);
	}
}
