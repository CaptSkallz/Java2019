import static org.junit.Assert.*;

import org.junit.Test;


public class ArrayStackTest {

	@Test
	public void testStack() {
		final int SIZE = 99;
	    ArrayStack<Integer> st = new ArrayStack<Integer>();
	    assertTrue(st.isEmpty());
	    for(int i=0; i<SIZE; i++) {
	    	assertTrue(st.size() == i);
	    	st.push(i);
	    }
	    assertTrue(!st.isEmpty());
	    for(int i=SIZE-1; i>=0; i--) {
	    	assertTrue(st.top() == i);
	    	assertTrue(st.pop() == i);
	    }
	    assertTrue(st.isEmpty());
	}
}
