This is the second step of building a programming language; the top down parser. The main runnable class is Parser.java. The other classes define all the symbols and structures of the sample language.

Program input: Stream of text/symbols/tokens that make up your program in a txtfile.

Program output: Linearly indented tree structure representing the parse tree of the given program.

This program extends the lexical analyzer and now builds a parse tree of the statements in the program. Parse trees are imperative as they allow to represent the entire syntax of the program in a single data structure.

The beauty of this is that the parse tree can be implicitly built by simply utilizing nested classes. The whole program can be thought of as a single statement, which contains other statements nested inside it, which in turn has other statements nested inside that. The point is, the entire parse tree can be recursively built and read by simply creating class for each structure of the language.

With the parse tree now built, we can move on to the next step: the intermediate code compiler.
