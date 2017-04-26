package myPackage;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Rafael
 * @category Assignment #1 Calculator for arithmetic expressions
 * 
 */
public class Calculator {

	static String operators = "+-*/^";
	
	/**
	 * Exactly the as System.out.println but shorter
	 * */
	public static void println(String s){
		System.out.println(s);
	}
		
	/**
	 * Exactly the as System.out.print but shorter
	 * */
	public static void print(String s){
		System.out.print(s);
	}
	
	/**
	 * Just a hello message from calculator.
	 */
	public static void welcome(){
		println("------------");
		println("-CALCULATOR-");
		println("------------");
		println("\n");
		println("Please, enter one by one each operand pressing ENTER.");
		println("Calculator will finish when the input is SPACE.");
		println("Ready to start:");
		println("\n");
	}
	
	/**
	 * Return true if the input String is a valid operator.
	 * 
	 **/
	public static boolean isOperator(String o){
		
		return operators.contains(o);
	}
		
	/**
	 * Return true if the input String is a number.
	 * 
	 **/
	public static boolean isNumeric(String strin)  {  
		return Character.isDigit(strin.charAt(0)) || strin.charAt(0)=='.'; 
	}
	/**
	 * Return true if the input char is a number.
	 * 
	 **/
	public static boolean isNumeric(char strin)  {  
		return Character.isDigit(strin) || strin=='.'; 
	}
	
	/**
	 * Return the priority of the given operator.
	 * 
	 **/
	public static int getPriority(char op) {
		switch (op) {
		case '+':
		case '-':
			return 1;

		case '/':
		case '*':
			return 2;

		case '^':
			return 3;

		default:
			//It will never reach this
			return -1;
		}
	}

	/**
	 * Convert a String math expression in infix to Postfix form.
	 * @param String with the infix expression
	 * @return String with the postfix expression
	 **/
	public static String infixToPostfix(String infix) {
		Stack<Character> stack = new Stack<>();
		StringBuilder postFix = new StringBuilder();

		for (int i = 0; i < infix.length(); i++) {
			// If char is an operand or one digit of a double number then add to output
			if (isNumeric(infix.charAt(i)) ) {			
				postFix.append(infix.charAt(i));
				
			} else {
				char operator = infix.charAt(i);
				while (!stack.isEmpty() && getPriority(operator) <= getPriority(stack.peek())) {
					postFix.append(stack.pop());
				}
				stack.push(operator);
			}
		}

		while (!stack.isEmpty()) {
			postFix.append(stack.pop());
		}

		return postFix.toString();
	}

	/**
	 * Ask the user for the math expresion on infix form.
	 * @return String that contains a infix expression
	 * */
	public static String askForExpression(){
		StringBuilder sb = new StringBuilder();
		Scanner reader = new Scanner(System.in);
		String input = "";
		
		while(!input.equals("e")){
			input = reader.nextLine();
			
			if(input.length()==1 && isOperator(input) || isNumeric(input)){
				sb.append(input);
			}else if(!input.equals("e")){
				print("ERROR: input should enter digit by digit. Introduce 'e' when finish.");
				System.exit(0);
			}
		}
		reader.close();
		return sb.toString();
	}
	
	
	public static void main(String[] args){
		welcome();
		
		String infix = askForExpression();
		println(infix);
		String postfix = infixToPostfix(infix);
		println(postfix);
		
	
	}

}
