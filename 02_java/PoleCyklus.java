/**
 * cykly a polia
 * @author PB
 */
public class PoleCyklus {
final static int MAX = 100;	
  public static void main(String[] args) {
    char[] poleChar = new char[MAX];

    for (int i = 0;  i < poleChar.length;  i++) System.out.print(i);
    System.out.println();
    
    for (int i = 0;  i < MAX;  i++) System.out.print(i);
    System.out.println();

    for (int i = MAX-1;  i >= 0;  i--) System.out.print(i); 
    System.out.println();
    
    int i=0; 
    do {
    	System.out.print(i);
    	i++;
    }
    while (i < MAX);
    System.out.println();
    
    int j=MAX;
    while (j-- > 0) System.out.print(j);
    System.out.println();

    for (char ch:poleChar) { System.out.print(ch); }
    System.out.println();   
  } 
}
