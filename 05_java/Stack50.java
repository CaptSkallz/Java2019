/**
 * Parametrizovany zasobnik
 * @author PB
 */
public class Stack50<E> {
  protected E[] S;
  protected int top;

  @SuppressWarnings("unchecked")
  public Stack50(int Size) {
    S = (E[]) new Object[Size];
    // toto nejde: S = new E[Size];
	top = 0;
  }
  public boolean isEmpty () {
    return top == 0;
  }
  public void push (E item) {
    S[top++] = item; 
  }
  public E pop () {
    return S[--top];
  }
  public E top () {
	return S[top-1];
  }
}
