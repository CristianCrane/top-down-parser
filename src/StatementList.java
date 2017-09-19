import java.util.LinkedList;

// <s list> --> { <statement> } 
public class StatementList 
{
	LinkedList<Statement> list;
	
	public StatementList(LinkedList<Statement> sList)
	{
		list = sList;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <s list>");
		
		for (Statement s : list)
		{
			s.printParseTree(indent + " ");
		}
	}
	
}
