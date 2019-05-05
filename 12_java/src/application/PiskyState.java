package application;

import java.io.Serializable;

public class PiskyState implements Serializable {
	private static final long serialVersionUID = 1L;
	public int[][] playground;
	public boolean nextPlayerIsX = false;
	public long elapsedTime = 0;
	
	public PiskyState(int size) {
		 playground = new int[size][size];
	}
}
