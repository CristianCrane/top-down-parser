// <block> --> "{" <s list> "}" 
public class BlockStatement extends Statement
{
	StatementList slist;
	
	BlockStatement(StatementList list)
	{
		slist = list;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		String indent2 = indent1 + " ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <block>");
		slist.printParseTree(indent2);
	}
}
