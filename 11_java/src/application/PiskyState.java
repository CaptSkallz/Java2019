package application;
import java.io.Serializable;

public class PiskyState implements Serializable {
	public final static int SIZE = 10;

	private static final long serialVersionUID = 1L;
	public int[][] playground = new int[SIZE][SIZE];
	public boolean nextPlayerIsX = false;
	public long elapsedTime = 0;
}