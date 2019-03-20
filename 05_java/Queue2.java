public class Queue2<E> implements QueueInterface<E> {
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
	if (size == 0)
	  node.setNext(node);
	else {
	  node.setNext(rear.getNext());
	  rear.setNext(node);
	}
	rear = node;
	size++;
  }
  public E dequeue() throws EmptyQueueException {
    if (size == 0)
      throw new EmptyQueueException("Queue is empty.");
	size--;
	E tmp = rear.getNext().getElement();
	if (size == 0)
	  rear = null;
	else 
	  rear.setNext(rear.getNext().getNext());
	return tmp;
  }
  public E front() throws EmptyQueueException {
	if (size == 0)
	  throw new EmptyQueueException("Queue is empty.");
	return rear.getNext().getElement();
  }  
}
