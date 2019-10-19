package fr.domiq.stack;

import java.util.Arrays;

public class Stack<T> {

	private static final int DEFAULT_SIZE = 5;

	private T[] elements;
	private int top;
	private int size;

	public Stack() {
		this(DEFAULT_SIZE);
	}

	public Stack(int size) {
		this.size = size;
		this.elements = newArray(size);
		this.top = 0;
	}

	public int size() {
		return top;
	}

	public boolean isEmpty() {
		return top == 0;
	}

	public void push(T element) {
		if (top == size) {
			System.err.println("Stack is full");
			return;
		}

		elements[top] = element;
		top++;
	}

	public T pop() {
		if (top == 0) {
			System.err.println("Stack is empty");
			return null;
		}

		top--;
		T element = elements[top];
		elements[top] = null;
		return element;
	}

	public T peek() {
		if (top == 0) {
			System.err.println("Stack is empty");
			return null;
		}

		return elements[top - 1];
	}

	@SafeVarargs
	private static <T> T[] newArray(int length, T... array) {
		return Arrays.copyOf(array, length);
	}
}
