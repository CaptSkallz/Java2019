package application;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class RealBindings {
	public static void main(String[] args) {
		DoubleProperty polomer = new SimpleDoubleProperty();
		DoubleProperty priemer = new SimpleDoubleProperty();
		
		priemer.bind(polomer.multiply(2));
		DoubleProperty obvod = new SimpleDoubleProperty();
		
		obvod.bind(polomer.multiply(2).multiply(Math.PI));
		
		NumberBinding stvorec = Bindings.multiply(polomer, polomer);
		DoubleProperty obsah = new SimpleDoubleProperty();
		obsah.bind(stvorec.multiply(Math.PI));
		
		//polomer.bind(polomer.divide(2));

		for (double r = 0; r < 2; r += 0.5) {
			polomer.set(r);
			//obvod.set(r);
			System.out.printf("polomer=%6.2f, priemer=%6.2f, obvod=%6.2f, obsah=%6.2f\n", polomer.getValue(),
					priemer.getValue(), obvod.getValue(), obsah.getValue());
		}

	}
}