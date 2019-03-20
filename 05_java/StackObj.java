/**
 * Zasobnik, ktoreho prvky su objekty triedy Object
 * @author PB
 */
public class StackObj {
    protected Object[] S;
    protected int top;

    public StackObj (int Size) {
      S = new Object[Size];
      top = 0;
    }
    public boolean isEmpty () {
        return top == 0;
    }
    public void push (Object item) {
        S[top++] = item;
    }
    public Object pop () {
        return S[--top];
    }
    public Object top() {
    	return S[top-1];
    }
}
