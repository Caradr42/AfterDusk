package proyecto_videojuegos;

//import CBA.GameObjects.Player;
import ECS.Systems.SpriteRender;
import ECS.EntityManager;
import ECS.Components.Sprite;
import ECS.Entity;

/**
 * Game entry point class
 * 
 * @author 
 */
public class Proyecto_Videojuegos {
    
    //Test for the Component Based Archiquecture (CBA)
    /*public static void main(String[] args) {
        Player p = new Player();
        p.update();
        
    }*/
    
    //Test for the Entity Component System (ECS)
    /*public static void main(String[] args) {
       EntityManager em;
       SpriteRender sr;
       Entity player;
       Sprite sprite;
       
       sprite = new Sprite();
       sprite.name = "Test name";
       em = new EntityManager();
       player = em.createEntity("player");
       em.addComponetToEntity(player, sprite);
       sr = new SpriteRender(em);
              
       sr.update();
       
       //gm.Update();
       System.out.println("end");
    }*/
    
    public static void main(String[] args) {
       System.setProperty("sun.java2d.opengl", "true");
       MainThread mt = new MainThread("Game", 1200, 900);
       mt.start();
    }
}
