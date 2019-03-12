public class Foo {
  private String id;
  //@Override
  public boolean equals(Foo f) { return id.equals(f.id);}
  
  @Override
  public boolean equals(Object obj) {
	// TODO Auto-generated method stub
	return super.equals(obj);
  }
}