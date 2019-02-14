package proyecto_videojuegos;

import Scene.Scene;
import Scene.Scenes.MainWorld;
import graphics.Display;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;   

/**
 * «Singleton»
 * Thread from which the game will run 
 *
 * @author Pablo Moreno
 * A00823402
 * @date 25/01/2018 
 * @versión 1.0 
 */
public class MainThread implements Runnable{
    
    //Necessary graphic objects 
    //private BufferStrategy bs;  // to have several buffers when displaying in a canvas
    //private Graphics g;         // to paint objects in the display canvas
    //display stuff
    private Display display;    // to display in the game in its canvas
    String title;               // title of the window
    final private int width;          // width of the window
    final private int height;         // height of the window    
    //thread stuff
    private Thread thread;      // thread to create the game. points to the instance of this Game as a Runnable
    private boolean running;    // to set the game running status (controls the in thread execution)
    private double tps; //ticks per second
    private final boolean showTPS = false; //controls if the tps will be show on the console
    int fps = 60;
    
    //ECS stuff
    MainWorld scene;
    
    /**
     * to create title, width and height and set the game is still not running.
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public MainThread(String title, int width, int height){ //Create object in init so it can be created in the tread
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
    }
    
    /**
     * While boolean running is true, this will be executing itself
     * This is the cycle that makes possible the game, and it helps
     * the player to see how the character is moving.
     */
    @Override
    public void run() {
        init(); //Initialization of objects in the thread
        
        double timeTick = 1000000000 / fps; //time for  each tick in nanoseconds, ejm: at 50fps each tick takes 0.01666_ seconds wich is equal to 16666666.6_ nanoseconds
        double delta = 0; 
        long now; //current frame time
        long lastTime = System.nanoTime(); //the previos frame time 
        double initTickTime = lastTime;
        while (running) {            
            now = System.nanoTime();
            delta += (now - lastTime) / timeTick;
            lastTime = now;
            
            //delta acumulates enogh tick fractions until a tick is completed and we can now advance in the tick
            if(delta >= 1){
                
                tick();
                render();
                
                delta--;
                tps = 1000000000 / (now - initTickTime);
                if(showTPS){System.out.println("tps: " + tps);}
                initTickTime = now;
            }
        }
        stop();
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
     * Initializes objects inside the thread, because init() is called from run()
     */
    private void init() {
        display = new Display(title, width, height);
        scene = new MainWorld();
        scene.systemJobManager.init();
    }
    
    /**
     * Void method that moves everything in the game with each execution
     * but it does not show it to the user.
     */
    private void tick() {
        display.getKeyManager().tick();
        scene.systemJobManager.update();
    }
        
    /**
     * Void that shows to the user every tick or step took
     * in the game, like the movement of the player.
     */
    private void render() {
        //get the buffer strategy from the display
        display.bs = display.getCanvas().getBufferStrategy();
        
        /*
            If it is null, we define one with 3 buffers to display images
            of the game, ifn ot null, then we display every image of the but
            after clearing the Rectangle, getting the graphic object from the
            buffer strategy element. 
            Show the graphic and dispose it to the trash system*/
        if(display.bs == null) {
            /*
                This is like having 3 "screens" for eficiency.
                We display while painting the other ones.*/
            display.getCanvas().createBufferStrategy(3);
        }     
        else {
            //Getting the next buffer of one of the three
            display.g = display.bs.getDrawGraphics();
            
            //Here you render entities
            scene.systemJobManager.render(display.g);
            
            display.bs.show();
            display.g.dispose(); //Dispose to avoid waiting the garbage collector.
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