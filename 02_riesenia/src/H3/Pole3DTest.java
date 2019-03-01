import org.junit.Test;

import static org.junit.Assert.*;

public class Pole3DTest {

    @Test
    public void equalsIgnoreCase() {
        {
            String pole1[][][] = {{{null}, {"Tomas"}}};
            String pole2[][][] = {{{null}, {"ahoj"}}};
            assertFalse("Test1", Pole3D.equalsIgnoreCase(pole1, pole2));
            //System.out.println(equalsIgnoreCase(pole1, pole2));
        }


    }
}