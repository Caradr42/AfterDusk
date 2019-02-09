package proyecto_videojuegos;

//import CBA.GameObjects.Player;
import ECS.Systems.GameManager;
import ECS.EntityManager;
import ECS.Components.RenderComponent;
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
        p.Update();
        
    }*/
    
    //Test for the Entity Component System (ECS)
    public static void main(String[] args) {
       EntityManager em;
       GameManager gm;
       Entity player;
       RenderComponent render;
       
       render = new RenderComponent("render");
       em = new EntityManager();
       player = em.createEntity("player");
       em.addComponetToEntity(player, render);
       gm = new GameManager(em);
              
       gm.Update();
       
       //gm.Update();
       System.out.println("end");
    }
    
}
