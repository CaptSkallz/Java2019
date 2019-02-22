public class Obrys {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
      char[][] matica = {{' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
              {' ',' ',' ','#',' ',' ','#',' ',' ',' '},
              {' ',' ',' ',' ','#',' ','#',' ',' ',' '},
              {' ',' ',' ','#','#','#','#','#',' ',' '},
              {' ',' ',' ',' ',' ','#','#','#',' ',' '},
              {' ',' ',' ','#','#','#','#',' ',' ',' '},
              {' ',' ',' ',' ','#','#','#',' ',' ',' '},
              {' ',' ',' ',' ',' ','#',' ',' ',' ',' '},
              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};
      /*
    for (int i = 0; i < matica.length; i++) {
      for (int j = 0; j < matica[i].length; j++) {
        System.out.print(matica[i][j]);
      }
      System.out.println();
    }
    */
    
    obrys8(matica,'#','@');
    obrys4(matica,'#','$');
    
    for (int i = 0; i < matica.length; i++) {
      for (int j = 0; j < matica[i].length; j++) {
        System.out.print(matica[i][j]);
      }
      System.out.println();
    }

  }
  
  public static void obrys8(char[][] matica, char hladany, char obrysovy){
	  System.out.println("obrys8 not implemented");
  }
  
  public static void obrys4(char[][] matica, char hladany, char obrysovy){
	  System.out.println("obrys4 not implemented");
  }
} 