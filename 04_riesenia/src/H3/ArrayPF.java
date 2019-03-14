package H3;

public class ArrayPF {
	String arrayOfStrings[] = null;
	int numberOfElement = 0;
	int lenghtOfArray = 0;
	int arrayOfPriorities[] = null;

	// opraveny kod z cviceni H3...
	
	public ArrayPF(int lenghtOfArray) {
		this.arrayOfStrings = new String[lenghtOfArray];
		this.arrayOfPriorities = new int[lenghtOfArray];
		this.lenghtOfArray = lenghtOfArray;
	}

	public void enqueue(String elem, int prio) {
		if(numberOfElement == lenghtOfArray){
			return;
		}
		
		for(int i = numberOfElement - 1; i >= 0; i--){
			if(arrayOfPriorities[i] < prio){
				arrayOfStrings[i + 1] = elem;
				arrayOfPriorities[i + 1] = prio;
				numberOfElement++;
				return;
			}
			else{
				arrayOfPriorities[i + 1] = arrayOfPriorities[i];
				arrayOfStrings[i + 1] = arrayOfStrings[i];
			}
		}
		
		arrayOfPriorities[0] = prio;
		arrayOfStrings[0] = elem;
		numberOfElement++;

	}

	public String dequeue() {
		return "";
	}

	public boolean isEmpty() {
		return numberOfElement == 0;
	}

	public static void main(String[] args) {
		ArrayPF f = new ArrayPF(100);
		f.enqueue(new String("janka"), 5);
		f.enqueue(new String("danka"), 2);
		f.enqueue(new String("hanka"), 1);
		f.enqueue(new String("anka"), 4);
		f.enqueue(new String("zuzanka"), 3);
		f.enqueue(new String("elenka"), 1);
		f.enqueue(new String("zofka"), 6);
		f.enqueue(new String("evka"), 4);
		System.out.println(f);
		// while (!f.isEmpty())
		// System.out.println(f.dequeue());
	}

	@Override
	public String toString() {
		String result = "number of elements = " + numberOfElement + "\n";
		for(int i = 0; i < numberOfElement; i++){
			result += arrayOfPriorities[i] + ":" + arrayOfStrings[i] + "\n";
		}
		return result;
	}
}
