public class QueueMain {

  public static void main(String[] args) {
    Queue<Integer> q = new Queue<Integer>();
    for(int i=1; i<10; i++)
      q.enqueue(new Integer(i));
    while (!q.isEmpty()) {
      System.out.println(q.front());
      q.dequeue();
    }
    Queue2<Integer> q2 = new Queue2<Integer>();
    for(int i=1; i<10; i++)
      q2.enqueue(new Integer(i));
    while (!q2.isEmpty()) {
      System.out.println(q2.front());
      q2.dequeue();
    }
  }
}
