package application;

import java.io.Serializable;
import java.util.Random;

import javafx.scene.image.Image;

public class PexesoState implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int size;	
	public Integer[][] playground;
	
    String pika[] = {
        "pk000010.gif", "pk000020.gif", "pk000030.gif", "pk000070.gif", "pk000090.gif", "pk000110.gif", "pk000130.gif", "pk000160.gif", "pk000200.gif", "pk000240.gif", 
        "pk000250.gif", "pk000260.gif", "pk000310.gif", "pk000320.gif", "pk000360.gif", "pk000370.gif", "pk000410.gif", "pk000430.gif", "pk000460.gif", "pk000470.gif", 
        "pk000500.gif", "pk000540.gif", "pk000580.gif", "pk000630.gif", "pk000650.gif", "pk000670.gif", "pk000690.gif", "pk000730.gif "
    };

	
	public boolean nextPlayerIsX = false;
	public long elapsedTime = 0;
	
	public PexesoState(int size) {
		this.size = size;
		playground = new Integer[size][size];
		Random rnd = new Random();
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				playground[i][j] = -(1+rnd.nextInt(pika.length));
	}
}
