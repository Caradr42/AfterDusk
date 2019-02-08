/*
* KeyManager
*
* Class that controlls everything related to the keyboard.
* With it we can controll what happen when a key is pressed and released.
*
* @author Pablo Moreno
* A00823402
* @date 25/01/2018 
* @versión 1.0 
*
*/

package IO;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
    //flag to move up the player (main flag of the up key)
    private boolean up = false;   
    
    //flag to move down the player (main flag of the down key)
    private boolean down = false;   
    
    //flag to move right the player (main flag of the left key)
    private boolean left = false;   
    
    //flag to move left the player (main flag of the right key)
    private boolean right = false;    
    
    //flag to know if the up key arrow was pressed
    private boolean UpPressed = false;      
    
    //flag to know if the down key arrow was pressed
    private boolean DownPressed = false; 
    
    //flag to know if the right key arrow was pressed
    private boolean RightPressed = false;   
    
    //flag to know if the left key arrow was pressed
    private boolean LeftPressed = false;    

    //to store all the flags for every key
    private boolean keys[]; 
    
    /**
     * Constructor of the class KeyManager.
     * It gives each instance of KeyManager a boolean array
     * to keep track of every key.
     */
    public KeyManager() {
        keys = new boolean[256];
    }

    /**
     * Method that is called when a key is typed
     * @param e is the key that was typed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    /**
     * If it is an arrow, it sets to true JUST the Pressed flag,
     * for any other key, it sets to true its only flag
     * @param e is the key that was pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //the KeyCode of the key pressed will be saved in key
        int key = e.getKeyCode();
        
        //If the up key arrow was pressed
        if(key == KeyEvent.VK_UP){
            //Set to true the pressed flag for the up key arrow
            UpPressed = true;
        }
        
        //Else if the down key was pressed
        else if(key == KeyEvent.VK_DOWN){
            //Set to true the pressed flag for the down key arrow
            DownPressed = true;
        }
        
        //Else if the left key was pressed
        else if(key == KeyEvent.VK_LEFT){
            //Set to true the pressed flag for the left key arrow
            LeftPressed = true;
        }
        
        //Else if the right key was pressed
        else if(key == KeyEvent.VK_RIGHT){
            //Set to true the pressed flag for the right key arrow
            RightPressed = true;
        }
        
        //Else for any other key
        else
        {
            //Set to true its only flag
            keys[e.getKeyCode()] = true;
        }
    }
    
    /**
     * Method that is executed when a key is released.
     * For arrows, it sets to true their main flag,
     * for any other key, their only flag will be false.
     * @param e is the key that was released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        //the KeyCode of the key pressed will be saved in key
        int key = e.getKeyCode();
        
         //If the up key arrow was released
        if(key == KeyEvent.VK_UP){
            
            //If the up key was pressed
            if(UpPressed){

                //Set its main flag to true
                keys[e.getKeyCode()] = true;
            } 
        }
        
        //If the down key arrow was released
        else if(key == KeyEvent.VK_DOWN){
            
            //If the down key was pressed
            if(DownPressed){

                //Set its main flag to true
                keys[e.getKeyCode()] = true;
            }
        }
        
        //If the left key arrow was released
        else if(key == KeyEvent.VK_LEFT){
            
            //If the left key was pressed
            if(LeftPressed){

               //Set its main flag to ture
               keys[e.getKeyCode()] = true;
            }    
        }
        
        //If the right key arrow was released
        else if(key == KeyEvent.VK_RIGHT){
            
            //If the right key was pressed
            if(RightPressed){

               //Set its main flag to true
               keys[e.getKeyCode()] = true;
            }
        }
        
        //If any other key was released
        else
        {
            //Set its flag to false
            keys[e.getKeyCode()] = false;
        }
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        /*
            If the up key arrow was first pressed, and then released
            the player will move upwards*/
        up = UpPressed && keys[KeyEvent.VK_UP];
        
        /*
            If the down key arrow was first pressed, and then released
            the player will move downwards*/
        down = DownPressed && keys[KeyEvent.VK_DOWN];
        
        /*
            If the left key arrow was first pressed, and then released
            the player will move to the left*/
        left = LeftPressed && keys[KeyEvent.VK_LEFT];
        
        /*
            If the right key arrow was first pressed, and then released
            the player will move to the right*/        
        right = RightPressed && keys[KeyEvent.VK_RIGHT];
    }
    
    /**
     * 
     * @return isDown a boolean that says if the player should move downwards
     */
    public boolean isDown() { 
        return down;
    }

    /**
     * 
     * @return isUp a boolean that says if the player should move upwards
     */
    public boolean isUp() {
        return up;
    }

    /**
     * 
     * @return isLeft a boolean that says if the player should move to the left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * 
     * @return isRight a boolean that says if the player should move to the right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * Changes the value of the pressed and main flag of the down boolean.
     * this will result in a change of value for the boolean during the
     * next tick.
     * 
     * @param down the new value for the down boolean
     */
    public void setDown(boolean down) {

        this.DownPressed = down;
        
        keys[KeyEvent.VK_DOWN] = down;
    }

    /**
     * Changes the value of the pressed and main flag of the up boolean.
     * this will result in a change of value for the boolean during the
     * next tick.
     * 
     * @param up the new value for the down boolean
     */
    public void setUp(boolean up) {

        this.UpPressed = up;
        
        keys[KeyEvent.VK_UP] = up;
    }

    /**
     * Changes the value of the pressed and main flag of the left boolean.
     * this will result in a change of value for the boolean during the
     * next tick.
     * 
     * @param left the new value for the down boolean
     */
    public void setLeft(boolean left) {

        this.LeftPressed = left;
        
        keys[KeyEvent.VK_LEFT] = left;
    }

    /**
     * Changes the value of the pressed and main flag of the right boolean.
     * this will result in a change of value for the boolean during the
     * next tick.
     * 
     * @param right the new value for the down boolean
     */
    public void setRight(boolean right) {

        this.RightPressed = right;
        
        keys[KeyEvent.VK_RIGHT] = right;
    }
}
