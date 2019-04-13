package ECS.Systems;

import Assets.Assets;
import ECS.Components.UIEntity;
import ECS.SystemJob;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class UIEntitiesSystem extends SystemJob{
    
    UIEntity uiEntity;
    
    public UIEntitiesSystem(Scene scene) {
        super(scene);
        uiEntity = new UIEntity();
    }

    @Override
    public void update() {
        entities = scene.entityManager.getEntitiesWithComponents(uiEntity.getClass());
        
        for(Integer e: entities){
            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
            if(uiEntity.visible){
                uiEntity.currentFrame = uiEntity.animation[(int)(uiEntity.frameCounter) % uiEntity.animationLenght];
                
                uiEntity.frameCounter += uiEntity.speed;
                //System.out.println(sprite.speed);
            }
        }        
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiEntity.getClass());
        
        for(Integer e: entities){
            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
            
            for(int i = 0; i < uiEntity.animationsNames.size(); ++i){
                uiEntity.animations.add(Assets.animations.get(uiEntity.animationsNames.get(i)));
            }
            
            uiEntity.animation = uiEntity.animations.get(0).first;
            uiEntity.animationLenght = uiEntity.animations.get(0).second;
            
            uiEntity.currentFrame = uiEntity.animation[0];
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
