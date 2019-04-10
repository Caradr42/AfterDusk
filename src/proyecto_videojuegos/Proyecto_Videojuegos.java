package proyecto_videojuegos;

//import CBA.GameObjects.Player;
import ECS.EntityManager;
import ECS.Components.Sprite;
import ECS.Entity;

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
        
    public static void main(String[] args) {
       System.setProperty("sun.java2d.opengl", "true");
       MainThread mt = new MainThread("Game", 1366, 768);
       mt.start();
    }
}
