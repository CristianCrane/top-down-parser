
public abstract class Statement {

	// ⟨statement⟩ → ⟨assignment⟩ | ⟨cond⟩ | ⟨switch⟩ | ⟨while loop⟩ | ⟨do loop⟩ | ⟨for loop⟩ | ⟨print⟩ | ⟨block⟩ 

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <statement>");
	}
}
