package proyecto_videojuegos;

import Assets.Assets;
import Utility.MSG;
import graphics.Display;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import static proyecto_videojuegos.MainThread.height;


/**
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/02/2019
 * @version 1.1
 */
public class LoadingThread implements Runnable{
    
    Display display;
    
    //Assets
    BufferedImage[] tempusLogo;
    BufferedImage[] loadingIcon;
    BufferedImage[] tempusName;
    BufferedImage[] fade;
    
    //thread stuff
    private Thread thread;      // thread to create the game. points to the instance of this Game as a Runnable
    private boolean running;
    
    //Frames per second stuff
    public static double tps; //ticks per second
    private final boolean showTPS = true; //controls if the tps will be show on the console
    
    public static int fps = 30;
    
    public static double deltaTime;
    public static double nomalizedDeltaTime;
    public static int currentFrame;
    public static double tpsBuffer;
    
    //Boolean initRef;
    MSG msg;
    /**
     * Constructor
     * @param display
     * @param msg 
     */
    public LoadingThread(Display display, MSG msg) {
        this.display = display;
        
        running = false;
        tps = 0;
        deltaTime = 1 / fps;
        nomalizedDeltaTime = 1;
        currentFrame = 1;
        this.msg = msg;
    }
    
    
    @Override
    public synchronized void run() {
        init(); //Initialization of objects in the thread
        
        long timeTick = 1000000000 / fps; //time for  each tick in nanoseconds, ejm: at 50fps each tick takes 0.01666_ seconds wich is equal to 16666666.6_ nanoseconds
        
        long initTime; //current frame time
        long endTime; //the previous frame time 
        long sleepTime;
        long sleepTimeMillis;
        long remainingNanos;
        
        while (running) {
            boolean temp = msg.getFinished();
            //System.out.println(temp);
            if(temp){
                stop();
            }
            
            initTime = System.nanoTime();
            
            render();
            
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
            if(currentFrame > 32768){
                currentFrame = 1;
            }
            //System.out.println(nomalizedDeltaTime);
        }
        stop();
    }
    
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
    
    public void init(){
        tempusLogo = Assets.crop(100, 85, 11, "/Resources/Images/solarDial_animation.png", false);
        fade = Assets.crop(1, 1, 32, "/Resources/Images/gradient.png", true);
        tempusName = Assets.crop(212, 18, 1, "/Resources/Images/TempusDevelopment.png", false);
    }
    
    public synchronized void stop() {
        //System.out.println("STOP");
        //If it is running
        if(running) {
            //Stop it
            running = false;
            
            //Try thread.join
            try{
                thread.join();
                
            //If it was not successful, catch the exception 
            }catch(InterruptedException ie) {
            }
        }
    }
    
    private synchronized void render() {
        
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
                        
            display.g.setColor(Color.BLACK);
            display.g.fillRect(0, 0, display.width, display.height);
            display.g.setColor(Color.WHITE);           
            
            int dialFrame = currentFrame / 6 % 11;
            int fadeFrame;
            if (dialFrame < 5) fadeFrame = ((dialFrame * 4) + 10) % 32;
            else fadeFrame = (32 - (dialFrame * 4) + 17) % 32;
            display.g.drawImage(tempusLogo[dialFrame], display.width / 2 - 50 * 4, display.height / 2 - 42 * 4, 100 * 4, 85 * 4, null);
            //display.g.fillRect(display.width / 2 - 108 * 4, display.height / 2 + 192, 216 * 4, 22 * 4);
            display.g.drawImage(tempusName[0], display.width / 2 - 106 * 4, display.height / 2 + 200, 212 * 4, 18 * 4, null);
            display.g.drawImage(fade[fadeFrame], display.width / 2 - 50 * 4, display.height / 2 - 42 * 4, 100 * 4, 85 * 4, null);
            
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
}
