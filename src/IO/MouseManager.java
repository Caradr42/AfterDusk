/**
* MouseManager
*
* Class that helps the user use the mouse, an action can be
* performed with the movement of the mouse, or a certain
* action like a click.
*
* @author José Alberto González Arteaga [A01038061]
* @author Tanya Yaretzi González Elizondo [A00823408]
* @author Pablo Moreno Tamez [A00823402]
* @author Carlos Adrián Guerra Vázquez [A00823198]
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
    public boolean wasLeftReleaseBlock = false;
    
    public boolean wasRightRelease;
    public boolean wasRightReleaseBlock = false;
    
    
    //the position of the mouse as a vector
    public Vector2 position;
    
    //constructor, assigns a mouse position of (0,0)
    public MouseManager(){
        position = new Vector2();
    } 
  
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            left = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            right = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            left = false;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            right = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        position.x = (e.getX());
        position.y = (e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position.x = (e.getX());
        position.y = (e.getY());
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
            wasLeftClick = false;
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
            wasRightClick = false;
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
            wasLeftRelease = false;
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
            wasRightRelease = false;
            wasRightReleaseBlock = false;
        }
    }
    
    /**
     * Checks if the left was clicked
     * @return 
     */
    public boolean wasLeftClicked(){
        return wasLeftClick;
    }
    
    /**
     * Checks if the right was clicked
     * @return 
     */
    public boolean wasRighttClicked(){
        return  wasRightClick;
    }
    
    /**
     * Checks if the left was released
     * @return 
     */
    public boolean wasLeftReleased(){
        return wasLeftRelease;
    }
    
    /**
     * Checks if the left was released
     * @return 
     */
    public boolean wasRightReleased(){
        return wasRightRelease;
    }
}

