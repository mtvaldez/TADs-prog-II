package uy.edu.um.adt.queue;

import static java.lang.Integer.valueOf;
import static org.junit.Assert.*;

import org.junit.Test;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.queue.EmptyQueueException;
import uy.edu.um.prog2.adt.queue.MyQueue;

public class QueueImplTest {

	@Test
	public void testFlujoCompleto() {
		MyQueue<Integer> queue = new MyLinkedListImpl<>();
		
		queue.enqueue(valueOf(21));
		queue.enqueue(valueOf(34));
		queue.enqueue(valueOf(3));

        assertTrue(queue.contains(21));
        assertEquals(3, queue.size());

		try {

			assertEquals(valueOf(21), queue.dequeue());

		} catch (EmptyQueueException e) {

			fail(e.getMessage());

		}

        assertFalse(queue.contains(21));

		assertEquals(2, queue.size());
		
		assertTrue(queue.contains(34));
		
		assertFalse(queue.contains(18));

        try {

            assertEquals(valueOf(34), queue.dequeue());

        } catch (EmptyQueueException e) {

            fail(e.getMessage());

        }

        try {

            assertEquals(valueOf(3), queue.dequeue());

        } catch (EmptyQueueException e) {

            fail(e.getMessage());

        }

        try {

            queue.dequeue();

            fail("Se espera excepcion");
        } catch (EmptyQueueException e) {

            assertTrue(true);

        }
	}

}
