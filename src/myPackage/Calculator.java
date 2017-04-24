/**
 * 
 */
package myPackage;

import java.util.ArrayList;

/**
 * @author Rafael
 * @category Assignment #1
 * 
 */
public class Calculator {

	char[] operators = {'+' , '-', '*','/', '^'};
	
	public static void welcome(){
		System.out.println("------------");
		System.out.println("-CALCULATOR-");
		System.out.println("------------");
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		welcome();
		System.out.println("Please, enter one by one each operand pressing ENTER.");
		System.out.println("Calculator will finish when the input is SPACE.");
		System.out.println("\n");

	}

}
