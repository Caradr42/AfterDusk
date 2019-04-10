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
        ArrayList<String> tempAnim = new ArrayList<>();
        
        Entity e = entityManager.createEntityWithComponents("InventoryPlayer", 
                new Inventory(0)
        );
        
        tempAnim.add("grass");
        
        Entity i = entityManager.createEntityWithComponents("tool", 
                new Item ("tool", true, e.getID()),
                new Transform(new Vector3(50, 50, 0)),
                new Sprite("tool", true,16, 16, 10, new ArrayList<>(Arrays.asList("grass")))
        );
        
        tempAnim = new ArrayList<>();
        tempAnim.add("player_down");
        
        entityManager.createEntityWithComponents("Player",
            new Sprite("sprite", true, 32, 32, 8, new ArrayList<>(Arrays.asList("player_down"))),
            new Transform(new Vector3(50,50, 16)),
            new Player(),
            new Playable(100, e.getID(), new Vector3()) /*,

            new Transform(new Vector3(50,50,50)),
            new Player(),
            new Playable(100, 1, new Vector3())*/

        );
        
        tempAnim = new ArrayList<>();
        tempAnim.add("grass");
        
        entityManager.createEntityWithComponents("grass",
                new Tile("grass1"),
                new Transform(new Vector3(0,0,0)),
                new Sprite("grass", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grass")))
        );
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
            new SpriteSystem(this)
        );
    }
}
