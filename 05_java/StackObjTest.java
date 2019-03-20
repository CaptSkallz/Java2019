import static org.junit.Assert.*;

import org.junit.Test;


public class StackObjTest {

	@Test
	public void testStack() {
		final int SIZE = 99;
	    StackObj st = new StackObj(SIZE);
	    assertTrue(st.isEmpty());
	    for(int i=0; i<SIZE; i++)
	    	st.push(i);
	    assertTrue(!st.isEmpty());
	    for(int i=SIZE-1; i>=0; i--) {
	    	assertTrue((Integer)st.top() == i);
	    	st.pop();
	    }
	    assertTrue(st.isEmpty());
	}
}
