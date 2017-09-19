
public class DefaultCase extends SwitchCase
{
	// StatementList sList from SwitchCase
	public DefaultCase(StatementList list)
	{
		sList = list;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " Default");
		sList.printParseTree(indent + " ");	
	}
}
