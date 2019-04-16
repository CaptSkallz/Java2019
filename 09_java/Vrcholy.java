public class Vrcholy extends Thread {
	Trojuholniky ap;
	int N = 32;
	int[] x;
	int[] y;
	
	public Vrcholy(Trojuholniky ap) {
		this.ap = ap;
		x = new int[N];
		y = new int[N];
		for(int i=0; i<N; i++) {
			x[i] = (int)(Math.random()*600);
			y[i] = (int)(Math.random()*600);
		}		
	}
	public void run() {
		int i = 0;
		while (true) {
			int i1 = i-1;
			int i2 = i+1;
			if (i1 < 0) i1 = N-1;
			if (i2 == N) i2 = 0;
			double x1 = x[i1];
			double x2 = x[i2];
			double y1 = y[i1];
			double y2 = y[i2];
			
			double X1 = Math.sqrt(3)*(y2-y1)/2 + (x1+x2)/2;
			double Y1 = Math.sqrt(3)*(x1-x2)/2 + (y1+y2)/2;

			double X2 = Math.sqrt(3)*(y1-y2)/2 + (x1+x2)/2;
			double Y2 = Math.sqrt(3)*(x2-x1)/2 + (y1+y2)/2;
			double X,Y;
			
			if ((x[i]-X1)*(x[i]-X1) + (y[i]-Y1)*(y[i]-Y1) < 
			    (x[i]-X2)*(x[i]-X2) + (y[i]-Y2)*(y[i]-Y2)) {
			    	X = X1; Y = Y1;} else { X = X2; Y = Y2 ; }
			    
			double laziness = 0.01;
			double new_X = x[i] + (double)(X-x[i])*laziness;
			double new_Y = y[i] + (double)(Y-y[i])*laziness;

			//System.out.println(x[i]+";"+y[i] + " -> ");
			//System.out.println(X+";"+Y);
			
			x[i] = (int)(new_X);
			y[i] = (int)(new_Y);
			
			i++;
			if (i == N) i=0;
			ap.repaint();
		    try { 			 
			  sleep(4);
			} catch(Exception e) {}
		}
	}
}
