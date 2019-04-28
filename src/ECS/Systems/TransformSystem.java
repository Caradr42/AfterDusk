package ECS.Systems;

import ECS.Components.Transform;
import ECS.SystemJob;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class TransformSystem extends SystemJob{
    
    Transform transform;
    Transform parentTransform;
    
    public TransformSystem(Scene scene) {
        super(scene);
        transform = new Transform();
        parentTransform = new Transform();
    }

    @Override
    public void update() {
    
        updatePositionIfChild();
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(transform.getClass());
        for(Integer e: entities){
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            transform.relativePosition = transform.position;
        }
        updatePositionIfChild();
    }
    
    public void updatePositionIfChild(){
        
        for(Integer e: entities){
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
                        
            if(transform.parent != 0){
                if(transform.position != transform.previousPosition){
                    transform.relativePosition = transform.relativePosition.add(transform.position.sub(transform.previousPosition));
                }
                
                parentTransform = scene.entityManager.getEntityComponentInstance(transform.parent, parentTransform.getClass());
                transform.position = parentTransform.position.add(transform.relativePosition);
            }
            transform.previousPosition = transform.position;
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
