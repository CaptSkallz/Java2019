/**
 * prvy stack
 * @author PB
 */
public class Stack {
  protected int[] S;		// reprezentácia
  protected int top = -1;	

  public Stack(int Size) {
    S = new int[Size];
  }
  public boolean isEmpty() {
    return (top < 0);
  }
  public void push(int element) {
    if (top+1 == S.length) 
      System.out.println("Stack is full");
    else
      S[++top] = element;
  }
  public int pop() {
    int element;
    if (isEmpty()) {
      System.out.println("Stack is empty");
      return -1;
    }
    element = S[top--];
    return element;
  }
}
