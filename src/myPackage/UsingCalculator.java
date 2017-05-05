/**
 * @author Rafael
 * @category Assignment #1 Calculator for arithmetic expressions
 * 
 */

package myPackage;

public class UsingCalculator {

	private static Calculator calculadora = new Calculator();
	
	public static void main(String[] args) {
		
		String infix = calculadora.askForExpression();
		System.out.println("Infix form: " + infix);
		
		String postfix = calculadora.infixToPostfix(infix);
		System.out.println("Postfix form: " + postfix);
		
		Double solution = calculadora.evaluatePostfix(postfix);
		System.out.println("The solution is: " + solution.toString());
		
	}

}
