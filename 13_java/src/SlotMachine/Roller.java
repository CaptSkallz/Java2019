package SlotMachine;

import java.awt.*;

public class Roller extends Thread
{

    public Roller(Game game, int x)
    {
        y = 0;
        imgItem = new Image[5];
        speed = 1.0D;
        length = 2;
        rolling = false;
        this.game = game;
        this.x = x;
        for(int i = 0; i < 5; i++)
            imgItem[i] = game.getImage(game.getDocumentBase(), (new StringBuilder("Images/slot-item-")).append(i + 1).append(".png").toString());

        value = 2;
    }

    public void roll(int ticks)
    {
        if(!rolling)
        {
            this.ticks = ticks;
            rolling = true;
        }
    }

    public void run()
    {
        do
        {
            while(!rolling) 
                try
                {
                    sleep(100L);
                }
                catch(InterruptedException interruptedexception) { }
            if(ticks == 0)
            {
                rolling = false;
                game.stillRolling--;
                y += y % 109 <= 39 ? -(y % 109) : y % 109;
                value = 6 - (436 + y) / 109;
                if(value == 0 || value == 6)
                    value = 1;
                game.endRolling();
            }
            y -= (int)speed * (ticks-- / 4);
            if(y < -((imgItem.length - 1) * imgItem[0].getHeight(game)) - 30)
                y = 79;
            game.repaint();
             try
            {
                sleep(40L);
            }
            catch(InterruptedException interruptedexception1) { }
        } while(true);
    }

    public void paint(Graphics g)
    {
        for(int l = 0; l < length; l++)
        {
            for(int i = 0; i < imgItem.length; i++)
            {
                int yy = y + l * imgItem.length * imgItem[i].getHeight(game) + imgItem[i].getHeight(game) * i;
                g.drawImage(imgItem[i], x, yy, game);
                g.setColor(Color.BLACK);
            }

        }

    }

    public int getValue()
    {
        return value;
    }

    static final int NUM_ITEMS = 5;
    private int x;
    private int y;
    private Image imgItem[];
    private Game game;
    private double speed;
    private int length;
    private boolean rolling;
    private int ticks;
    private int value;
}
