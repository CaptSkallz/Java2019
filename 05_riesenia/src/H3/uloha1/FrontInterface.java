package H3.uloha1;

public interface FrontInterface<E> {
        public void enqueue(E elem, int prio);
        public E dequeue();
        public boolean isEmpty();
}
