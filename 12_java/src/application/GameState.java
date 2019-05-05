package application;
/**

 * JavaFX Template for CheckerBoard-based games.
 * GameState
 * @author Lukáš Gajdošech
 */

import java.io.*;

public class GameState implements Serializable {   //Herny stav
    final int SIZE;                                //velkost sachovnice
    public int remainingTime = 10;                 //ostavajuci cas v tahu
    public boolean player = true;                  //ci je na rade hrac alebo oponent
    public int [][] plocha;                        //stav sachovnice
    public boolean inProgress = true;              //hra prebieha

    public GameState(int SIZE) {
        this.SIZE = SIZE;
        plocha = new int[SIZE][SIZE];
    }

    public void save(String filePath) {
        File f = new File(filePath);
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(f));
            os.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameState load(String filePath) {
        File f = new File(filePath);
        ObjectInputStream os;
        GameState state = null;
        try {
            os = new ObjectInputStream(new FileInputStream(f));
            state = (GameState) os.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (state == null)
            System.out.println("Neuspesne nacitanie");
        return state;
    }

}
