package graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/*
* Display
*
* Class that creates the window that is shown to the user
* in which the player and background are displayed, thanks to
* the canvas that is also created in this class.
*
* @author Pablo Moreno
* A00823402
* @date 25/01/2018 
* @versión 1.0 
*
*/
public class Display {
    //this is the app class
    private JFrame jframe; 
    
    //to display images
    private Canvas canvas; 
    
    //title of the window
    private String title; 
    
    //width of the window
    private int width; 
    
    //height of the window
    private int height; 
    
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
        //Setting the title that was passed as parameter in the object of class Display
        this.title = title;
        
        //Setting the width that was passed as parameter in the object of class Display
        this.width = width;
        
        //Setting the height that was passed as parameter in the object of class Display
        this.height = height;
        
        //Creating the window and canvas
        createDisplay();
    }
    
    /*
    * creates the app and the canvas and add the canvas to the window app
    */
    public void createDisplay(){
       //create the app window
       jframe = new JFrame(title);
        
       //set the size of the window
       jframe.setSize(width, height);
        
       //setting not resizable, visible and possible to close
       jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
            is the one that can hear the keyboard*/
       canvas.setFocusable(false);
       
       /*
            adding the canvas to the app window and packing to
            get the right dimensions*/
       jframe.add(canvas);
       
       jframe.pack();
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
}