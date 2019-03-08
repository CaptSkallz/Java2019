/**
 * Cvicenie 3
 * @author PB
 */
public class Matrix {
	  public int rows, cols;
	  public int[][] mat;  

	  public Matrix (int rows, int cols) {
	    mat=new int [rows][cols];
	    for(int i=0; i<rows; i++)
	      for(int j=0; j<cols; j++){
	        if (cols==rows + 1 )
		  mat[i][j]=1;
		else
		  mat[i][j]=0;
	      }
	  }
	  public Matrix (Matrix M) {
	    mat=new int [M.rows][M.cols];
	    for(int i=0; i<M.rows; i++)
	      for(int j=0; j<M.cols; j++)
	        mat[i][j] = M.mat[i][j];
	  }
	  public Matrix plus (Matrix M){
	    if((M.rows==rows)&&(M.cols==cols))
	    {
	    for(int i=0; i<M.rows; i++)
	      for(int j=0; j<M.cols; j++)
	 	mat[i][j]=M.mat[i][j]+mat[i][j];      
	    }    
	  return this;
	  }
	  
	  public Matrix times (Matrix M){
	  for(int i=0; i<M.rows; i++)
	    for(int j=0; j<M.cols; j++)
	      for(int k=0; k<M.cols; k++)
	        mat[i][j]+=M.mat[i][k]*M.mat[k][j];
	  return this;
	  }
	  
	  public Matrix transpose (){    
	    for(int i=0; i<this.rows; i++)
	      for(int j=0; j<this.cols; j++)
	        mat[i][j]=mat[j][i];
	    
	  return this;
	  }
	  public String toString (){
	    String s=new String();
	    for(int i=0; i<rows; i++)
	      for(int j=0; j<cols; j++){
	        s+=s+String.valueOf(this.mat[i][j]);
	      }
	  return s;  
	  }
	  /*public void set (int row, int col, int newValue){
	  return;
	  }
	  public int get (int row, int col){
	  return;
	  }*/
	  public static void main (String[] args){
	     Matrix test = new Matrix(4,4);
	     Matrix test2 = new Matrix(4,4);
	     test=test.times(test2);
	     System.out.println(test.toString()); 
	  }
	}