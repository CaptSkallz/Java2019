package H3;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

final class BinaryTree implements BinaryTreeInterface {
    private BinaryNode root;

    public BinaryTree () {
 	  root = null;
    }
    public BinaryTree(BinaryNode root) {
		this.root = root;
	}
    public boolean find(String x) {
        if (root == null){
            return false;
        }
        else{
            return root.find(x);
        }
      }
    public BinaryTree insert(String x) {
        if(root == null) {
            root = new BinaryNode(x);
        } else {
            root.insert(x);
        }
   	  return this;
    }
    public BinaryTree delete(String key) {
    	return null;
    	// cvicenie 4
    }    
    public String toString(){
       return (root == null)? "nema root" : root.toString();  // cvicenie 4
    }
    public String[] toArray() {
    	String[] p = new String[100]; // 100 asi nie je dobra konstanta :-)
     	// cvicenie 4
        return p;
     }
     public String maximalny(){
        if(root==null)
            return "";
        else
            return root.maximalny();
     }


    public static void main (String args[]) {
      BinaryTree s = new BinaryTree();
      Random random = new Random();
      /*
        s.insert("d");
        s.insert("a");
        s.insert("b");
        s.insert("c");
        System.out.println(s.find("a"));
        System.out.println(s.find("b"));
        System.out.println(s.find("c"));
        System.out.println(s.find("d"));
        System.out.println(s.find("e"));
        */
      String[] pole = new String[110];
      for(int i=0; i<110; i++) {
          String str = new BigInteger(10, random).toString();
          s = s.insert(str);
          pole[i] = str;
      }
      int pocet = 0;
      for(String prvok: pole){
          if(s.find(prvok) == false){
              pocet++;
          }
      }
      Arrays.sort(pole);
        System.out.println(pole[109]+" "+s.maximalny());
        System.out.println("pocet false = " + pocet);
      System.out.println(s.toString());
      System.out.println("....");
      }
}
