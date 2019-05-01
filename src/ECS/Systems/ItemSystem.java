package ECS.Systems;

import ECS.Components.Collidable;
import ECS.Components.Item;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.SystemJob;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class ItemSystem extends  SystemJob{
    Sprite sprite;
    Item item;
    Transform transform;
    Collidable collidable;
    
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
            
            //System.out.println(item.isInInventory);
            if(item.isInInventory){
                sprite.visible = false;
                collidable.active = false;
            }else{
                sprite.visible = true;
                collidable.active = true;
            }
            
            /*if(item.name.equals("sword1")){
                System.out.println(transform.position.x + " " + transform.position.y + " ::: " + sprite.visible + " | " + collidable.active);
            }*/
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
