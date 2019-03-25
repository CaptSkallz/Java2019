import java.util.*;

public class LinkedListStack2<E> {
private LinkedList<E> lst = new LinkedList<E>();

void push(E elem) { lst.addFirst(elem); }
void pop() { lst.removeFirst(); }
E top() { return lst.getFirst(); }

public String toString() {
	String s = "";
	for(E elem :lst)
		s += elem + ", ";
	return s;
}
boolean empty() { return lst.size() == 0; }
public static void main(String[] args) {
   LinkedListStack2<Integer> lls = new LinkedListStack2<Integer>();
   lls.push(new Integer(1));
   lls.push(new Integer(2));
   System.out.println(lls);
   while (!lls.empty()) {
	 System.out.println(lls.top());
	 lls.pop();
   }
  }
}
