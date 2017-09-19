import java.util.LinkedList;

// <expr> --> <boolTerm> { "||" <boolTerm> } 
public class Expr {

	LinkedList<BoolTermItem> boolTermItemList;

	Expr(LinkedList<BoolTermItem> btItemList)
	{
		boolTermItemList = btItemList;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <Expr>");
		for ( BoolTermItem t : boolTermItemList )
			t.printParseTree(indent+" ");
	}
}
