class Cab { // trieda C spája triedy A + B
	private A a = new A(); // vložená referencia (!) na objekt a typu A
	private B b = new B(); // vložená referencia (!) na objekt b typu B

	public void doA() {
		a.doA();
	}

	public void doB() {
		b.doB();
	}

}
