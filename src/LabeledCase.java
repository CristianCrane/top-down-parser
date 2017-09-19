
public class LabeledCase extends SwitchCase
{
	// StatementList sList; (from SwitchCase)
	Label label;
	
	public LabeledCase(Label l, StatementList li)
	{
		label = l;
		sList = li;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " case : " + label.getLabel());
		sList.printParseTree(indent + " ");	
	}
}
