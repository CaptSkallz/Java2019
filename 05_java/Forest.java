import java.util.*;

public class Forest<E> {
  private ArrayList<Tree<E>> broths;
  
  public Forest(ArrayList<Tree<E>> broths) {
	  this.broths = broths;
  }
  public String toString(){
	  String s = "";
	  char ch = '[';
	   for(Tree<E> e:broths) {
		  s += ch + e.toString();
		  ch = ',';
	  }
	  s += "]";
	  return s;
  }
  public boolean find(E key) {
	  for(Tree<E> e:broths) {
		  if (e.find(key)) return true;
	  }
	  return false; //
  }
  public int size() {
	  int s = 0;
	  for(Tree<E> e:broths) {
		  s += e.size();
	  }
	  return s; //
  }
  public void insert(E parent, E el) {
	for(Tree<E> e:broths)
	  e.insert(parent,el);
  }
  public void addLast(Tree<E> t) {
	if (broths == null)
	  broths = new ArrayList<Tree<E>>();
	broths.add(t);
  }
  public Forest<E> remove(E el, Forest<E> f) {
	if (f == null)
	  return f;
	else {
	  ArrayList<Tree<E>> newBroths = new ArrayList<Tree<E>>(); 	
	  for(Tree<E> e:broths) {
	    Tree<E> t = e.remove(el,e);
	    if (t != null)
	      newBroths.add(t);	
	  }
	  return new Forest<E>(newBroths);
	}
  }
  public static void main(String[] args) {
	  Tree<String> t1 = new Tree<String>("system");
  	  Tree<String> t2 = new Tree<String>("system32");
	  Tree<String> t3 = new Tree<String>("config");
	  ArrayList<Tree<String>> al1 = new ArrayList<Tree<String>>();
	  al1.add(t1);
	  al1.add(t2);
	  al1.add(t3);
	  Tree<String> t123 = new Tree<String>("WINNT", new Forest<String>(al1));
	  Tree<String> u1 = new Tree<String>("Universal crack");
	  Tree<String> u2 = new Tree<String>("java");
	  ArrayList<Tree<String>> al2 = new ArrayList<Tree<String>>();
	  al2.add(u1);
	  al2.add(u2);
	  Tree<String> u12 = new Tree<String>("Program Files", new Forest<String>(al2));

	  ArrayList<Tree<String>> al3 = new ArrayList<Tree<String>>();
	  al3.add(t123);
	  al3.add(u12);
	  
	  Forest<String> disk = new Forest<String>(al3);
	  System.out.println(disk);
	  System.out.println(disk.find("java"));
	  System.out.println(disk.find("kava"));
	  System.out.println(disk.size());
	  disk.insert("java","pascal");
	  disk.insert("java","c++");
	  System.out.println(disk);
	  disk = disk.remove("config",disk);
	  System.out.println(disk);
	  disk = disk.remove("java",disk);
	  System.out.println(disk);
	  disk = disk.remove("WINNT",disk);
	  System.out.println(disk);
	  disk = disk.remove("Universal crack",disk);
	  System.out.println(disk);
	  disk = disk.remove("Program Files",disk);
	  System.out.println(disk);
  }
}
