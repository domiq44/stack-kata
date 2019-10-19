package fr.domiq.stack;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class StackTest {

	@InjectMocks
	private Stack<Integer> stack;

	@Mock
	private PrintStream printStream;

	@Before
	public void initialize() {
		initMocks(this);
		System.setErr(printStream);
	}

	@Test
	public void should_store_one_element() throws Exception {
		stack.push(101);
		assertThat(stack.size(), equalTo(1));
	}

	@Test
	public void should_store_many_elements() throws Exception {
		stack.push(101);
		stack.push(102);
		assertThat(stack.size(), equalTo(2));
	}

	@Test
	public void should_read_and_delete_last_element() throws Exception {
		stack.push(101);
		stack.push(102);
		stack.push(103);
		Integer element = stack.pop();
		assertThat(stack.size(), equalTo(2));
		assertThat(element, equalTo(103));
	}

	@Test
	public void should_read_last_element() throws Exception {
		stack.push(101);
		stack.push(102);
		stack.push(103);
		Integer element = stack.peek();
		assertThat(stack.size(), equalTo(3));
		assertThat(element, equalTo(103));
	}

	@Test
	public void should_return_true_when_stack_is_empty() throws Exception {
		assertThat(stack.isEmpty(), equalTo(true));
	}

	@Test
	public void should_return_false_when_stack_is_not_empty() throws Exception {
		stack.push(101);
		assertThat(stack.isEmpty(), equalTo(false));
	}

	@Test
	public void should_not_authorize_store_one_element_when_stack_is_full() throws Exception {
		stack.push(101);
		stack.push(102);
		stack.push(103);
		stack.push(104);
		stack.push(105);
		stack.push(106);
		verify(printStream, times(1)).println("Stack is full");
	}

	@Test
	public void should_not_authorize_read_and_delete_last_element_when_stack_is_empty() throws Exception {
		stack.pop();
		verify(printStream, times(1)).println("Stack is empty");
	}

	@Test
	public void should_not_authorize_read_last_element_when_stack_is_empty() throws Exception {
		stack.peek();
		verify(printStream, times(1)).println("Stack is empty");
	}
}
