package myPackage;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

	private final String operators = "+-*/^()";
	private ArrayList<Character> operatorList;
	private ArrayList<Double> operandList;

	/**
	 * Default constructor
	 * */
	public Calculator(){
		this.operatorList = new ArrayList<Character>();
		this.operandList = new ArrayList<Double>();
		welcome();
	}

	/**
	 * Exactly the same as System.out.println but shorter
	 * */
	private void println(String s){
		System.out.println(s);
	}

	/**
	 * Exactly the same as System.out.print but shorter
	 * */
	private void print(String s){
		System.out.print(s);
	}

	/**
	 * Print the given error message and force the end of the program.
	 * @param errorMessage String: Containts the error message that will be printed.
	 * 
	 * */
	private void exitBecauseError(String errorMessage){
		System.err.println("ERROR -> " + errorMessage);
		System.exit(1);
	}
	
	/**
	 * Just a hello message from calculator.
	 */
	private void welcome(){
		println("------------");
		println("-CALCULATOR-");
		println("------------");
		println("\n");
		println("Please, enter one by one each operand pressing ENTER.");
		println("Calculator will finish when the input is 'e'.");
		println("Ready to start:");
		println("\n");
	}

	/**
	 * Return true if the input String is a valid operator.
	 * 
	 **/
	private boolean isOperator(String o){

		return operators.contains(o);
	}

	/**
	 * Return true if the input String is a number.
	 * 
	 **/
	private boolean isNumeric(String strin)  {  
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
	private boolean isNumeric(char strin)  {  
		return Character.isDigit(strin) || strin=='.'; 
	}

	/**
	 * Return the priority of the given operator.
	 * @param op Char with the valid operator.
	 * @return from 1 to 3, the priority of the given operator.
	 **/
	private int getPriority(char op) {
		switch (op) {
		case '+':
		case '-':
			return 1;

		case '/':
		case '*':
			return 2;

		case '^':
			return 3;
		case '(':
		case ')':
			return 0;

		default:
			//It will never reach this
			return -1;
		}
	}

	/**
	 * It calculates the mathematical operation with the input parameters
	 * @param op char: A valid operation
	 * @param value1 double: First operand
	 * @param value2  double: Second operand
	 * */
	private double calculate(char op, double value1, double value2){
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
	 * Convert a String math expression in infix to Postfix form.
	 * @param infix String with the infix expression
	 * @return String with the postfix expression
	 **/
	public String infixToPostfix(String infix) {
		Stack<Character> stack = new Stack<>();
		StringBuilder postFix = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		for (int i = 0; i < infix.length(); i++) {
			//Reset auxiliary variable
			temp.delete(0, temp.length());

			//Fill with the full operand/operator
			while(infix.charAt(i)!= ' '){
				temp.append(infix.charAt(i));
				i++;
			}

			//If char is an operand add to output
			if (isNumeric(temp.toString())) {		
				postFix.append(temp.toString());
				postFix.append(" ");

				//If it is an operator
			}else{

				char operator = temp.charAt(0);

				//If it is a left parenthesis we add it and it will mark like a set point in the expression because mathematical priorities reasons
				if (operator == '('){	

					stack.push(operator);

					//Before adding it to the stack, it has to be sorted and respecting the priorities letting the 
				}else if(getPriority(operator) > 0){

					//We output all the operators who has more or the same priority that found
					while (!stack.isEmpty() && getPriority(operator) <= getPriority(stack.peek())) {

						postFix.append(stack.pop());
						postFix.append(" ");

					}
					//When others operators are out, we save our with less priority
					stack.push(operator);

					//If it is an right parenthesis we must output all the operators found since the left parenthesis
				}else if (operator == ')'){

					if(stack.empty()){

						exitBecauseError("Problem with parenthesis, bad sintaxis.");

						//If the first operator is the left parenthesis it just deletes it
					}else if(stack.peek()=='('){

						stack.pop();

					}else{
						//If not, it just output the operators until find the left parenthesis
						while(!stack.empty() && stack.peek()!='('){
							postFix.append(stack.pop());
							postFix.append(" ");
						}
						if(stack.empty()){

							exitBecauseError("Problem with parenthesis, bad sintaxis.");

							//Left parenthesis found so now, it deletes it.
						}else if(stack.peek().equals('(')){

							stack.pop();

						}
					}
				}
			}
		}
		while (!stack.isEmpty()) {
			postFix.append(stack.pop());
			postFix.append(" ");
		}

		return postFix.toString();
	}

	/**
	 * Check if the input String is using parenthesis properly or not.
	 * @param str String with the mathematical expression to check
	 * @return true if it is right, false if there is something wrong
	 **/
	private boolean parenthesisCheck(String str)  {
		Stack<Character> stack = new Stack<Character>();
		char c;

		for(int i=0; i < str.length(); i++) {
			c = str.charAt(i);

			if(c == '(')
				stack.push(c);
			else if(c == ')')
				if(stack.empty())
					return false;
				else if(stack.peek() == '(')
					stack.pop();
				else
					return false;
		}
		return stack.empty();
	}

	/**
	 * Ask the user for the math expression on infix form.
	 * @return String that contains a infix expression
	 * */
	public String askForExpression(){
		StringBuilder sb = new StringBuilder();
		Scanner reader = new Scanner(System.in);
		String input = "";

		while(!input.equals("e")){
			input = reader.nextLine();


			if(isOperator(input)){

				sb.append(input);
				
				if(!input.equals("(") && !input.equals(")")) {
					
					try{
						operatorList.add(input.charAt(0));
					}catch(StringIndexOutOfBoundsException s){
						
						exitBecauseError("No input. Please type only numbers and valid operators: +-*/^() ");
					}
				}
				sb.append(" ");

			}else if(isNumeric(input)){

				sb.append(input);

				operandList.add(Double.parseDouble(input));

				sb.append(" ");	

			}else if (!input.equals("e")){

				exitBecauseError("Unknown input. Please type only numbers and valid operators: +-*/^() ");
			}
		}
		reader.close();

		if(!parenthesisCheck(sb.toString())){
			exitBecauseError("Invalid use of parenthesis.");
			return "";
			
		}else if (operandList.size() > operatorList.size()+1){
			exitBecauseError("Invalid sintaxis. The expression: " + sb.toString() + " has no mathematical sense.");
			return "";
		}else{
			return sb.toString();
		}

	}

	/**
	 * It calculates the result of a PostFix given expression.
	 * @param postfix String with the mathematical expression in PostFix form.
	 * @return double data with the solution.
	 * */
	public double evaluatePostfix(String postfix){
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

				try{
					pila.push(calculate(temp.charAt(0), pila.pop(), pila.pop()));

				}catch(EmptyStackException e){

					exitBecauseError("Problem with operators.");
					System.exit(1);
				}
			}
		}
		return pila.pop();
	}

}