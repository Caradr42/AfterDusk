package ECS.Systems;

import ECS.Components.Talkative;
import ECS.SystemJob;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class ConversationSystem extends SystemJob{

    public ConversationSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(Talkative.class);
        
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
