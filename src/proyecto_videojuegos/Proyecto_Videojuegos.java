package proyecto_videojuegos;

//import CBA.GameObjects.Player;

import Utility.MSG;
import graphics.Display;

/**
 * Game entry point
 * 
 * @author 
 */
public class Proyecto_Videojuegos {
    
    //Test for the Component Based Archiquecture (CBA)
    /*public static void main(String[] args) {
        Player p = new Player();
        p.update();
        
    }*/
        //1366, 768
    public static void main(String[] args) {
       System.setProperty("sun.java2d.opengl", "true");
       Display display = new Display("Game", 1366, 768);
       //Display display = new Display("Game", 1920, 1054);
       Boolean initRef = false;
       MSG msg = new MSG();
       
       LoadingThread loading = new LoadingThread(display, msg);
       loading.start();
       
       MainThread mt = new MainThread(display, msg);
       mt.start();
    }
}
//1366 y 768