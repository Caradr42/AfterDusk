/*
* MouseManager
*
* Class that helps the user use the mouse, an action can be
* performed with the movement of the mouse, or a certain
* action like a click.
*
* @author Pablo Moreno
* A00823402
* @date 04/02/2019 
* @versión 2.0 
*
*/

package IO;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
    //To check if left has been pushed
    public boolean left;
    
    //To check if right has been pushed
    public boolean right;
    
    //To get x position of the mouse
    public int x;
    
    //To get the y position of the mouse
    public int y;
    
    /**
     * Constructor of the class MouseManager
     * 
     * By default, the left and right buttons flags are off
     */
    public MouseManager() {
        left = false;
        
        right = false;
    }

    /**
     * mouseClicked
     * 
     * Method called whenever the mouse is clicked
     * 
     * @param e the mouse event that just occurred
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

     /**
     * mousePressed
     * 
     * Method called whenever the mouse is pressed
     * 
     * @param e the mouse event that just occurred
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //If the left button was pressed
        if (e.getButton() == MouseEvent.BUTTON1) {
            //Put its boolean to true
            setLeft(true);
                       
           //Set the x position of the object to the one of the mouse
           setX(e.getX());
           
           //Set the y position of the object to the one of the one
           setY(e.getY());
        }
        
        //If the right button was pressed
        if (e.getButton() == MouseEvent.BUTTON3) {
            setRight(true);
        }
    }

    /**
     * mouseReleased
     * 
     * Method called whenever the mouse is released
     * 
     * @param e the mouse event that just occurred
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //If the left button was released
       if (e.getButton() == MouseEvent.BUTTON1) {
           //set to false its boolean
           setLeft(false);
           
           //Set the x position of the object to the one of the mouse
           setX(e.getX());
           
           //Set the y position of the object to the one of the one
           setY(e.getY());
       }
       
       if (e.getButton() == MouseEvent.BUTTON3) {
           setRight(false);
       }
    }

    /**
     * mouseEntered
     * 
     * Method called whenever the mouse enters in the screen
     * 
     * @param e the mouse event that just occurred
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    /**
     * mouseExited
     * 
     * Method called whenever the mouse gets out of the screen
     * 
     * @param e the mouse event that just occurred
     */
    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    /**
     * mouseDragged
     * 
     * Method called whenever the mouse is dragged
     * 
     * @param e the mouse event that just occurred
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
            //set to true its boolean
            setLeft(true);

            //Set the x position of the object to the one of the mouse
            setX(e.getX());

            //Set the y position of the object to the one of the one
            setY(e.getY());
        }
    }

    /**
     * mouseMoved
     * 
     * Method called whenever the mouse is moved
     * 
     * @param e the mouse event that just occurred
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Void to set the x position of the object of class MouseManager
     * 
     * @param x is the new x position of the instance
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Void to set the y position of the object of class MouseManager.
     * 
     * @param y is the new y position of the instance
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Method to get the X position of the mouse
     * 
     * @return the x position of the mouse
     */
    public int getX() {
        return x;
    }

    /**
     * Method to get the Y position of the mouse
     * 
     * @return the y position of the mouse
     */
    public int getY() {
        return y;
    }

    /**
     * A boolean to know if the left button was clicked
     * 
     * @return the boolean representing the left button
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * A boolean to know if the right button was clicked
     * 
     * @return the boolean representing the right button
     */
    public boolean isRight() {
        return right;
    }

    /**
     * Void to modify the state of the left button
     * 
     * @param left, true for clicked, 0 for released
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Void to modify the state of the right button
     * 
     * @param right, true for clicked, 0 for released
     */
    public void setRight(boolean right) {
        this.right = right;
    }
}
