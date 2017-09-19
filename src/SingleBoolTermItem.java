// first term in Expr
public class SingleBoolTermItem extends BoolTermItem
{

	SingleBoolTermItem(BoolTerm bt)
	{
		bterm = bt;
	}

	void printParseTree(String indent)
	{
		bterm.printParseTree(indent);
	}

}
