import java.util.LinkedList;

// <case list> --> { <case> }+ 
public class CaseList {

	LinkedList<SwitchCase> cases;
	
	public CaseList(LinkedList<SwitchCase> cList)
	{
		cases = cList;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <case list>");
		
		for (SwitchCase c : cases)
		{
			c.printParseTree(indent + " ");
		}
	}
}
