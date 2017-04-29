package myPackage;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Rafael
 * @category Assignment #1 Calculator for arithmetic expressions
 * 
 */
public class Calculator {

	static String operators = "+-*/^";
	static ArrayList<Character> operatorList = new ArrayList<Character>();
	static ArrayList<Double> operandList = new ArrayList<Double>();
	
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
		  try  {  
			  double d = Double.parseDouble(strin); 
			  
		  } catch(NumberFormatException nfe){  
			  return false;  
		  }  
		  return true;  
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
	 * @param op Char with the validd operator.
	 * @return from 1 to 3, the priority of the given operator.
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
		StringBuilder temp = new StringBuilder();

		for (int i = 0; i < infix.length(); i++) {
			// If char is an operand or one digit of a double number then add to output
			temp.delete(0, temp.length());
			
			while(infix.charAt(i)!= ' '){
				temp.append(infix.charAt(i));
				i++;
			}
			
			if (isNumeric(temp.toString())) {			
				postFix.append(temp.toString());
				postFix.append(" ");
				
			} else {
				char operator = temp.charAt(0);
				while (!stack.isEmpty() && getPriority(operator) <= getPriority(stack.peek())) {
					postFix.append(stack.pop());
					postFix.append(" ");
				}
				stack.push(operator);
			}
		}

		while (!stack.isEmpty()) {
			postFix.append(stack.pop());
			postFix.append(" ");
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
			
			if(isOperator(input) && operatorList.size()+1==operandList.size()){
				sb.append(input);
				operatorList.add(input.charAt(0));
				sb.append(" ");
			}else if(isNumeric(input) && operatorList.size()==operandList.size()){
				sb.append(input);
				operandList.add(Double.parseDouble(input));
				sb.append(" ");
			}else if (!input.equals("e")){
				print("ERROR: input should enter number, operator and number. Introduce 'e' when finish.");
				System.exit(0);
			}
		}
		reader.close();
		return sb.toString();
	}
	
	/**
	 * It calculates the mathematical operation with the input parameters
	 * @param op Char that means a valid operation
	 * @param value1 First operand
	 * @param value2 Second operand
	 * */
	public static double calculate(char op, double value1, double value2){
		switch (op) {
		case '+':
			return value1+value2;

		case '-':
			return value2-value1;
			
		case '*':
			return value2*value1;
			
		case '/':
			return value2/value1;
		
		case '^':
			return Math.pow(value2,value1);
			
		default:
			return 0;
		}
	}
	/**
	 * It calculates the result of a PostFix given expression.
	 * @param postfix String with the mathematical expression in PostFix form.
	 * @return Double data with the solution.
	 * */
	public static double evaluatePostfix(String postfix){
		Stack<Double> pila = new Stack<Double>();
		StringBuilder temp = new StringBuilder();
		
		for (int i=0; i< postfix.length(); i++){
			temp.delete(0, temp.length());
			
			while(postfix.charAt(i)!= ' '){
				temp.append(postfix.charAt(i));
				i++;
			}
			
			//If is a number, we push it into the stack
			if(isNumeric(temp.toString())){
				pila.push(Double.valueOf(temp.toString()));
		
			//If is an operator, we operate with it
			}else{
				
				pila.push(calculate(temp.charAt(0), pila.pop(), pila.pop()));
				
			}
		}
		
		return pila.pop();
	}

	public static void main(String[] args){
		welcome();
		
		String infix = askForExpression();
		println(infix);
		String postfix = infixToPostfix(infix);
		println(postfix);
		Double end = evaluatePostfix(postfix);
		println(end.toString());
	
	}
}
