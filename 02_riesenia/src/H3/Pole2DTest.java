import static org.junit.Assert.*;

public class Pole2DTest {

    @org.junit.Test
    public void pocet() {
        {
            String[][] pole = {{"yEs"}, {"No"}};
            assertEquals("test1", 2,
                    Pole2D.pocet(pole));
            //System.out.println(pocet(pole));
        }
        {
            String[][] pole = {{"tyEss"}, {" No"}};
            assertEquals("test2", 2,
                    Pole2D.pocet(pole));
        }

    }

    @org.junit.Test
    public void nahodnyRetazec() {
        assertNotNull("Test3",Pole2D.nahodnyRetazec());
    }
}