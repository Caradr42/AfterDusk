package ECS.Systems;

import ECS.Components.Sprite;
import ECS.SystemJob;
import Scene.Scene;
import Assets.Assets;
import java.awt.image.BufferedImage;

/**
 * System that executes Sprite behabiour
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class SpriteSystem extends SystemJob{

    Sprite sprite;
    /**
     * Constructor
     * @param scene
     * @param active 
     */
    public SpriteSystem(Scene scene, boolean active) {
        super(scene, active);
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
            
            //render the first animation
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
