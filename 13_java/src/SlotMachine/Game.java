package SlotMachine;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Applet
    implements KeyListener
{

    public Game()
    {
        rollers = new Roller[3];
        money = 10D;
        win = 0.0D;
        showHelp = false;
        stillRolling = 0;
    }

    public void init()
    {
        resize(744, 475);
        bufImage = new BufferedImage(getWidth(), getHeight(), 2);
        slotMachineImage = getImage(getDocumentBase(), "Images/slot-machine-bg.png");
        for(int i = 0; i < rollers.length; i++)
        {
            rollers[i] = new Roller(this, 154 + i * 140);
            rollers[i].start();
        }

        rollButton = new MyImageButton(this, 283, 390, "roll-button");
        helpButton = new MyImageButton(this, 483, 390, "rules-button");
        imgHelp = getImage(getDocumentBase(), "Images/help.png");
        int a = imgHelp.getHeight(this);
        randomizer = new Random();
        addKeyListener(this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void showHelp()
    {
        showHelp = true;
         repaint();
    }

    public void hideHelp()
    {
        showHelp = false;
        repaint();
    }

    public void startRolling()
    {
        if(stillRolling == 0)
        {
            money -= 0.5D;
            win = 0.0D;
            stillRolling = rollers.length;
            Roller aroller[];
            int j = (aroller = rollers).length;
            for(int i = 0; i < j; i++)
            {
                Roller roller = aroller[i];
                roller.roll(randomizer.nextInt(50) + 150);
            }

        }
    }

    private double getEuro(int n)
    {
        switch(n)
        {
        case 1: // '\001'
            return 0.050000000000000003D;

        case 2: // '\002'
            return 0.20000000000000001D;

        case 3: // '\003'
            return 0.5D;

        case 4: // '\004'
            return 1.0D;

        case 5: // '\005'
            return 2D;
        }
        return 0.0D;
    }

    public void endRolling()
    {
        if(stillRolling == 0)
            if(rollers[0].getValue() == rollers[1].getValue() && rollers[1].getValue() == rollers[2].getValue())
            {
                money += win = getEuro(rollers[0].getValue()) * 8D;
            } else
            {
                if(rollers[0].getValue() == rollers[1].getValue())
                    money += win = getEuro(rollers[0].getValue()) * 2D;
                if(rollers[0].getValue() == rollers[2].getValue())
                    money += win = getEuro(rollers[0].getValue()) * 2D;
                if(rollers[1].getValue() == rollers[2].getValue())
                    money += win = getEuro(rollers[1].getValue()) * 2D;
            }
    }

    public void paint(Graphics g)
    {
    	//System.out.println("paint");
        Graphics bg = bufImage.getGraphics();
        bg.setColor(Color.WHITE);
        bg.fillRect(0, 0, getWidth(), getHeight());
        for(int i = 0; i < rollers.length; i++)
            rollers[i].paint(bg);
        //
        bg.drawImage(slotMachineImage, 0, 0, this); 
        //
        Font font = new Font("Monospaced", 1, 20);
        bg.setFont(font);
        bg.setColor(Color.WHITE);
        rollButton.paint(bg);
        helpButton.paint(bg);
        bg.drawString(String.format("Stav \372\u010Dtu: %02.2f \u20AC", new Object[] {
            Double.valueOf(money)
        }), 100, 320);
        if(win > 0.0D)
            bg.drawString(String.format("V\375hra: %02.2f \u20AC", new Object[] {
                Double.valueOf(win)
            }), 350, 320);
        if(showHelp)
            bg.drawImage(imgHelp, 110, 65, this);
        g.drawImage(bufImage, 0, 0, this);
    }

    public void keyPressed(KeyEvent evt)
    {
        if(evt.getKeyCode() == 32 || evt.getKeyCode() == 10)
            startRolling();
    }

    public void keyReleased(KeyEvent keyevent)
    {
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    private final int WIDTH = 744;
    private final int HEIGHT = 475;
    private BufferedImage bufImage;
    private Image slotMachineImage;
    private Roller rollers[];
    private double money;
    private double win;
    MyImageButton rollButton;
    MyImageButton helpButton;
    private Image imgHelp;
    private boolean showHelp;
    int stillRolling;
    Random randomizer;
}
