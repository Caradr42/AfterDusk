package proyecto_videojuegos;
import Assets.Assets;
import ECS.SystemJobManager;
import Scene.Scenes.*;
import Signals.Listener;
import Utility.MSG;
import graphics.Camera;
import graphics.Display;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    //Display's stuff
    public static Display display;    // to display in the game in its canvas
    public static String title;               // title of the window
    public static int width;          // width of the window
    public static int height;         // height of the window    
    //Camera
    public static Camera c;
    //thread stuff
    private Thread thread;      // thread to create the game. points to the instance of this Game as a Runnable
    private boolean running;    // to set the game running status (controls the in thread execution)
    //Frames per second stuff
    public static double tps; //ticks per second
    
    //Debug variables
    private boolean showTPS = true; //controls if the tps will be show on the console
    public static boolean showColliders = false;
    ///
    public static int fps = 60;   
    
    public static double deltaTime;
    public static double nomalizedDeltaTime;
    public static int currentFrame;
    public static double tpsBuffer;
    
    //ECS stuff
    MainWorld scene;
    //loading stuff
    //Boolean initRef;
    //LoadingThread lt;
    MSG msg;
    /**
     * to create title, width and height and set the game is still not running.
     * @param display
     * @param lt
     */
    public MainThread(Display display, MSG msg){ //Create object in init so it can be created in the tread
        this.title = display.title;
        this.width = display.width;
        this.height = display.height;
        this.display = display;
        running = false;
        tps = 0;
        deltaTime = 1 / fps;
        nomalizedDeltaTime = 1;
        currentFrame = 1;
        //this.initRef = initRef;
        //this.lt = lt;
        this.msg = msg;
    }
    
    /**
     * While boolean running is true, this will be executing itself
     * This is the cycle that makes possible the game, and it helps
     * the player to see how the character is moving.
     */
    @Override
    public void run() {
        //wait time for the logo
        try{
            Thread.sleep(1200);
        }catch (InterruptedException ex){
            
        }
        System.err.println("Main Thread: " + Thread.currentThread());
        //System.out.println("aaa");
        init(); //Initialization of objects in the thread
        //initRef = true;
        //lt.stop();
        
        
        msg.setFinished(true);
        
        /*try{
            Thread.sleep(10000);
        }catch (InterruptedException ex){
            
        }*/
        
        long timeTick = 1000000000 / fps; //time for  each tick in nanoseconds, ejm: at 50fps each tick takes 0.01666_ seconds wich is equal to 16666666.6_ nanoseconds
        
        long initTime; //current frame time
        long endTime; //the previous frame time 
        long sleepTime;
        long sleepTimeMillis;
        long remainingNanos;
        
        while (running) {
            initTime = System.nanoTime();
            tick();
            render();
            scene.entityManager.flushRemoveEntityQueue();
            
            endTime = System.nanoTime();
            sleepTime = timeTick - (endTime - initTime);
            sleepTimeMillis = sleepTime / 1000000;
            remainingNanos = sleepTime - (sleepTimeMillis * 1000000);
            
            if(sleepTimeMillis > 0 && remainingNanos >= 0){
                try {
                    Thread.sleep(sleepTimeMillis, (int)remainingNanos);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //FPS calculator
            tps = 1000000000 / (System.nanoTime() - initTime);
            deltaTime = 1 / tps;
            nomalizedDeltaTime = fps / tps;
            currentFrame++;
            if(currentFrame > fps){
                currentFrame = 1;
            }
            //System.out.println(nomalizedDeltaTime);
        }
        stop();
    }
        
    
    /**
     * Initializes objects inside the thread, and initializes all objects 
     * related to the game, including assets, scenes, 
     */
    private void init() {   
        //display = new Display(title, width, height);
        
        Assets.init(); //initializes the game assets
        c = new Camera(-width / 2, -height / 2, 4, display);
        scene = new MainWorld(display, c);
        Assets.houseTheme.play();
        /* //DEBUG : prints all listeners class attache dto the scene
        for(Listener<?> l : scene.entityManager.removeEntitiesSignal.listeners){
            System.out.println(l.getClass());
        }*/
    }
    
    /**
     * The tick method is executed each frame of the game, before render so that
     * render shows all the changes after computation.
     */
    private void tick() {
        //TODO: not only execute one scene but all scenes
        scene.display.getKeyManager().tick();
        scene.display.getMouseManager().tick();
        
        scene.systemJobManager.update();
        
        
        /*
         * This loop waits for the end of the execution of the 
         * completionExecutorService in the systemJob manager of the scene.
         * The completionExecutorService executes the scene Systems in a 
         * multithreded environment, so we need to waith for its execution.
         * that the render() is executed after all computations are finished
         */
        for(int i = 0; i < scene.systemJobManager.systemsListSize; i++){
            try {
                scene.systemJobManager.completionService.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    /**
     * The render method executes the rendering of the scene display
     * //TODO: not only execute one scene but all scenes for all displays
     */
    private void render() {
        
        //get the buffer strategy from the display
        display.bs = display.getCanvas().getBufferStrategy();
        
        /*
         * If it is null, we define one with 3 buffers to display images
         * of the game, ifn ot null, then we display every image of the but
         * after clearing the Rectangle, getting the graphic object from the
         * buffer strategy element. 
         * Show the graphic and dispose it to the trash system
        */
        if(display.bs == null) {
            /*
                This is like having 3 "screens" for eficiency.
                We display while painting the other ones.*/
            display.getCanvas().createBufferStrategy(3);
        }     
        else {
            //Getting the next buffer of one of the three
            display.g = (Graphics2D)display.bs.getDrawGraphics();
            /* This draws a grey rectangle al the back of every image so the 
             * buffer is flushed.*/
                        
            display.g.setColor(Color.GRAY);
            display.g.fillRect(0, 0, width, height);
            
            display.g.setFont(Assets.undefinedMedium);
            display.g.setColor(Color.WHITE);
            
            /*AffineTransform at = new AffineTransform();
            
            at.scale(4, 4);
            at.translate(100, 100);
            display.g.transform(at);*/
            
            c.tick(display.g);
                      
            //Here you render scenes
            scene.systemJobManager.render(display.g);
            
            c.tickUI(display.g);
            
            //Shows the frames per second(tps) on scereen on the top left corner
            if(showTPS){
                if(currentFrame % 10 == 0){
                    tpsBuffer = tps;
                }
                display.g.setColor(Color.GREEN);
                display.g.drawString(Integer.toString((int)tpsBuffer), 0, 10);
                display.g.drawString(Integer.toString((int)currentFrame), 16, 10);
                
                //display.g.drawRect(0, 0, 16, 16);
            }            
            
            display.bs.show();
            display.g.dispose();//Dispose to avoid waiting the garbage collector.
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