/**
 * kazdy blok obsahuje konstrukciu jedneho, resp. dvoch poli 
 * nakreslite obrazok, ktory podla vas predstavuje pamatovu
 * reprezentaciu vytvorenych struktur (s hodnotami a smernikmi). 
 * V pripade, ze si myslite, ze dana konstrukcia skonci chybou, 
 * vtedy nekreslite ziaden obrazok.
 * 
 */

public class Obrazky {	
	
{	
	int [] A;
}

{
	int [] A = new int [4];
}

{
	int [][] A = new int[4][3];
}

{
	int [][] A = new int[4][];
	A[1] = new int[4];
	A[3] = new int[2];
}

{
	int [] A = new int[4];
	int [] B = {0,1,2,3,4,5,6,7,8,9};
	System.arraycopy(B,2,A,0,4);
}

{
	int [] A = new int[4];
	int [] B = {2,3,4};
	System.arraycopy(B,0,A,0,4);
}

{
	int [] A = new int[4];
	int [] B = {0,1,2,3,4,5,6,7,8,9};
	System.arraycopy(B,8,A,0,4);
}

{
	int [] A = {1,1,1,1};
	int [] B = {2,2,2};
	System.arraycopy(A,0,B,1,2);
	System.arraycopy(B,0,A,0,3);
}

{
	int [] A = new int[4];
	int [] B = {0,1,2,3,4,5,6,7,8,9};
	System.arraycopy(B,0,A,0,10);
}

{
	int [][] A = new int[4][3];
	int [] B = {1,2,3,4,5,6,7,8,9,10};
	System.arraycopy(B,0,A[0],0,3);
	System.arraycopy(B,1,A[1],0,3);
	System.arraycopy(B,2,A[2],0,3);
	System.arraycopy(B,3,A[3],0,3);
}

}