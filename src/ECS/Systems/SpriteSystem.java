/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Sprite;
import ECS.SystemJob;
import Scene.Scene;
import Assets.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author carlo
 */
public class SpriteSystem extends SystemJob{

    Sprite sprite;
    
    public SpriteSystem(Scene scene) {
        super(scene);
        sprite = new Sprite();
    }

    @Override
    public void update() {
        entities = scene.entityManager.getEntitiesWithComponents(sprite.getClass());
        
        for(Integer e: entities){
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            if(sprite.visible){
                sprite.currentFrame = sprite.animation[(int)(sprite.frameCounter) % sprite.animationLenght];
                
                sprite.frameCounter+= sprite.speed;
                //System.out.println(sprite.speed);
            }
        }
        
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(sprite.getClass());
        
        //initializes al sprites by assignig the actual Buffered image animations based on the animations names
        for(Integer e: entities){
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            
            for(int i = 0; i < sprite.animationsNames.size(); ++i){
                sprite.animations.add(Assets.animations.get(sprite.animationsNames.get(i)));
            }
            
            sprite.animation = sprite.animations.get(0).first;
            sprite.animationLenght = sprite.animations.get(0).second;
            
            sprite.currentFrame = sprite.animation[0];
        }
    }
    
    

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
