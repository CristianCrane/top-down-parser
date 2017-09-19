// <boolPrimary> --> <E> [ <rel op> <E> ] 

public abstract class BoolPrimary 
{
	E e;
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <bool primary>");
	}
}
