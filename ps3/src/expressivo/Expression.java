/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionParser;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    //   TODO
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
	public static Expression parse(String input) {
	    // Step 1: Create a CharStream from the input string using ANTLRInputStream
	    CharStream stream = new ANTLRInputStream(input);  // Use ANTLRInputStream if CharStreams is unavailable

	    // Step 2: Instantiate the lexer using the CharStream
	    ExpressionLexer lexer = new ExpressionLexer(stream);
	    lexer.reportErrorsAsExceptions(); // Throw exceptions for lexer errors

	    // Step 3: Tokenize the input and create a TokenStream
	    TokenStream tokens = new CommonTokenStream(lexer);

	    // Step 4: Instantiate the parser using the TokenStream
	    ExpressionParser parser = new ExpressionParser(tokens);
	    parser.reportErrorsAsExceptions(); // Throw exceptions for parser errors

	    // Step 5: Parse the expression and get the parse tree
	    ParseTree tree = parser.root();  // Assuming 'root' is the starting rule in your grammar

	    // Step 6: Create an ExpressionMaker to walk the parse tree
	    ExpressionMaker maker = new ExpressionMaker();

	    // Step 7: Walk through the parse tree to build the Expression object
	    new ParseTreeWalker().walk(maker, tree);

	    // Step 8: Return the built Expression object from the ExpressionMaker
	    return maker.getExpression();
	}
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();

	void checkRep();
    
    // TODO more instance methods
	
	 public Expression differentiate(String variable);

	    /**
	     * Simplify an expression.
	     * @param environment maps variables to values. Variables are required to be case-sensitive nonempty 
	     *         strings of letters. The set of variables in environment is allowed to be different than the 
	     *         set of variables actually found in expression. Values must be nonnegative numbers.
	     * @return an expression equal to the input, but after substituting every variable v that appears in both
	     *         the expression and the environment with its value, environment.get(v). If there are no
	     *         variables left in this expression after substitution, it's evaluated to a single number.
	     */
	 public Expression simplify(Map<String, Double> environment);
    
}
