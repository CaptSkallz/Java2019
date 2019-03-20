import static org.junit.Assert.*;

import org.junit.Test;


public class Stack50Test {

	@Test
	public void testStack() {
		final int SIZE = 99;
	    Stack50<Integer> st = new Stack50<Integer>(SIZE);
	    assertTrue(st.isEmpty());
	    for(int i=0; i<SIZE; i++)
	    	st.push(i);
	    assertTrue(!st.isEmpty());
	    for(int i=SIZE-1; i>=0; i--) {
	    	assertTrue(st.top() == i);
	    	st.pop();
	    }
	    assertTrue(st.isEmpty());
	}
}
