package SlotMachine;

import java.awt.*;
import java.awt.event.*;

public class MyImageButton
    implements MouseListener, MouseMotionListener
{

    public MyImageButton(Game rgame, int x, int y, String imgFile)
    {
        this.x = x;
        this.y = y;
        name = imgFile;
        game = rgame;
        imgButtonUp = game.getImage(game.getDocumentBase(), (new StringBuilder("Images/")).append(imgFile).append(".png").toString());
        imgButtonDown = game.getImage(game.getDocumentBase(), (new StringBuilder("Images/")).append(imgFile).append("-down.png").toString());
        imgButton = imgButtonUp;
        game.addMouseListener(this);
        game.addMouseMotionListener(this);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return imgButton.getWidth(game);
    }

    public int getHeight()
    {
        return imgButton.getHeight(game);
    }

    public void paint(Graphics g)
    {
        g.drawImage(imgButton, x, y, game);
    }

    boolean thisButton(MouseEvent evt)
    {
        return evt.getX() > x && evt.getX() < x + getWidth() && evt.getY() > y && evt.getY() < y + getHeight();
    }

    public void mouseClicked(MouseEvent evt)
    {
        if(thisButton(evt))
            if(name == "rules-button")
                game.showHelp();
            else
            if(name == "roll-button")
                game.startRolling();
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent evt)
    {
        game.hideHelp();
        if(thisButton(evt))
        {
            imgButton = imgButtonDown;
            game.repaint();
        }
    }

    public void mouseReleased(MouseEvent evt)
    {
        if(thisButton(evt))
        {
            imgButton = imgButtonUp;
            game.repaint();
        }
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
    }

    public void mouseMoved(MouseEvent evt)
    {
        if(game.helpButton.thisButton(evt) || game.rollButton.thisButton(evt))
            game.setCursor(new Cursor(12));
        else
        if(game.getCursor().getType() != 0)
            game.setCursor(new Cursor(0));
    }

    private int x;
    private int y;
    private String name;
    private Image imgButton;
    private Image imgButtonUp;
    private Image imgButtonDown;
    private Game game;
}
