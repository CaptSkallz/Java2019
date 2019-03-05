abstract class Postupnost {
  protected long prvy;
  protected long aktualny;
  public long Prvy() {
	  aktualny = prvy;
	  return aktualny;
  }
  abstract long Dalsi(); 
  public void printPostupnost(int n) {
	  System.out.print(Prvy());
	  for(int i= 0;i<n; i++) {
		  System.out.print(", "+ Dalsi());		  
	  }
	  System.out.println();
  }
}
