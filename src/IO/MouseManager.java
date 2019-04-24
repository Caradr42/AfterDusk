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
    
    public boolean wasLeftClick;
    public boolean wasLeftClickBlock;
    
    public boolean wasRightClick;
    public boolean wasRightClickBlock;
    
    public boolean wasLeftRelease; 
    public boolean wasLeftReleaseBlock = true;
    
    public boolean wasRightRelease;
    public boolean wasRightReleaseBlock = true;
    
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
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("press");
        /*if(e.getButton() == MouseEvent.BUTTON1){
            left = true;
            leftClick = true;
            leftRelease = false;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            right = true;
            rightClick = true;
            rightRelease = false;
        }*/
        if(e.getButton() == MouseEvent.BUTTON1){
            left = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            right = true;
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
        
        /*if(e.getButton() == MouseEvent.BUTTON1){
            left = false;
            leftClick = false;
            leftRelease = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            right = false;
            rightClick = false;
            rightRelease = true;
        }*/
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
        
        /*leftClick = false;
        rightClick = false;
        leftRelease = false;
        rightRelease = false;*/
        
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
        
        /*leftClick = false;
        rightClick = false;
        leftRelease = false;
        rightRelease = false;*/

    }
    
    public void tick(){
        if(left){
            if(!wasLeftClickBlock){
                wasLeftClick = true;
            }else{
                wasLeftClick = false;
            }
            wasLeftClickBlock = true;
        }else{
            wasLeftClickBlock = false;
        }
        
        if(right){
            if(!wasRightClickBlock){
                wasRightClick = true;
            }else{
                wasRightClick = false;
            }
            wasRightClickBlock = true;
        }else{
            wasRightClickBlock = false;
        }
        
        if(!left){
            if(!wasLeftReleaseBlock){
                wasLeftRelease = true;
            }else{
                wasLeftRelease = false;
            }
            wasLeftReleaseBlock = true;
        }else{
            wasLeftReleaseBlock = false;
        }
        
        if(!right){
            if(!wasRightReleaseBlock){
                wasRightRelease = true;
            }else{
                wasRightRelease = false;
            }
            wasRightReleaseBlock = true;
        }else{
            wasRightReleaseBlock = false;
        }
    }
    
    public boolean wasLeftClicked(){
        return wasLeftClick;
    }
    
    public boolean wasRighttClicked(){
        return  wasRightClick;
    }
    
    public boolean wasLeftReleased(){
        return wasLeftRelease;
    }
    
    public boolean wasRightReleased(){
        return wasRightRelease;
    }
}

