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
import Scene.Scene;
import Signals.Listener;
import graphics.Display;
import java.util.ArrayList;
import maths.Vector2;

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
    public MainWorld(Display display) {
        super(display);
    }
    
    /**
     * Here you add entities to the game manager of this scene with their
     * respective components instances.
     */
    @Override
    protected void addEntities() {
        
        for(int i = 0; i < 50; i++){
            entityManager.createEntityWithComponents("COSO_" + Integer.toString(i),
                new Sprite("sprite_" + Integer.toString(i), 50, 50,Assets.coso),
                new Transform(new Vector2(50,50))
            );           
        }       
    }
    
    /**
     * Here you add systems to the system manager of the scene
     * only add one type of system per system type which extends SystemJob.
     */
    @Override
    protected void addSystems(){
        systemJobManager.addSystems(
            new SpriteRender(this), 
            new Movement(this)
        );
    }
}
