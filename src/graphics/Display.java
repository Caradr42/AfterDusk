package graphics;

import static ECS.SystemJob.scene;
import IO.KeyManager;
import IO.MouseManager;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
* Display
*
* Class that creates the window that is shown to the user
* in which the player and background are displayed, thanks to
* the canvas that is also created in this class. A buffer strategy and a 
* Graphics2D object are attached to the Display so that they can be used to 
* render to the window.
*
* The display has a KeyManager and a MouseManager attached to it, from were 
* input is obtained
* @author José Alberto González Arteaga [A01038061]
* @author Tanya Yaretzi González Elizondo [A00823408]
* @author Pablo Moreno Tamez [A00823402]
* @author Carlos Adrián Guerra Vázquez [A00823198]
* @date 25/01/2018 
* @versión 1.0 
*
*/
public class Display {
    //title of the window
    public String title; 
    //window dimensions
    public int width; 
    public int height; 
    //Window & graphics stuff
    public JFrame jframe; 
    public Canvas canvas; //to display images
    public BufferStrategy bs;  // to have several buffers when displaying in a canvas
    public Graphics2D g;         // to paint objects in the display canvas
    //InputStuff
    public KeyManager keyManager;  //key manager asociated with the display
    public MouseManager mouseManager; //mouse manager asociated with the display
    
    /**
     * Initializes the values for the application game.
     * This is also the constructor of the class Display.
     * 
     * @param title to display the title of the window
     * @param width to set the width
     * @param height to set the height
     * 
     */
    public Display(String title, int width, int height){
        this.title = title; 
        this.width = width;
        this.height = height;
        
        //Creating the window and canvas
        initFrameAndCanvas();
    }
    
    /*
    * creates the app and the canvas and add the canvas to the window app
    */
    private void initFrameAndCanvas(){
       jframe = new JFrame(title);    
       jframe.setSize(width, height);
       //jframe.setUndecorated(true);
       //setting not resizable, visible and possible to close
       jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       jframe.setFocusTraversalKeysEnabled(false);
       jframe.setResizable(false);
       //since it is null, the window will be centered at the center of the screen
       jframe.setLocationRelativeTo(null);
       //Making the window visible to the user
       jframe.setVisible(true);
       
       //creating the canvas to paint and setting size
       canvas = new Canvas();
       //Adjusting the preferred size to the canvas
       canvas.setPreferredSize(new Dimension(width, height));
       //Setting the max size for the canvas
       canvas.setMaximumSize(new Dimension(width, height));
       /*
        Java should not pay attention to the canvas, since jFrame
        is the one that can hear the keyboard
       */
       canvas.setFocusable(false);
       
       /*
        adding the canvas to the app window and packing to
        get the right dimensions
       */
       jframe.add(canvas);
       jframe.pack();
       
       keyManager = new KeyManager();
       mouseManager = new MouseManager();
       
       //Asociating the input listeners to the frame
       jframe.addKeyListener(keyManager);
       jframe.addMouseListener(mouseManager);
       jframe.addMouseMotionListener(mouseManager);
       canvas.addMouseListener(mouseManager);
       canvas.addMouseMotionListener(mouseManager);
      
    }
    
    public void tick(){
        width = jframe.getWidth();
        height = jframe.getHeight();
    }
    
    /**
     *to get the jFrame of the game
     * @return jFrame
     * 
     */
    public JFrame getJframe() {
        return jframe;
    }
    
    /**
     * To get the canvas of the game
     * @return the canvas
     * 
     */
    public Canvas getCanvas(){
        return canvas;
    }

    /**
     * getKeyManager
     * @return 
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * getMouseManager
     * @return 
     */
    public MouseManager getMouseManager() {
        return mouseManager;
    }

    /**
     * getWidth
     * @return 
     */
    public int getWidth() {
        return width;
    }

    /**
     * getHeight
     * @return 
     */
    public int getHeight() {
        return height;
    }       
}