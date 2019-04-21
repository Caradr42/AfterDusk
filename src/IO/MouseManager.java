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

import Maths.Vector2;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * class inn charge of managing mouse inputs from a given window
 * implements MouseListener and MouseMotionListener so that it overrides them
 * with the wanted callbacks.
 */
public class MouseManager implements MouseListener , MouseMotionListener {
    //booleans indicating if a button is pressed
    public boolean left; 
    public boolean right;
    
    public boolean leftClick; 
    public boolean rightClick;
    public boolean leftRelease; 
    public boolean rightRelease;
    
    public boolean moving = false;
    
    //the position of the mouse as a vector
    public Vector2 position;
    
    /**
     * constructor, assigns a mouse position of (0,0)
     */
    public MouseManager(){
        position = new Vector2();
    } 
  
    @Override
    public void mouseClicked(MouseEvent e) {
        
        //System.out.println("click");
    }
    
    /**
     * Executes when a mouse button is pressed.
     * Updates buttons and position when so
     * @param e mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("press");
        
        if(e.getButton() == MouseEvent.BUTTON1){
            left = true;
            leftClick = true;
            leftRelease = false;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            right = true;
            rightClick = true;
            rightRelease = false;
        }
    }

    /**
     * Executes when a mouse button is released.
     * Updates buttons and position when so
     * @param e mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("release");
        
        
        if(e.getButton() == MouseEvent.BUTTON1){
            left = false;
            leftClick = false;
            leftRelease = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            right = false;
            rightClick = false;
            rightRelease = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("enter");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("exit");
    }
    
    /**
     * Executes when a mouse button is dragged.
     * Updates buttons and position when so
     * @param e mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        position.x = (e.getX());
        position.y = (e.getY());
        
        leftClick = false;
        rightClick = false;
        leftRelease = false;
        rightRelease = false;
        
        /*
        if(e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK){
            left = true;
        }
        if(e.getButton() == MouseEvent.BUTTON2_DOWN_MASK){
            right = true;
        }*/
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position.x = (e.getX());
        position.y = (e.getY());
        
        leftClick = false;
        rightClick = false;
        leftRelease = false;
        rightRelease = false;

    }
    
    public void tick(){
        //leftClick = false;
    }
    
    public boolean wasLeftClicked(){
        boolean temp;
        temp = leftClick;
        leftClick = false;
        return temp;
    }
    
    public boolean wasRighttClicked(){
        boolean temp;
        temp = rightClick;
        rightClick = false;
        return temp;
    }
    
    public boolean wasLeftReleased(){
        boolean temp;
        temp = leftRelease;
        leftRelease = false;
        return temp;
    }
    
    public boolean wasRightReleased(){
        boolean temp;
        temp = rightRelease;
        rightRelease = false;
        return temp;
    }
}

