import java.util.*;

abstract class Primary
{
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <primary>");
	}

	abstract Val Eval(HashMap<String,Val> state);
	abstract void emitInstructions();
}