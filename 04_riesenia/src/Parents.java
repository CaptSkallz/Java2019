import java.util.Stack;

public class Parents {
	
   public static boolean checkParent(String input) {
	  Stack theStack = new Stack(); 
	  for (int j = 0; j < input.length(); j++) {
	     char ch = input.charAt(j);
	     switch (ch) {
	       case '[': case '(':
		     theStack.push(new Character(ch)); 
		     break;
		   case ']': case ')':
		     if (!theStack.isEmpty()) {
		       char chx = (Character)(theStack.pop()); 
		       if ((ch == ']' && chx != '[') || (ch == ')' && chx != '('))
		        return false;
		     } else
		        return false;
		     break;
		    }
		  }
		  return (theStack.isEmpty());
   }
   public static void main(String[] args) {
	   System.out.println(checkParent("()()()"));
	   System.out.println(checkParent("([])[[]]"));
	   System.out.println(checkParent("([])[[])"));
	   System.out.println(checkParent("]][][["));
   }
}
