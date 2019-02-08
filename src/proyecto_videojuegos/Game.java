/*
* Game
*
* Game class that controls the tick and render cycle of the whole game.
* It also adds an objet of the KeyManger class to the window.
* 
*
* @author Pablo Moreno
* A00823402
* @date 25/01/2018 
* @versión 1.0 
*
*/

package proyecto_videojuegos;


import graphics.Display;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Game implements Runnable{
    
    //To have several buffers when displaying
    private BufferStrategy bs; 
    
    //To paint objects
    private Graphics g; 
    
    //To display in the game
    private Display display; 
    
    //Title of the window
    private String title; 
    
    //width of the window
    private int width; 
    
    //height of the window
    private int height; 
    
    //thread to create the game
    private Thread thread; 
    
    //to set the game
    private boolean running; 
    
    //to use a player
    private Player player;   
    
    //Number of lifes of the player
    private int lifes;
    
    //to manage the keyboard
    private KeyManager keyManager; 
    
    //To state the number of targets in the game
    private int targetsLimit;
    
    //Counter of the targets that hit the ground
    private int failedTargets;
    
    //Limit of failedTargets per player's life
    private int failedLimit;
    
    //To store the targets
    LinkedList<Target> targets;

    /**
     * While boolean running is true, this will be executing itself
     * This is the cycle that makes possible the game, and it helps
     * the player to see how the character is moving.
     * 
     */
    @Override
    public void run() {
       //Initializing the display
       init();
       
       //frames per second
       int fps = 100;
       
       /*
            time for each tick in nano segs
            we must put it in a standard unit because each computer
            has a different behaviour*/
       double timeTick = 1000000000 / fps;
       
       //initializing delta
       double delta = 0;
       
       //define now to use inside the loop
       long now;
       
       //initializing the las time to the computer time in nanoseconds
       long lastTime = System.nanoTime();
       
       //While the game is in execution
       while(running) {
           //setting the time now to the actual time
           now = System.nanoTime();
           
           //acumulating to delta the difference between times in timeTick units
           delta += (now - lastTime) / timeTick;
           
           //updating the last time
           lastTime = now;
           
           //if delta is positive we tick the game
           if(delta >= 1) {
               //to make a change in the game
               tick();
               
               //to display the change
               render();
               
               //Reducing delta
               delta--;
           }
       }
       
       //Stop everythin when running is false
       stop();
    }
    
    /**
     * to create title, width and height and set the game is still not running.
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     * 
     */
    public Game(String title, int width, int height){
        this.title = title;
        
        this.width = width;
        
        this.height = height;
        
        running = false;
        
        keyManager = new KeyManager();
        
        targets = new LinkedList<Target>();
        
        targetsLimit = 5;
        
        failedTargets = 0;
        
        failedLimit = 10;
    }

    /**
     * To get the width of the game window.
     * @return an <code>int</code> value with the width
     * 
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * To get the height of the game window.
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Initializing the display window of the game.
     * 
     */
    private void init() {
        display = new Display(title, width, height);

    }
    
    /**
     * Void method that moves everything in the game with each execution
     * but it does not show it to the user.
     */
    private void tick() {
        //"Updating" The keyManager listener of the window
        keyManager.tick();
        
        //avancing player with collision
        player.tick();
        
        //Ticking the targets
        for(int i = 0; i < targetsLimit; i++) {
            Target target = targets.get(i);
            
            target.tick();
            
            //if the player touches the target
            if(player.intersects(target)) {
                //In the bottom part
                if(player.getY() >= target.getY() + 2 * target.getHeight() / 5 && player.getX() >= target.getX() - target.getWidth() / 2 && player.getX() <= target.getX() + target.getWidth() / 2) {
                    //reset the position of the target
                    target.resetPosition();
                }
            }
            
            //else if the target touches the ground
            else if(target.getY() >= this.getHeight() + target.getHeight()) {
                target.resetPosition();
            }
        }
    }
    
    /**
     *
     * @return  The keyManager of the window
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    /**
     * Void that shows to the user every tick or step took
     * in the game, like the movement of the player.
     * 
     */
    private void render() {
        //get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        
        /*
            If it is null, we define one with 3 buffers to display images
            of the game, ifn ot null, then we display every image of the but
            after clearing the Rectangle, getting the graphic object from the
            buffer strategy element. 
            Show the graphic and dispose it to the trash system*/
        if(bs == null) {
            /*
                This is like having 3 "screens" for eficiency.
                We display while painting the other ones.*/
            display.getCanvas().createBufferStrategy(3);
        }
        
        else {
            //Getting the next buffer of one of the three
            g = bs.getDrawGraphics();
            
            /*
                Using drawImage in this way, puts the given image in all the
                space of the game.*/
            g.drawImage(Assets.background, 0, 0, width, height, null);
            
            //Painting the buffer strategy "g"
            player.render(g);
            
            //Rendering the targets
            for(int i = 0; i < targetsLimit; i++) {
                targets.get(i).render(g);
            }
            
            bs.show();
            
            //Dispose to avoid waiting the garbage collector.
            g.dispose();
        }
            
    }
    
    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        //If it is not running
        if(!running) {
            //Set it to run
            running = true;
            
            //Create a new Thread
            thread = new Thread(this);
            
            //And start it
            thread.start();
        }
    }
    
    /**
     * stopping the thread of the game
     */
    public synchronized void stop() {
        //If it is running
        if(running) {
            //Stop it
            running = false;
            
            //Try thread.join
            try{
                thread.join();
                
            //If it was not successful, catch the exception 
            }catch(InterruptedException ie) {
                //And print it
                ie.printStackTrace();
            }
        }
    }
}