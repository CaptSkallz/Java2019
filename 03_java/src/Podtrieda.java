public class Podtrieda extends Nadtrieda {
		public int a;
		public Podtrieda() {
			a = -1;
		}
		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
		public int getSuperA() {
			return super.a;
		}
		public int getSuperGetA() {
			return super.getA();
		}
}

