import java.util.*;

class Parenthesized extends Primary
{
	Expr e;

	Parenthesized(Expr exp)
	{
		e = exp;
	}

	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		//IO.displayln("");
		e.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)
	{
		//return e.Eval(state);
		return null;
	}
	
	void emitInstructions()
	{
		//e.emitInstructions();
	}
}