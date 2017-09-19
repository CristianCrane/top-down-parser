import java.util.LinkedList;

public class BoolTerm
{

	LinkedList<BoolPrimaryItem> primaryItemList;

	BoolTerm(LinkedList<BoolPrimaryItem> pItemList)
	{
		primaryItemList = pItemList;
	}
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <bool term>");
		for ( BoolPrimaryItem p : primaryItemList )
			p.printParseTree(indent+" ");
	}

}
