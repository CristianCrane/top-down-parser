// <assign> --> <id> "=" <expr>
public class Assign 
{
	Id id;
	Expr expr;
	
	Assign(Id name, Expr e)
	{
		id = name;
		expr = e;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <assign>");
		id.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " =");
		expr.printParseTree(indent1);
	}
}
