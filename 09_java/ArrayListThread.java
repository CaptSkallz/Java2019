public class ArrayListThread extends Thread {
	boolean kind;
	//static ArrayListNotSynchronized al = new ArrayListNotSynchronized();
	static ArrayListSynchronized al = new ArrayListSynchronized();
	public ArrayListThread(boolean kind) {
		this.kind = kind;
	}
	public void run() { 
		while (true) {
			if (kind) 
				al.add();
			else
				al.delete();
		}
	}
	public static void main(String[] args) {
		ArrayListThread t1 = new ArrayListThread(true);
		t1.start();
		ArrayListThread t2 = new ArrayListThread(false);
		t2.start();
	}

}
