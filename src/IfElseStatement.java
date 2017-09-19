
public class IfElseStatement extends ConditionalStatement 
{

	// <conditionalStatement> --> if ( <expr> ) <statement> else <statement2>
	
		// Expr expr inherited from ConditionalStatement
		// Statement s inherited from ConditionalStatement
		Statement s2;
		
		IfElseStatement(Expr e, Statement stmt, Statement stmt2)
		{
			expr = e;
			s = stmt;
			s2 = stmt2;
		}
		
		void printParseTree(String indent)
		{
			String indent1 = indent + " ";
			String indent2 = indent1 + " ";
			
			super.printParseTree(indent);
			
			IO.displayln(indent1 + indent1.length() + " <cond>");
			
			IO.displayln(indent2 + indent2.length() + " if");
			expr.printParseTree(indent2);
			s.printParseTree(indent2);
			IO.displayln(indent2 + indent2.length() + " else");
			s2.printParseTree(indent2);

		}
}
