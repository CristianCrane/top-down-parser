
public class SingleBoolPrimary extends BoolPrimary
{
	SingleBoolPrimary(E exp1)
	{
		e = exp1;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		
		super.printParseTree(indent);
		//IO.displayln(indent + indent.length() + " <SingleBoolPrimary>");
		e.printParseTree(indent1);

	}
}
