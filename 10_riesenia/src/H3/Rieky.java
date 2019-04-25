package H3;

import java.util.Random;

public class Rieky {
    int pocet_riek = 10;
    Rieka[] rieky = new Rieka[pocet_riek];

    public Rieky () {
        for (int i=0; i < pocet_riek; i++){
            Rieka r = new Rieka();
            rieky[i] = r;
        }
        for (int i=0; i < pocet_riek; i++){
            rieky[i].start();
        }
    }

    public synchronized void kontrola(Rieka r) {
        for (Rieka r2 : rieky){
            if (r2 == null || r == null){
                return;
            }
            if (r2 != r){
                if (r2.col == r.col){
                    r2.zijem = false;
                }
            }
        }
    }

    public synchronized int pocet_zivych (){
        int res = 0;
        for (Rieka r : rieky){
            if (r.zijem){
                res += 1;
            }
        }
        return res;
    }


    class Rieka extends Thread{
        int col = 0;
        boolean zijem = true;

        public Rieka() {
            col = new Random().nextInt(20);
        }


        public void update(){
            int d = new Random().nextInt(3) - 1;
            col += d;
            if (col < 0) {
                col = 0;
            }
            if (col >= 80) {
                col = 80;
            }
            kontrola(this);
        }

        @Override
        public void run() {
            while(zijem){
                update();
                try {
                    sleep(350 + new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Printer extends Thread{
        @Override
        public void run() {
            int counter = 10;
            while(counter > 0){
                try {
                    sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                StringBuffer sb = new StringBuffer("                                                                                    ");
                for (Rieka r : rieky){
                    if (r.zijem) {
                        sb.setCharAt(r.col, '*');
                    }
                }
                if (pocet_zivych() == 1){
                    counter -= 1;
                }
                System.out.println(sb.toString());
            }
        }
    }

    public static void main(String[] args) {
        Rieky r = new Rieky();
        Printer p = r.new Printer();
        p.start();
        System.out.println("finished");
    }
}


