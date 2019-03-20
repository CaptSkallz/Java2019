public class Queue<E> implements QueueInterface<E> {
Node<E> front;
Node<E> rear;
int size = 0;
	
  public int size() {
     return size;
  }
  public boolean isEmpty() {
	return (size==0);  
  }
  public void enqueue(E elem) {
	Node<E> node = new Node<E>();
	node.setElement(elem);
	node.setNext(null); 	
	if (size == 0)
	  front = node; 	
	else
	  rear.setNext(node); 
	rear = node; 	
	size++;
  }
  public E dequeue() throws EmptyQueueException {
    if (size == 0)
      throw new EmptyQueueException("Queue is empty.");
    E tmp = front.getElement();
	front = front.getNext();
	size--;
	if (size == 0)
	  rear = null; 	
	return tmp;
   }
  public E front() throws EmptyQueueException {
	if (size == 0)
	  throw new EmptyQueueException("Queue is empty.");
	return front.getElement();
  }
}
