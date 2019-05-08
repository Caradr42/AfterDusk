package proyecto_videojuegos;

//import CBA.GameObjects.Player;

import Utility.MSG;
import graphics.Display;

/**
 * Game entry point
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 25/01/2018 
 * @versión 1.0 
 */
public class Proyecto_Videojuegos {
    
        
    public static void main(String[] args) {
       System.setProperty("sun.java2d.opengl", "true");
       Display display = new Display("Game", 1066, 568);
       //Display display = new Display("Game", 1920, 1054);
       Boolean initRef = false;
       MSG msg = new MSG();
       
       LoadingThread loading = new LoadingThread(display, msg);
       loading.start();
       
       MainThread mt = new MainThread(display, msg);
       mt.start();
    }
}
