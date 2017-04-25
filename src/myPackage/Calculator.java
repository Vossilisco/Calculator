package myPackage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Rafael
 * @category Assignment #1 Calculator for arithmetic expressions
 * 
 */
public class Calculator {

	static char[] operators = {'+', '-', '*', '/', '^'};
	static ArrayList<String> myInput = new ArrayList<String>(10);
	static ArrayList<String> myOperands = new ArrayList<String>(10);
	static ArrayList<String> myOperators = new ArrayList<String>(10);
	static Scanner reader = new Scanner(System.in);
	static String input = "";
	
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
		for (char iterator : operators) {
			if(String.valueOf(iterator).equals(o)) return true;
		}
		return false;
	}
		
	/**
	 * Return true if the input String is a number.
	 * 
	 **/
	public static boolean isNumeric(String strin)  {  
	  try {  
	    double d = Double.parseDouble(strin);  
	  }  
	  catch(NumberFormatException nfe) {  
	    return false;  
	  }  
	  return true;  
	}
	
	/**
	 * Print all the input in Infix Form
	 * */
	public static void showInfixForm(){
		for (int i = 0; i < myOperands.size(); i++) {
			print(myOperands.get(i));
			if(i<= myOperators.size()-1) {
				print(" ");
				print(myOperators.get(i));
				print(" ");
			}
		}
	}

	/**
	 * Print all the input in Postfix Form
	 * */
	public static void showPostfixForm(){
		print(myOperands.get(0));
		print(" ");
		print(myOperands.get(1));	
		for (int i = 0; i < myOperators.size(); i++) {
			print(" ");
			print(myOperators.get(i));
			print(" ");
			if(i+2<= myOperands.size()-1) {
				print(myOperands.get(i+2));
			}
		}
	}
	
	public static void main(String[] args) {
		welcome();
		
		while(!input.equals(" ")){
			
			input = reader.nextLine();
			if(isNumeric(input) && myOperands.size()+1==myOperators.size()+1 ||
					isOperator(input)&& myOperands.size()>=myOperators.size()+1) {
				
				println("Se cumple\n");
				if(isNumeric(input)) myOperands.add(input); 
				else myOperators.add(input);
				
			// If the input data is not numeric, no operator and no space, so is an invalid input.	
			}else if(!input.equals(" ")){
				println("Wrong input data, expected: 'operand' 'operator' 'operand' .\n");
				System.exit(0);
			}
		}
		
		println("Infix form:");
		showInfixForm();
			
		println("\nPostfix form:");
		showPostfixForm();	
	}

}
