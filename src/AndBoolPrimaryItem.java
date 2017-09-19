// represents && <BoolPrimary>
public class AndBoolPrimaryItem extends BoolPrimaryItem
{
	// BoolPrimary bprimary; inherited from PrimaryItem

	AndBoolPrimaryItem(BoolPrimary p)
	{
		bprimary = p;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " &&");
		bprimary.printParseTree(indent);
	}
}
