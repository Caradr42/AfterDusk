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
        
        Entity e;
        Sprite spriteComp ;
        Transform transformComp;
        
        for(int i = 0; i < 3000; i++){
            e = entityManager.createEntity("COSO_" + Integer.toString(i));
            spriteComp = new Sprite("sprite_" + Integer.toString(i), 50, 50,Assets.coso);
            transformComp = new Transform(new Vector2(50,50));
            entityManager.addComponetToEntity(e,spriteComp);
            entityManager.addComponetToEntity(e,transformComp);
        }       
    }
    
    @Override
    protected void addSystems(){
        systemJobManager.addSystem(new SpriteRender(this));
        systemJobManager.addSystem(new Movement(this));
    }
}
