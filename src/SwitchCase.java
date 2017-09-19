// <case> --> "case" <label> ":" <s list> | "default" ":" <s list> 

public abstract class SwitchCase 
{
	StatementList sList;
	
	abstract void printParseTree(String indent);
}
