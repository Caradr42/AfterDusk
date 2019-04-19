package ECS.Systems;

import Assets.Assets;
import ECS.Components.UIEntity;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.event.KeyEvent;

/**
 * System that executes User interface behabiour
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class UIEntitiesSystem extends SystemJob{
    
    UIEntity uiEntity;
    
    boolean PlayerInventoryBuffer;
    
    public UIEntitiesSystem(Scene scene) {
        super(scene);
        uiEntity = new UIEntity();
        PlayerInventoryBuffer = false;
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
            
            
            if(uiEntity.name.equals("Player_Inventory")){
                if(scene.display.getKeyManager().keys[KeyEvent.VK_X] || scene.display.getKeyManager().keys[KeyEvent.VK_I] || scene.display.getKeyManager().keys[KeyEvent.VK_Q]){
                    if(PlayerInventoryBuffer == false){
                        if(uiEntity.visible){
                            uiEntity.visible = false;
                        }else{
                            uiEntity.visible = true;
                        }
                    }
                    PlayerInventoryBuffer = true;
                }else{
                    PlayerInventoryBuffer = false;
                }
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
