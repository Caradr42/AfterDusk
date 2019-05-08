package ECS.Systems;

import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.Components.UIText;
import ECS.SystemJob;
import Scene.Scene;

/**
 * Manages the userinterfaceText System
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */

public class UITextSystem extends SystemJob{
    
    UIText uiText;
    Transform textTransform;
    Sprite textSprite;
    /**
     * Constructor
     * @param scene
     * @param active 
     */
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
