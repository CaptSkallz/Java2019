import java.util.LinkedList;

public class LinkedListStack {
@SuppressWarnings("rawtypes")
private LinkedList lst = new LinkedList();

@SuppressWarnings("unchecked")
void push(Object elem) { lst.addFirst(elem); }
void pop() { lst.removeFirst(); }
Object top() { return lst.getFirst(); }
boolean empty() { return lst.size() == 0; }
public static void main(String[] args) {
  LinkedListStack lls = new LinkedListStack();
  lls.push((String)"hello");
  lls.push((String)"world");
  for (;!lls.empty(); lls.pop()) {
	  System.out.println(lls.top());
  }
}
}
