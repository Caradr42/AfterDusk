/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        
        Entity e = entityManager.createEntityWithComponents("InventoryPlayer", 
                new Inventory(0)
        );
        
        
        Entity i = entityManager.createEntityWithComponents("tool", 
                new Item ("tool", true, e.getID()),
                new Transform(new Vector3(50, 50, 32)),
                new Sprite("tool", true, 16, 16, 10, new ArrayList<>(Arrays.asList("weird")))
        );
        
        entityManager.createEntityWithComponents("tool", 
                new Item ("tool", true, e.getID()),
                new Transform(new Vector3(55, 55, 28)),
                new Sprite("tool", true, 16, 16, 10, new ArrayList<>(Arrays.asList("shield")))
        );
        
        
        entityManager.createEntityWithComponents("Player",
            new Sprite("sprite", true, 32, 32, 8, new ArrayList<>(Arrays.asList("player_down", "player_up" , "player_right", "player_left"))),
            new Transform(new Vector3(50,50, 64)),
            new Player(),
            new Playable(100, e.getID(), new Vector3()) /*,

            new Transform(new Vector3(50,50,50)),
            new Player(),
            new Playable(100, 1, new Vector3())*/
        );
        
         entityManager.createEntityWithComponents("Player_Inventory", 
                new UIEntity("Player_Inventory", false, 240, 135, 52 , 30 , 0, new ArrayList<>(Arrays.asList("inventory")) )
         );
        
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
            new UIEntitiesSystem(this)
        );
    }
}
