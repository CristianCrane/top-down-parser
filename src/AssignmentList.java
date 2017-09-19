import java.util.*;

class AssignmentList
{
	LinkedList<AssignStatement> assignmentList;

	AssignmentList(LinkedList<AssignStatement> al)
	{
		assignmentList = al;
	}

	void printParseTree(String indent)
	{
		for ( AssignStatement a : assignmentList )
			a.printParseTree(indent);
	}

//	void M(HashMap<String,Val> state) // function to interpret this AssignStatement list
//	{
//		for ( AssignStatement a : assignmentList )
//			a.M(state);
//	}
//
//	void emitInstructions()
//	{
//		for ( AssignStatement a : assignmentList )
//			a.emitInstructions();
//	}
}