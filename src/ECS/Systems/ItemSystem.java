package ECS.Systems;

import ECS.Components.Collidable;
import ECS.Components.Item;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.SystemJob;
import Scene.Scene;

/**
 * Manages the item System
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class ItemSystem extends  SystemJob{
    Sprite sprite;
    Item item;
    Transform transform;
    Collidable collidable;
    /**
     * Constructor
     * @param scene
     * @param active 
     */
    public ItemSystem(Scene scene, boolean active) {
        super(scene, active);
        this.item = new Item();
        this.sprite = new Sprite();
        this.transform = new Transform();
        this.collidable = new Collidable();
    }

    @Override
    public void update() {
        for(Integer e: entities){
            item = scene.entityManager.getEntityComponentInstance(e, item.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            collidable = scene.entityManager.getEntityComponentInstance(e, collidable.getClass());
            transform = scene.entityManager.getEntityComponentInstance(e, Transform.class);
            if(item.isInInventory){
                sprite.visible = false;
                collidable.active = false;
            }else{
                sprite.visible = true;
                collidable.active = true;
            }
        
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(item.getClass(), sprite.getClass(), transform.getClass(), collidable.getClass());
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
