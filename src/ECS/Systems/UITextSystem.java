package ECS.Systems;

import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.Components.UIText;
import ECS.SystemJob;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class UITextSystem extends SystemJob{
    
    UIText uiText;
    Transform textTransform;
    Sprite textSprite;
    
    public UITextSystem(Scene scene, boolean active) {
        super(scene, active);
        uiText = new UIText();
        textTransform = new Transform();
        textSprite = new Sprite();
    }

    @Override
    public void update() {
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiText.getClass());
        
        for(Integer e: entities){
            uiText = scene.entityManager.getEntityComponentInstance(e, uiText.getClass());
            textTransform = scene.entityManager.getEntityComponentInstance(e, textTransform.getClass());
            textSprite = scene.entityManager.getEntityComponentInstance(e, textSprite.getClass());
            
            uiText._textTransform = textTransform;
            uiText._textSprite = textSprite;
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
