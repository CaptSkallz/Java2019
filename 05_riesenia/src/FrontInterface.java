public interface FrontInterface<E> {
        public void enqueue(E elem, int prio);
        public E dequeue();
        public boolean isEmpty();
}