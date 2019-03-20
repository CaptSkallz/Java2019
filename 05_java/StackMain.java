/**
 * pouzitie tried Stack, StackObj, Stack50 a NodeStack
 * @author PB
 */
public class StackMain {
    public static void main(String[] args) {
    final int SSIZE = 10;
    Stack s = new Stack(SSIZE);
    for(int i=0; i<SSIZE; i++)
      s.push(i);
    while (!(s.isEmpty()))
      System.out.println(s.pop());
    //-------
    StackObj pd = new StackObj(SSIZE);
    pd.push(new Integer(123456));
    pd.push("ahoj");
    /*
    System.out.println(pd.pop());
    System.out.println(pd.pop());
    */
 
    String str = (String)pd.pop();
    System.out.println(str);

    Integer numb = (Integer)pd.pop();
    System.out.println(numb);
    
    //---------
    Stack50<String> st50 = new Stack50<String>(SSIZE);
    st50.push("caf");
    st50.push("hello");
    st50.push("salut");
    // st50.push(new Integer(12345));
    System.out.println(st50.pop());
    //---------
    NodeStack<Integer> sn = new NodeStack<Integer>();
    for(int i=0; i<10; i++)
      sn.push(i);
    while (!sn.isEmpty())	
      System.out.println(sn.pop());	
  }
}


