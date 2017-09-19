// > <boolprimary>
public class GtBoolPrimary extends CompareBoolPrimary
{
	GtBoolPrimary(E exp1, E exp2)
	{
		e = exp1;
		e2 = exp2;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		
		IO.displayln(indent + indent.length() + " <GtBoolPrimary>");
		e.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " >");
		e2.printParseTree(indent1);
	}
}
