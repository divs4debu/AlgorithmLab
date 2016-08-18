import java.util.*;
import java.util.List;
import java.util.Arrays;

/**
 * @author Divya Prakash Varshney
 * This is the stack class helps in managing the operator in the infix string.
 */

class  Stack<E> {

	List<E> stack;
	int size;
	Stack() {
		stack = new ArrayList<E>();
		size = 0;
	}
	public void push(E element) {
		stack.add(element);
		size++;

	}

	public E pop() {
		if (size > 0)///////////////////////////////////////////
			return stack.remove(--size);
		else
			return null;////////////////////////////////////////////

	}

	public int getSize() {
		return size;
	}
	public E peek() {
		return stack.get(size - 1);
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void print() {//////////////////////////////////////////////////////
		for (int i = 0; i < stack.size(); i++) {
			System.out.print(stack.get(i));
		}
	}
}


class Evaluate {

	String infixEquation;
	List<String> expr = new ArrayList<String>();
	Precedence p = Precedence.INITIATE;

	public enum Precedence {
		INITIATE (-1),
		ADDITION (1),
		SUBTRACTION (1),
		MULTIPLICATION (2),
		DIVISION (2),
		EXPONENT (3),
		PARENTHESIS (0);

		private final int precedence;

		Precedence(int prec) {
			this.precedence = prec;
		}
		int knowPrecedence() {
			return precedence;
		}
	}
	Evaluate() {
	}
	Evaluate(String s) {
		loadEquation(s);
	}

	private String removeSpaces(String s) {
		return s.replaceAll("\\s+", "");
	}

	public void loadEquation(String s) {
		s = removeSpaces(s);
		expr = new ArrayList<String>();
		String tmp = "";
		for (char c : s.toCharArray()) {
			String str = Character.toString(c);
			if (isOperator(str)) {
				if (!tmp.equals(""))
					expr.add(tmp);
				expr.add(str);
				tmp = "";
			} else {
				tmp += str;
			}
		}
		if (!tmp.equals(""))
			expr.add(tmp);
	}

	public String generatePostfix() {

		String outputEquation = "";
		Stack<String> stack = new Stack<String>();
		stack.push("(");
		for (int i = 0; i < expr.size(); i++) {
			String el = expr.get(i);

			if (isOperand(el)) {
				outputEquation += el + " ";
				printStyle(stack,el,outputEquation);
			} else if (el.equals("(")) {
				stack.push(el);
				printStyle(stack,el,outputEquation);
			} else if (el.equals(")")) {
				while (!stack.peek().equals("(")) {
					outputEquation += stack.pop() + " ";
					printStyle(stack,el,outputEquation);
				}
				stack.pop();
			} else if (isOperator(el)) {
				while (!stack.isEmpty() && getPrecedence(el) <= getPrecedence(stack.peek())) {
					outputEquation += stack.pop() + " ";
					printStyle(stack,el,outputEquation);
				}
				stack.push(el);
				printStyle(stack,el,outputEquation);
			}
			//printStyle(stack,el,outputEquation);
		}

		while (!stack.isEmpty()) {
			String expr = stack.pop();
			if (!expr.equals("("))
				outputEquation += expr + " ";
		}

		return outputEquation;
	}

	private boolean isOperator(String s) {
		return (getPrecedence(s) != -1);
	}

	private boolean isOperand(String s) {
		return !isOperator(s);
	}

	public int getPrecedence(String operator) {
		if (operator.equals("^"))
			return Precedence.EXPONENT.knowPrecedence();
		if (operator.equals("+"))
			return Precedence.ADDITION.knowPrecedence();
		if (operator.equals("-"))
			return Precedence.SUBTRACTION.knowPrecedence();
		if (operator.equals( "*"))
			return Precedence.MULTIPLICATION.knowPrecedence();
		if (operator.equals( "/"))
			return Precedence.DIVISION.knowPrecedence();
		if (operator.equals("("))
			return Precedence.PARENTHESIS.knowPrecedence();
		if (operator.equals(")"))
			return Precedence.PARENTHESIS.knowPrecedence();
		return -1;
	}

	private void printStyle(Stack s,String opr,String out) {

		System.out.print("\n" + opr + "\t\t"); //////////////////////////////////////////////////
		s.print();/////////////
		System.out.println("\t\t" + out); //////////////
	}


}




class Main {

	public static void main(String args[]) {
		Evaluate e = new Evaluate();
		System.out.println("Enter the infix expression");
		e.loadEquation(new Scanner(System.in).nextLine());
		System.out.println("Postfix\t :"+e.generatePostfix());


	}
}

