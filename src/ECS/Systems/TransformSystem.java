package ECS.Systems;

import ECS.Components.Transform;
import ECS.SystemJob;
import Maths.Vector3;
import Scene.Scene;

/**
 * Manages the transform System
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class TransformSystem extends SystemJob{
    
    Transform transform;
    Transform parentTransform;
    
    /**
     * Constructor
     * @param scene
     * @param active 
     */
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
    
    /**
     * updates the position if it is a child
     */
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
            transform._renderedY = (int)(transform.position.y - transform.position.z);
            transform._previousPosition = transform.position;
            
            transform._renderedPosition = new Vector3(transform.position.x, transform._renderedY, transform.position.z);
           
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
