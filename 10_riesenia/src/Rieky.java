import java.util.Random;

public class Rieky {
    final int Count = 2;
    Rieka[] rieky = new Rieka[Count];
    Random rnd = new Random();

    public Rieky(){
        for (int i = 0; i < Count; i++) {
            rieky[i] = new Rieka();
        }
        Printer printer = new Printer();
        printer.start();
    }

    synchronized public void killAll(){
        for (int i = 0; i < rieky.length; i++) {
            rieky[i].isAlive = false;
        }
    }

    synchronized public int alive(){
        int count = 0;
        for (int i = 0; i < rieky.length; i++) {
            if(rieky[i].isAlive) count++;
        }
        return count;
    }

    synchronized public void kontroler(Rieka r){
        for (int i = 0; i < rieky.length; i++) {
            if(rieky[i] != r && rieky[i] != null) {
                if(rieky[i].index == r.index && rieky[i].isAlive){
                    r.isAlive = false;
                    System.out.println(" umrela ");
                }
            }
        }
    }

    class Rieka extends Thread {
        int index;
        boolean isAlive;

        public Rieka(){
            this.index = rnd.nextInt(80);
            this.isAlive = true;
            start();
            // run();
        }

        @Override
        public void run() {
            super.run();
            while(isAlive){
                update();
                kontroler(this);
                try {
                    Thread.sleep(100 + rnd.nextInt(300));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(" Die ");
        }

        public void update(){
            int delta = rnd.nextInt(3)-1;
            index += delta;
            if(index > 79) index = 79;
            if(index < 0) index = 0;
        }
    }

    class Printer extends Thread {
        @Override
        public void run() {
            super.run();
            int count = 0;
            while(count < 10){
                // update();
                try {
                  //  System.out.println("riadok");
                    StringBuffer riadok = new StringBuffer(
                    "                                                                                                                               "
                    );
                    int test = alive();
                    if(test == 1) count++;
                    if(rieky != null) {
                        for (int i = 0; i < rieky.length; i++) {
                            if(rieky[i].isAlive)
                                riadok.setCharAt(rieky[i].index, '#');
                        }
                    }
                    System.out.println(riadok);
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(" Printer is die ");
            killAll();
        }
    }

    public static void main(String[]args){
        Rieky r = new Rieky();
    }
}
