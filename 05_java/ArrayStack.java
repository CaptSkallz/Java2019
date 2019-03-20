public class ArrayStack<E> implements StackInterface<E> {
  protected int capacity; 		
  protected E S[];		
  protected int top = -1;	

  public ArrayStack() {
    this(1000); 		
  }
  @SuppressWarnings("unchecked")
  public ArrayStack(int cap) {
    capacity = cap;
    S = (E[]) new Object[capacity];
    // S = new E[capacity];
  }
  public void push(E element) throws FullStackException {
    if (size() == capacity)
      throw new FullStackException("Stack is full.");
    S[++top] = element;
  }
  public E top() throws EmptyStackException {
    if (isEmpty())
      throw new EmptyStackException("Stack is empty.");
    return S[top];
  }
  public E pop() throws EmptyStackException {
	E element;
	if (isEmpty())
	  throw new EmptyStackException("Stack is empty.");
	  element = S[top];
	  S[top--] = null; 	// dereference S[top] for garbage collection.
	  return element;
	}
  public boolean isEmpty () {
	    return top < 0;
  }
  public int size() {
	    return top+1;
  }
  public String toString() {
	String s;
	s = "[";
	if (size() > 0) s+= S[0];
	if (size() > 1)
	  for (int i = 1; i <= size()-1; i++) s += ", " + S[i];
	return s + "]";
  }
  public static void main(String[] args) {
    ArrayStack<String> B = new ArrayStack<String>();
	B.push("Boris");
	B.push("Alenka");
	System.out.println((String)B.pop());
	B.push("Elena");
	System.out.println((String)B.pop());
  }
}
