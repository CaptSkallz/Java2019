class Cc {			// trieda C spája triedy A + B
  private A a = new A();	// vložená referencia (!) na objekt a typu A
  private B b = new B();	// vložená referencia (!) na objekt b typu B
  public void doA() { a.doA(); }  // delegovanie metód z triedy A do C
  public void doB() { b.doB(); }  // delegovanie metód z triedy B do C
}
