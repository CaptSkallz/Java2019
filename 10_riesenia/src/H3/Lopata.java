package H3;

import java.util.Random;
import java.util.concurrent.Semaphore;
public class Lopata {
    int pocet_lopat = 3;
    static int pocet_robotnikov = 5;
    Semaphore s;

    public Lopata() {
        s = new Semaphore(pocet_lopat);
    }


    public static void main(String[] args) {
        Lopata l = new Lopata();
        for (int i = 0; i < pocet_robotnikov; i++) {
            Robotnik r = l.new Robotnik(i);
            r.start();

        }
    }
    public class Robotnik extends Thread{
        long odrobeny_cas = 0;
        int id;
        Robotnik(int id){
            this.id = id;
        }

        public void run(){
            while(odrobeny_cas < 10000){
                System.out.println(id + " spiiim");
                try {
                    sleep(new Random().nextInt(100)+100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    s.acquire();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long start = System.currentTimeMillis();
                System.out.println(id + " makam");
                try {
                    sleep(new Random().nextInt(100)+200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                s.release();
                long end = System.currentTimeMillis();
                odrobeny_cas += end - start;




            }
            System.out.println(id + " koniec sichty");

        }

    }

}
