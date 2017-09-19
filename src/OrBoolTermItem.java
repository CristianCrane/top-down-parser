// represents "||" <boolTerm>
public class OrBoolTermItem extends BoolTermItem
{
	OrBoolTermItem(BoolTerm bt)
	{
		bterm = bt;
	}

	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " ||");
		bterm.printParseTree(indent);
	}
}
