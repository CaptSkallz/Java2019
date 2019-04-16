/*
 *  This code is taken from the book:
 *
 *   Winder, R and Roberts, G (2007) Developing Java Software, third edition, Wiley.
 *
 *  Copyright (c) 1996-2007 Russel Winder and Graham Roberts.
 *
 *  This code is licenced for use under the GNU General Public Licence (GPL), see licence.txt.
 */
import java.awt.Dimension ;
import java.awt.Graphics ;
import javax.swing.JPanel ;
/**
 *  A <code>JPanel</code> with a given size, the default being 300x300.  Subclasses must
 *  override the <code>paint</code> method which has signature:
 *
 * <pre> public void paint ( final Graphics g ) </pre>
 *
 *  @version 1999-09-04
 *  @author Graham Roberts
 *  @author Russel Winder
 */
public abstract class DrawPanel extends JPanel {
  /**
   *  The width of the panel.
   */
  private final int width ;
  /**
   * The height of the panel.
   */
  private final int height ;
  /**
   *  Default constructor, uses the default size which is 300x300.
   */
  protected DrawPanel ( ) { this ( 300 , 300 ) ; }
  /**
   *  Constructor for a size determined by the user.
   */
  protected DrawPanel ( final int w , final int h ) {
    width = w ;
    height = h ;
    setPreferredSize ( new Dimension ( width , height ) ) ;
  }
  /**
   *  Accessor for the width of the panel.
   */
  public int getWidth ( ) { return width ; }
  /**
   *  Accessor for the height of the panel.
   */
  public int getHeight ( ) { return height ; }
}
