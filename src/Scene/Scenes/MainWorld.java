/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene.Scenes;

import Assets.Assets;
import ECS.Components.*;
import ECS.Entity;
import ECS.Systems.*;
import Scene.Scene;
import graphics.Display;
import maths.Vector2;

/**
 *
 * @author carlo
 */
public class MainWorld extends Scene{

    public MainWorld(Display display) {
        super(display);
    }
      
    @Override
    protected void addEntities() {
        
        //Entity e;
        //Sprite spriteComp ;
        //Transform transformComp;
        
        /*
        spriteComp = new Sprite("sprite_" + Integer.toString(i), 50, 50,Assets.coso);
        transformComp = new Transform(new Vector2(50,50));
        /*
        entityManager.addComponetToEntity(e,spriteComp);
        entityManager.addComponetToEntity(e,transformComp);
        */
        
        for(int i = 0; i < 50000; i++){
            entityManager.createEntityWithComponents("COSO_" + Integer.toString(i),
                new Sprite("sprite_" + Integer.toString(i), 50, 50,Assets.coso),
                new Transform(new Vector2(50,50))
            );           
        }       
    }
    
    @Override
    protected void addSystems(){
        systemJobManager.addSystem(new SpriteRender(this));
        systemJobManager.addSystem(new Movement(this));
    }
}
