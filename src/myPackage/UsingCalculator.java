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
		String postfix = calculadora.infixToPostfix(infix);
		Double solution = calculadora.evaluatePostfix(postfix);
		
		System.out.println("Infix form: " + infix);
		System.out.println("Postfix form: " + postfix);
		System.out.println("The solution is: " + solution.toString());
		
	}
}
