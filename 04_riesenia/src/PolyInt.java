    public class PolyInt {
    	private long[] p;
     
    	public PolyInt(long[] p) {
    		this.p = p;		
    	}
     
    	public void vypisPole(long[] p) {
    		String s = "{";
    		for (int i = 0; i < p.length; i++) {
    			s += p[i] + ", ";
    		}
    		s += "}";
    		System.out.println(s);
    	}
     
    	/*
    	public void add(PolyInt p) {		
    		if (p.p.length > this.p.length) {
    			for (int i = 0; i < this.p.length; i++) {
    				p.p[i] += this.p[i];
    			}
    			this.p = p.p;
    		} else {
    			for (int i = 0; i < p.p.length; i++) {
    				this.p[i] += p.p[i];
    			}
    		}
    	}
    	*/
    	
    	public void add(PolyInt p) {
    		// skvostna myslienka si nazvat formalny argument p tak ako private field p
    		// v tom sa potom skvelo orientuje :-)
    		long[] vysledok = new long[Math.max(this.p.length, p.p.length)];
    		for (int i = 0; i < Math.max(this.p.length, p.p.length); i++) {
  				vysledok[i] += ((i < p.p.length)?p.p[i]:0) + 
  							   ((i < this.p.length)?this.p[i]:0);
   			}
    		this.p = vysledok;
    	}
    	
     
    	public void times(long c) {		
    		vypisPole(this.p);
    		if (c == 0) {
    			long[] x = { 0 };
    			p = x;
    			return;
    		}
    		if (c == 1) {
    			return;
    		}
    		for (int i = 0; i < p.length; i++) {
    			p[i] = p[i] * c;
    		}		
    	}
     
    	public void times(PolyInt p) {
    		long s1, s2;
    		long[] x = new long[(this.p.length - 1) + (p.p.length - 1) + 1];
    		for (int i = 0; i < x.length; i++) { // inicializujem pole na 0
    			x[i] = 0;
    		}
    		// kaslem na to ktore je dlhsie kratsie vzdy beriem vstup ze sa bude
    		// prenasobovat k this
     
    		for (int i = 0; i < p.p.length; i++) {
    			s1 = p.p[i];
    			if (s1 == 0) {
    				continue;
    			}
    			for (int j = 0; j < this.p.length; j++) {
    				s2 = this.p[j];
    				x[i + j] += (s1 * s2);
    			}
    		}
    		this.p = x;
     
    	}
     
    	public void derive() {
    		
    		long cislo;
    		for (int i = 1; i < p.length; i++) {
    			if (i - 1 == 0) {
    				cislo = p[i] * 1;
    				p[i - 1] = cislo;
    			} else { // ----
    				cislo = p[i] * (i);
    				p[i] = 0;
    				p[i - 1] = cislo;
    			}
    		}
    		// posledne bude o rad nizsie teda 0
    		p[p.length - 1] = 0;
    	}
    	
     
    	public long value(long e) {		
    		long hodnota = this.p[0];
    		// System.out.println(this.p[0]);
    		long cislo;
    		for (int i = 1; i < this.p.length; i++) {
    			cislo = (long) Math.pow(e, i);
    			cislo = cislo * this.p[i];
    			hodnota += cislo;
    		}
    		return hodnota;
     
    	}
    	
     
    	public String toString() {
    		String s = "";
    		if (p.length == 1) {
    			return "" + p[0];
    		}
    		for (int i = p.length - 1; i >= 0; i--) { // ideme odzadu od najvacsieho
    			if (i == 0) {
    				if (p[i] == 0) {
    					continue;
    				} else if (p[i] > 0) {
    					s += p[i];
    				} else {
    					s += "(" + p[i] + ")";
    				}
    			} else {
    				if (p[i] == 0) {
    					continue;
    				} else if (p[i] < 0) {
    					if (i != 1) {
    						s += "(" + p[i] + ")" + "*x^" + i;
    					} else {
    						s += "(" + p[i] + ")" + "*x";
    					}
    					if (i - 1 >= 0) {
    						s += "+";
    					}
    				} else {
    					if (p[i] != 1) {
    						if (i != 1) {
    							s += p[i] + "*x^" + i;
    						} else {
    							s += p[i] + "*x";
    						}
    					} else {
    						s += "x^" + i;
    					}
    					if (i - 1 >= 0) {
    						s += "+";
    					}
    				}
    			}
    		}
    		return s;
    	}
     
     
    	public static void main(String[] args) {
    		/*
    		2*x^5+4*x^4+(-2)*x^3+4*x^2+4, act: 12
    		po times
    		2*x^10+6*x^9+2*x^8+4*x^7+8*x^6+4*x^5+12*x^4+(-2)*x^3+8*x^2+4, exp:48
    		4*x^10+16*x^9+8*x^8+36*x^6+48*x^4+(-16)*x^3+32*x^2+16, act: 144
    		*/
    		 		
    		PolyInt pp1 = new PolyInt(new long[] { 1, 0, 1, -1, 1 }); // x^4+(-1)*x^3+x^2+1, exp:2
			PolyInt pp2 = new PolyInt(new long[] { 1, 0, 1,  0, 1, 1 }); // x^5+x^4+x^2+1
			System.out.println("pred add");
			System.out.println(pp1 + " at 1 = " + pp1.value(1));
			System.out.println(pp2 + " at 1 = " + pp2.value(1));
			pp1.add(pp2);
			System.out.println("po add");
			System.out.println(pp1 + " at 1 = " + pp1.value(1));
			System.out.println(pp2 + " at 1 = " + pp2.value(1));
			System.out.println("======");
    		
    		/*
    		 * PolyInt p1 = new PolyInt(new long[] { 1, 1 }); // x+1 PolyInt p2 =
    		 * new PolyInt(new long[] { -1, 1 }); // x-1 System.out.println("p1 = "
    		 * + p1); System.out.println(p1 + " at 1 = " + p1.value(1));
    		 * p1.times(p2); System.out.println("p3 = " + p1);
    		 */
     
    		for (int test = 0; test < 6; test++) {
    			PolyInt p1 = new PolyInt(new long[] { 1, 2, 3, -4, 1 });
    			PolyInt p2 = new PolyInt(new long[] { 1, 0, 0, 2 });
    			switch (test) {
    			case 0:
    				System.out.println("p1 = " + p1);
    				System.out.println("p2 = " + p2);
    				p1.add(p2);
    				System.out.println("p1 += p2, print p1 " + p1);
    				break;
    			case 1:
    				p1.times(p2);
    				System.out.println("p1 *= p2, print p1 = " + p1);
    				break;
    			case 2:
    				System.out.println(p1 + " at 1 = " + p1.value(1));
    				break;
    			case 3:
    				p1.derive();
    				System.out.println("p1 dx = " + p1);
    				break;
    			case 4:
    				p1.times(2);
    				System.out.println("p1 *= 2 " + p1);
    				break;
    			case 5:
    				p1.times(0);
    				System.out.print("p1 *= 0 " + p1);
    				break;
    			}
    		}
     
    	}
     
    }