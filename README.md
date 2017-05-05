# Calculator in Java
 1º Assigment for the course Algorithms and Data structures.

Assignment 1: Calculator for arithmetic expressions (15 points)

The task is to implement a program that calculates the value of an arithmetic
expression that is entered by the user. The user interface is irrelevant: it can be commandline,
or a desktop graphical interface, or a web interface. The implementation of a fancy UI
does not substitute for mistakes in implementing the main functionality.
The expression consists of numbers (integers or floating point) and arithmetic
operators (+, -, *, /, ^). The expressions are given in the infix notation. The program should
correctly calculate the result and display it. It should also detect syntax errors in the
expression if they exist.

This assignment is an exercise in using stacks, both for conversion of expressions
from infix to postfix form, and for calculating the value of expressions from the postfix form.

***

# How to run it?

Compile and execute UsingCalculator.java. 
Type one number press Enter, type one operator press Enter and so on. Type `e` when finished and it will display the infix form, the postfix form and the solution.


If you want to calculate for example:  `10 + 2 * 4.5`

Type 10  press Enter,
Type +   press Enter,
Type 2   press Enter,
Type *   press Enter,
Type 4.5 press Enter,
Type e   press Enter.


> Infix form: 10 + 2 * 4.5
> Postfix form: 10 2 4.5 * +
> The solution is: 19.0