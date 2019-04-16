/*
 *  This code is taken from the book:
 *
 *   Winder, R and Roberts, G (2007) Developing Java Software, third edition, Wiley.
 *
 *  Copyright (c) 1996-2007 Russel Winder and Graham Roberts.
 *
 *  This code is licenced for use under the GNU General Public Licence (GPL), see licence.txt.
 */
import java.awt.BorderLayout ;
import java.awt.Dimension ;
import java.awt.FlowLayout ;
import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;
import javax.swing.JButton ;
import javax.swing.JFrame ;
import javax.swing.JPanel ;
import javax.swing.SwingUtilities ;
/**
 *  This class provides a basic frame, with a quit button, that can be used to create simple
 *  graphics programs.
 *
 *  @version 2005-08-22
 *  @author Graham Roberts
 *  @author Russel Winder
 */
public class DrawFrame extends JFrame {
  /** 
   *  Construct a <code>DrawFrame</code> with a given title.  This constructor is private to
   *  avoid any instances of this class or subclasses being made directly.  The way of using
   *  this class is via the static <code>display</code> method.
   */
  private DrawFrame ( final String title ) {
    //  Initialize the JFrame ensuring the titlebar is set.
    super ( title ) ;
    //  Set up the quit button with its listener.
    final JButton quitButton = new JButton ( "Quit" ) ;
    quitButton.addActionListener ( new ActionListener ( ) {
        public void actionPerformed ( final ActionEvent ae ) { System.exit ( 0 ) ; }
      } ) ;
    //  Put the buttons into a JPanel so that the button remains the same size independent of
    //  any frame resizing.  The JPanel absorbs all the changes leaving the button as is.
    final JPanel buttonPanel = new JPanel ( new FlowLayout ( ) ) ;
    buttonPanel.add ( quitButton ) ;
    //  Create the contents of the frame. Use BorderLayout with the top (Center) part being the
    //  drawing area and the bottom (South) strip holding a quit button.  Do not use the North,
    //  East and West parts.
    setLayout ( new BorderLayout ( ) ) ;
    add ( buttonPanel , BorderLayout.SOUTH ) ;
    //  Ensure that window close events from the window manager are caught and acted upon by
    //  closing the window and exiting the application.
    setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE ) ;
  }
  /**
   *  Position a <code>DrawFrame</code> in the centre of the screen.
   */
  private void centreOnScreen ( ) {
    final Dimension displaySize = getToolkit ( ).getScreenSize ( ) ;
    final Dimension windowSize = getSize ( ) ;
    int x = ( displaySize.width - windowSize.width ) / 2 ;
    int y = ( displaySize.height - windowSize.height ) / 2 ;
    if ( x < 0 ) { x = 0 ; }
    if ( y < 0 ) { y = 0 ; }
    setLocation ( x , y ) ;
  }
  /**
   *  Start a <code>DrawFrame</code>/<code>DrawPanel</code> based application using the standard
   *  Sun idiomatic (i.e. safe) start up sequence for AWT/Swing code.
   *
   *  @param frameLabel the <code>String</code> to appear as the frame title of the displayed
   *  <code>JFrame</code>
   *  @param drawing reference to a (subclass of) <code>DrawPanel</code> object that is the
   *  drawing to be rendered.
   */
  public static void display ( final String frameLabel , final DrawPanel drawing ) {
    SwingUtilities.invokeLater ( new Runnable ( ) {
        public void run ( ) {
          try {
            final DrawFrame frame = new DrawFrame ( frameLabel ) ;
            frame.add ( drawing , BorderLayout.CENTER ) ;
            frame.pack ( ) ;
            frame.centreOnScreen ( ) ;
            frame.setVisible ( true ) ;
          }
          //  newInstance can generate the checked exceptions InstantiationException and
          //  IllegalAccessException which have to be caught to avoid client code having to do
          //  exception handling.  So catch all exceptions and terminate execution.  Aggressive
          //  and dictatorial yes but this should never happen for the simple drawings this
          //  class will be used for.
          catch ( Exception e ) {
            System.err.println ( "There was a problem trying to run this application." ) ;
            System.exit ( 1 ) ;
          }
        }
      } ) ;  
  }
}
