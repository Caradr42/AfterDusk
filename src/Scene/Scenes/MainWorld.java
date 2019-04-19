package Scene.Scenes;

import Assets.Assets;
import ECS.Components.*;
import ECS.Entity;
import ECS.SystemJob;
import ECS.Systems.*;
import Maths.Vector3;
import Scene.Scene;
import Signals.Listener;
import graphics.Camera;
import graphics.Display;
import java.util.ArrayList;
import Maths.Vector2;
import java.util.Arrays;

/**
 * Test scene. extends Scene 
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 27/02/2019
 * @version 1.0
 */
public class MainWorld extends Scene{
    
    /**
     * This scene constructor. assigns a display were this scene's entities will
     * render.
     * @param display
     * @param c
     */
    public MainWorld(Display display, Camera c) {
        super(display, c);
    }
    
    /**
     * Here you add entities to the game manager of this scene with their
     * respective components instances.
     */
    @Override
    protected void addEntities() {

        //A weird item in tht players inventory
        Entity weirdItm = entityManager.createEntityWithComponents("weird", 
                new Item ("weird", true),
                new Collidable(new Vector3(16, 16, 1)),
                new Transform(new Vector3(50, 50, 32)),
                new Sprite("weird", true, 16, 16, 10, new ArrayList<>(Arrays.asList("weird")))
        );

        //a shield in the players inventory
        Entity shieldItm = entityManager.createEntityWithComponents("shield", 
                new Tool(new Entity(0), new ArrayList<>(Arrays.asList(0)), new ArrayList<>(Arrays.asList(0))),
                new Item ("shield", true),
                new Collidable(new Vector3(16, 16, 1)),
                new Transform(new Vector3(70, 70, 28)),
                new Sprite("shield", true, 16, 16, 10, new ArrayList<>(Arrays.asList("shield")))
        );
        
        //The player's internal inventory
        Entity payerInv = entityManager.createEntityWithComponents("Player_Inventory", 
                new Inventory(new  Entity(0), 6, new ArrayList<>(Arrays.asList(weirdItm , shieldItm)))
        );
        
        Entity enemyInv = entityManager.createEntityWithComponents("Enemy_Inventory", 
                new Inventory(new  Entity(0), 5, new ArrayList<>(Arrays.asList(weirdItm)))
         );
        
        
        //The players Inventory user interface, has a reference to the player internal inventory
        Entity UIe = entityManager.createEntityWithComponents("Player_UIInventory", 
               new UIInventory("Player_Inventory", false, 240, 135, 52 , 30 , 0, new ArrayList<>(Arrays.asList("inventory")), new ArrayList<>(Arrays.asList(payerInv))) 
        );
        
        //The player, initialized with empty hands ans an inventory

        entityManager.createEntityWithComponents("Player",            
            new Transform(new Vector3(50,50, 64)),
            new Sprite("sprite", true, 32, 32, 8, new ArrayList<>(Arrays.asList("player_down","player_up","player_left","player_right"))),
            new Player(),
            new Playable(100, payerInv, new Vector3()),
            new Collidable(new Vector3(32, 32, 1))
        );

        
        entityManager.createEntityWithComponents("Enemy1", 
                new Transform(new Vector3(90, 90, 30)),
                new Sprite("enemy", true, 64, 80, 10, new ArrayList<>(Arrays.asList("enemy"))),
                new Collidable(new Vector3(64, 80, 1)),
                new Playable(300, enemyInv, new Vector3()));
        
         
        
        //draw grass grid
        for(int x = 0; x < 960; x += 16){
            for(int y = 0; y < 960; y += 16){
                entityManager.createEntityWithComponents("grass",
                        new Tile("grass" + Integer.toString(x) + "_" + Integer.toString(y)),
                        new Transform(new Vector3(x,y,16)),
                        new Sprite("grass", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grass")))
                );
            }
        }
    }
    
    /**
     * Here you add systems to the system manager of the scene
     * only add one type of system per system type which extends SystemJob.
     */
    @Override
    protected void addSystems(){
        systemJobManager.addSystems(
            new RenderSystem(this),
            new PlayerSystem(this),
            new SpriteSystem(this),
            new UIEntitiesSystem(this),
            new CollisionSystem(this)
        );
    }
}