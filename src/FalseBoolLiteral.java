import java.util.HashMap;

public class FalseBoolLiteral extends BoolLiteral 
{

	void printParseTree(String indent)
	{
		String indent2 = indent + "  ";
		
		super.printParseTree(indent);
		IO.displayln(indent2 + indent2.length() + " false");
	}
	
	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
	}

	
}
