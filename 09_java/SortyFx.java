import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class SortyFx extends Application {
	SortPanelFx buble, quick, merge, random;

	@Override
	public void start(Stage primaryStage) {
		buble = new SortPanelFx("Buble", Color.MAGENTA);
		quick = new SortPanelFx("Quick", Color.BLUE);
		merge = new SortPanelFx("Merge", Color.RED);
		random = new SortPanelFx("Random", Color.GREEN);
		FlowPane pane = new FlowPane(buble, quick, merge, random);

		Scene scene = new Scene(pane, 800, 200);// vytvor scenu

		primaryStage.setTitle("Triedenia"); // pomenuj okno
											// aplikacie,
											// javisko
		primaryStage.setScene(scene); // vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); // zobraz javisko
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class SortPanelFx extends Pane {
	private int[] a;
	private int lo, hi;
	private Color c;

	public SortPanelFx(String algo, Color col) {
		this.c = col;
		setPrefSize(200, 200);
		a = new int[100];
		for (int i = 0; i < a.length; i++)
			a[i] = (int) (200 * Math.random());
		SortThreadFx thread = new SortThreadFx(this, algo, a);
		thread.start();
	}

	public void swap(int i, int j) {
		lo = i;
		hi = j;
	}

	public void paintSortPanel() {
		getChildren().clear();
		for (int i = 0; i < a.length; i++) {
			Line li = new Line(2 * i, a[i], 2 * i, 0);
			li.setStroke((i == lo || i == hi) ? Color.BLACK : c);
			getChildren().add(li);
		}
	}
}

class SortThreadFx extends Thread {
	SortPanelFx can;
	String algo;
	int[] a;

	public SortThreadFx(SortPanelFx can, String algo, int[] a) {
		this.can = can;
		this.algo = algo;
		this.a = a;
	}

	public void run() {
		if (algo.equals("Buble"))
			bubleSort(a);
		else if (algo.equals("Quick"))
			quickSort(a, 0, a.length - 1);
		else if (algo.equals("Merge"))
			mergeSort(a, 0, a.length - 1);
		else
			randomSort(a);
	}

	void swap(int i, int j) {
		can.swap(i, j);
		Platform.runLater(() ->	can.paintSortPanel());
		try {
			sleep(10);
		} catch (Exception e) {
		}
	}

	void randomSort(int a[]) {
		while (true) {
			int i = (int) ((a.length - 1) * Math.random());
			int j = i + 1;
			swap(i, j);
			if (i < j && a[i] > a[j]) {
				int pom = a[i];
				a[i] = a[j];
				a[j] = pom;
			}
		}
	}

	void bubleSort(int a[]) {
		for (int i = a.length; --i >= 0;) {
			boolean flipped = false;
			for (int j = 0; j < i; j++) {
				swap(i, j);
				if (a[j] > a[j + 1]) {
					int T = a[j];
					a[j] = a[j + 1];
					a[j + 1] = T;
					flipped = true;
				}
			}
			if (!flipped)
				return;
		}
	}

	void quickSort(int a[], int lo0, int hi0) {
		int lo = lo0;
		int hi = hi0;
		if (lo >= hi)
			return;
		else if (lo == hi - 1) {
			swap(lo, hi);
			if (a[lo] > a[hi]) {
				int T = a[lo];
				a[lo] = a[hi];
				a[hi] = T;
			}
			return;
		}
		int pivot = a[(lo + hi) / 2];
		a[(lo + hi) / 2] = a[hi];
		a[hi] = pivot;
		while (lo < hi) {
			while (a[lo] <= pivot && lo < hi) {
				swap(lo, lo);
				lo++;
			}
			while (pivot <= a[hi] && lo < hi) {
				swap(hi, hi);
				hi--;
			}
			if (lo < hi) {
				swap(lo, hi);
				int T = a[lo];
				a[lo] = a[hi];
				a[hi] = T;
			}
		}
		a[hi0] = a[hi];
		a[hi] = pivot;
		quickSort(a, lo0, lo - 1);
		quickSort(a, hi + 1, hi0);
	}

	void mergeSort(int a[], int lo0, int hi0) {
		int lo = lo0;
		int hi = hi0;
		swap(lo, hi);
		if (lo >= hi)
			return;
		int mid = (lo + hi) / 2;
		mergeSort(a, lo, mid);
		mergeSort(a, mid + 1, hi);
		int end_lo = mid;
		int start_hi = mid + 1;
		while ((lo <= end_lo) && (start_hi <= hi)) {
			swap(lo, lo);
			if (a[lo] < a[start_hi])
				lo++;
			else {
				int T = a[start_hi];
				for (int k = start_hi - 1; k >= lo; k--) {
					a[k + 1] = a[k];
					swap(k, k + 1);
				}
				a[lo] = T;
				lo++;
				end_lo++;
				start_hi++;
			}
		}
	}
}
