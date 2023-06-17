package uy.edu.um.adt.stack;

import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import org.junit.Test;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.stack.EmptyStackException;
import uy.edu.um.prog2.adt.stack.MyStack;

public class StackImplTest {

	@Test
	public void testFlujoCompleto() {
		MyStack<Integer> colStack = new MyLinkedListImpl<>();
		
		colStack.push(valueOf(2));
		colStack.push(valueOf(4));
		colStack.push(valueOf(7));

		assertEquals(valueOf(7), colStack.peek());

		try {

			assertEquals(valueOf(7), colStack.pop());

		} catch (EmptyStackException e) {

			fail(e.getMessage());

		}

		assertEquals(valueOf(4), colStack.peek());

		try {

			assertEquals(valueOf(4), colStack.pop());

		} catch (EmptyStackException e) {

			fail(e.getMessage());

		}

		try {

			assertEquals(valueOf(2), colStack.pop());

		} catch (EmptyStackException e) {

			fail(e.getMessage());

		}
		try {
			
			colStack.pop();
			
			fail("El stack deberia estar vacio");

		} catch (EmptyStackException e) {
			
			assertTrue(true);

		}
	}

}
