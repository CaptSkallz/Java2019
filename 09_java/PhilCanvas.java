import java.awt.*;

class PhilCanvas extends Canvas {

    Diners controller;

    static final int NUMPHILS = 5;
    static final int THINKING = 0;
    static final int HUNGRY = 1;
    static final int GOTRIGHT = 2;
    static final int EATING =3;
    static final int GOTLEFT = 4;

    Image[] imgs = new Image[5];


    double [] philX  = new double[NUMPHILS];
    double [] philY  = new double[NUMPHILS];
    int [] state     = new int [NUMPHILS];
    boolean[] redraw = new boolean[NUMPHILS];

    double [] chopX  = new double[NUMPHILS];
    double [] chopY  = new double[NUMPHILS];
    boolean[] untable= new boolean[NUMPHILS];

    boolean wasdeadlocked = false;

    boolean frozen = false;

    PhilCanvas(Diners controller) {
        super();
        this.controller = controller;

        MediaTracker mt;
        mt = new MediaTracker(this);

        imgs[0] = controller.getImage(controller.getDocumentBase(), "thinking.gif");
        mt.addImage(imgs[0], 0);
        imgs[1] = controller.getImage(controller.getDocumentBase(), "hungry.gif");
        mt.addImage(imgs[1], 1);
        imgs[2] = controller.getImage(controller.getDocumentBase(), "gotright.gif");
        mt.addImage(imgs[2], 2);
        imgs[3] = controller.getImage(controller.getDocumentBase(), "eating.gif");
        mt.addImage(imgs[3], 3);
        imgs[4] = controller.getImage(controller.getDocumentBase(), "gotleft.gif");
        mt.addImage(imgs[4], 4);

        try {
            mt.waitForID(0);
            mt.waitForID(1);
            mt.waitForID(2);
            mt.waitForID(3);
            mt.waitForID(4);
        } catch (java.lang.InterruptedException e) {
            System.out.println("Couldn't load one of the images");
        }
        initPlacing();
    }

    Image offscreen;
    Dimension offscreensize;
    Graphics offgraphics;

    void backdrop() {
        Dimension d = size();
	    if ((offscreen == null) || (d.width != offscreensize.width)
	                            || (d.height != offscreensize.height)) {
	        offscreen = createImage(d.width, d.height);
	        offscreensize = d;
	        offgraphics = offscreen.getGraphics();
	        offgraphics.setFont(new Font("Helvetica",Font.BOLD,18));
	    }
        offgraphics.setColor(Color.lightGray);
        offgraphics.fillRect(0, 0, size().width, size().height);
        for (int i = 0; i < NUMPHILS; i++) {
            redraw[i] = true;
        }
     }

    void drawtable() {
        offgraphics.setColor(Color.red);
        offgraphics.fillOval(105,105,90,90);
        offgraphics.setColor(Color.black);
        for(int i=0; i<NUMPHILS; i++) {
            if(untable[i]) offgraphics.fillOval((int)chopX[i],(int)chopY[i],10,10);
                //offgraphics.drawString(String.valueOf(i),(int)chopX[i],(int)chopY[i]);
        }
    }


    public void paint(Graphics g) {
        backdrop();
        update(g);
    }

    public void update(Graphics g) {
        if (wasdeadlocked) {
            backdrop();
            wasdeadlocked=false;
        }
        for (int i = 0; i < NUMPHILS; i++) {
            if (redraw[i]) {
                philPaint(offgraphics,i);
                redraw[i] = false;
            }
        }
        drawtable();
        if (deadlocked()) {
            offgraphics.setColor(Color.black);
            offgraphics.drawString("DEADLOCKED",90,130);
            wasdeadlocked=true;
        }
        g.drawImage(offscreen, 0, 0, null);
    }

   void philPaint(Graphics g,int i) {
        g.setColor(Color.lightGray);
        g.fillRect((int)philX[i], (int)philY[i],imgs[0].getWidth(this),imgs[0].getHeight(this));
        g.drawImage(imgs[state[i]], (int)philX[i], (int)philY[i], this);
   //     g.setColor(Color.black);
   //     g.drawString(String.valueOf(i),(int)philX[i]+10, (int)philY[i]+10);
   }


    synchronized void setPhil(int id,int s)  throws java.lang.InterruptedException{
        while (frozen) wait();
        state[id] = s;
        redraw[id] = true;
        repaint();
    }

    synchronized void freeze(){
        frozen = true;
    }

    synchronized void thaw() {
        frozen = false;
        notifyAll();
    }

    synchronized void setFork(int id, boolean taken) {
        untable[id]= !taken;
    }

    boolean deadlocked(){
        int i=0;
        while(i<NUMPHILS && state[i]==GOTRIGHT) ++i;
        return i==NUMPHILS;
    }

    void initPlacing() {
        double x, y;
        double radius = 105.0;
        double centerAdj = 100.0;
        double radians;

        for (int i = 0; i < NUMPHILS; i++) {
            radians = i*(2.0 * Math.PI /(double)NUMPHILS);
            philX[i] = Math.sin(radians) * radius + centerAdj;
            philY[i] = Math.cos(radians) * radius + centerAdj;
         }

        radius = 35;
        centerAdj = 145;

        for (int i = 0; i < NUMPHILS; i++) {
            radians = i*(2 * Math.PI /(double)NUMPHILS) + Math.PI/5;
            chopX[i] = Math.sin(radians) * radius + centerAdj;
            chopY[i] = Math.cos(radians) * radius + centerAdj;
            untable[i] = true;
         }
    }
} 