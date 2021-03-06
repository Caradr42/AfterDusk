package IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 * class in charge of managing keyboard inputs from a given window
 * implements KeyListener so that it overrides them with the wanted callbacks.
 * Contains an array with boolean values for each key pressed true if pressed, 
 * false if not.
 * Contain an array with boolean values for each key that was released, after
 * the key got released its value will be true up until been read by using the
 * getWasPresed(key) function at which point it will be reseted to false.
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 28/01/2019
 * @version 1.0
 */
public class KeyManager implements KeyListener{
    //publicly accesible axis values
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean isE;
    public boolean space;
    
    //key array for pressed and released keys
    public boolean keys[];
    public boolean wasPressedBlock[];
    public boolean wasPressed[];
    public boolean wasReleasedBlock[];
    public boolean wasReleased[];
    /**
     * Constructor initializes the array to 256 as it is the limit to which 
     * KeyEvent constants are registered.
     */
    public KeyManager(){
        keys = new boolean[256];
        wasPressed = new boolean[256];
        wasReleased =new boolean[256];
        wasPressedBlock = new boolean[256];
        wasReleasedBlock = new boolean[256];
        Arrays.fill(wasReleasedBlock, true);
    }
    
    //pls dont use this
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
       
    /**
     * callback that sets the key array value of the key pressed to true if pressed
     * @param e parameter used by implementer
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    
    /**
     * callback that sets the key array value of the key released to false if pressed, and 
     * sets the key WasPressed array value of the key released to true when released
     * @param e parameter used by implementer 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * code to be executed once every frame. updates the axis pressed values
     */
    public void tick(){
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]; 
        isE = keys[KeyEvent.VK_E];
        space = keys[KeyEvent.VK_SPACE];
        
        for(int i = 0; i < 256; ++i){
            if(!keys[i]){
                if(!wasReleasedBlock[i]){
                    wasReleased[i] = true;
                }else{
                    wasReleased[i] = false;
                }
                wasReleasedBlock[i] = true;
            }else{
                wasReleased[i] = false;
                wasReleasedBlock[i] = false;
            }
            
            if(keys[i]){
                if(!wasPressedBlock[i]){
                    wasPressed[i] = true;
                }else{
                    wasPressed[i] = false;
                }
                wasPressedBlock[i] = true;
            }else{
                wasPressed[i] = false;
                wasPressedBlock[i] = false;
            }
        }
    }
    
    /**
     * returns if a key was pressed in any moment in the past before this method
     * was called. resets the wasPressed status of the key to false.
     * @param VK_Value A <b>KeyEvent</b> integer value for the wanted key.
     * @return Boolean indicating if the key was pressed.
     */
    public boolean getWasPresed(int VK_Value){
         boolean pressed = wasPressed[VK_Value];
         wasPressed[VK_Value] = false;
         return pressed;
    }
    
    
    public void resetKeys() {
        for(int i = 0; i < 256; i++) {
            keys[i] = false;
        }
    }
}
