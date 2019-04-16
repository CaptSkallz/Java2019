public class SortThread extends Thread {
SortCanvas can;
String algo;
int[] a;

	public SortThread(SortCanvas can, String algo, int[] a) {
      this.can = can;		
      this.algo = algo;
      this.a = a;
	}
	public void run() {
	  if (algo.equals("Buble")) 
		bubleSort(a);
	  else if (algo.equals("Quick")) 
		quickSort(a,0,a.length-1);
	  else if (algo.equals("Merge")) 
		mergeSort(a,0,a.length-1);
	  else
		randomSort(a);
	}
	
	void swap(int i, int j) {
		can.swap(i,j);
 		can.repaint();
 		try{ sleep(10); } catch(Exception e) {}
	}
	
    void randomSort(int a[]) {
	  while(true) {
		int i = (int)((a.length-1)*Math.random());
		int j = i+1;
		swap(i,j);
		if (i<j && a[i] > a[j]) {
			int pom = a[i];
			a[i] = a[j];
			a[j] = pom;
		}
	  }
    }
    void bubleSort(int a[]) {
      for (int i = a.length; --i>=0; ) {
        boolean flipped = false;
    	for (int j = 0; j<i; j++) {
    	  swap(i,j);
    	  if (a[j] > a[j+1]) {
    	  	int T = a[j];
    	  	a[j] = a[j+1];
    	  	a[j+1] = T;
    	  	flipped = true;
    	  }
    	 }
    	 if (!flipped)
    	   return;
      }
    }
    void quickSort(int a[], int lo0, int hi0)  {
      int lo = lo0;
      int hi = hi0;
      if (lo >= hi)
    	return;
      else if( lo == hi - 1 ) {
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
      while( lo < hi ) {
        while (a[lo] <= pivot && lo < hi) {
          swap(lo, lo);
          lo++;
        }
        while (pivot <= a[hi] && lo < hi ) {
          swap(hi, hi);
          hi--;
        }
        if( lo < hi ) {
          swap(lo, hi);
          int T = a[lo];
          a[lo] = a[hi];
          a[hi] = T;
        }
      }
      a[hi0] = a[hi];
      a[hi] = pivot;
      quickSort(a, lo0, lo-1);
      quickSort(a, hi+1, hi0);
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
        swap(lo,lo);
        if (a[lo] < a[start_hi])
          lo++;
        else {
       	  int T = a[start_hi];
          for (int k = start_hi - 1; k >= lo; k--) {
             a[k+1] = a[k];
             swap(k,k+1);
          }
          a[lo] = T;
          lo++;
          end_lo++;
          start_hi++;
        }
      }
   }       
}
