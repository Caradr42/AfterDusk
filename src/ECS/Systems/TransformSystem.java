package ECS.Systems;

import ECS.Components.Transform;
import ECS.SystemJob;
import Maths.Vector3;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class TransformSystem extends SystemJob{
    
    Transform transform;
    Transform parentTransform;
    
    public TransformSystem(Scene scene, boolean active) {
        super(scene, active);
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
                if(transform.position != transform._previousPosition){
                    transform.relativePosition = transform.relativePosition.add(transform.position.sub(transform._previousPosition));
                }
                
                parentTransform = scene.entityManager.getEntityComponentInstance(transform.parent, parentTransform.getClass());
                transform.position = parentTransform.position.add(transform.relativePosition);
                
            }
            transform.renderedY = (int)(transform.position.y - transform.position.z);
            transform._previousPosition = transform.position;
            //transform._renderedPosition = transform.position;
            //transform._renderedPosition.y = transform.position.y - transform.position.z ;
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
