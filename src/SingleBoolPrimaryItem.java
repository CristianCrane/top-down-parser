
public class SingleBoolPrimaryItem extends BoolPrimaryItem
{
	// BoolPrimary bprimary; inherited from PrimaryItem

	SingleBoolPrimaryItem(BoolPrimary p)
	{
		bprimary = p;
	}

	void printParseTree(String indent)
	{
		bprimary.printParseTree(indent);
	}
}
