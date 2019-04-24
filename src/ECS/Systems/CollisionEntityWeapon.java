/*
 * CollisionEntityWeapon
 * Checks the collisions between the weapons and the other collidables
 */
package ECS.Systems;

import ECS.Components.AttackCollider;
import ECS.Components.AttackComponent;
import ECS.Components.Collidable;
import ECS.Components.Transform;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author pablo
 */
public class CollisionEntityWeapon extends SystemJob{
    
    private AttackCollider attackCollider;
    private AttackComponent attackComponent;
    private Collidable collidable;
    private Transform transform;

   
    private ArrayList<Integer> arrAttack;
    private ArrayList<Integer>arrCollidable;
    
    

    public CollisionEntityWeapon(Scene scene) {
        super(scene);
    }

    @Override
    public void update() {
        
        for(Integer i : arrAttack) {
            for(Integer j : arrCollidable) {
                //if the collidable does not have an AttackCollider
                if(!Objects.equals(i, j)) {
                    if(checkAttack(i, j)) {
                        
                    }
                }
            }
        }
        initializeEntities();
    }

    @Override
    public void init() {
        initializeEntities();
        
        attackCollider = new AttackCollider();
        transform = new Transform();
        collidable = new Collidable();
        attackComponent = new AttackComponent();
    }

    @Override
    public void onCreate() {
        
    }

    @Override
    public void onDestroy() {

    }

    private void initializeEntities() {
        arrAttack = new ArrayList<>();
        arrCollidable = new ArrayList<>();
        entities = new ArrayList<>();

        arrAttack = scene.entityManager.getEntitiesWithComponents(attackComponent.getClass());

        arrCollidable = scene.entityManager.getEntitiesWithComponents(collidable.getClass());

        for(int i = 0; i < arrAttack.size(); i++) {
            entities.add(arrAttack.get(i));
        }
        
        for(int i = 0; i < arrCollidable.size(); i++) {
            entities.add(arrCollidable.get(i));
        }
    }
    
    private boolean checkAttack(int i, int j) {
        boolean result;
        //position of the hitbox of the weapon
        AttackComponent attacks = scene.entityManager.getEntityComponentInstance(i, attackComponent.getClass());
        
        //for each collider of the weapon
        for(int k = 0; k < attacks.arrColliders.size(); k++) {
            
        }
        
        Transform wpnTrans = scene.entityManager.getEntityComponentInstance(i, transform.getClass());
        AttackCollider wpnColl = scene.entityManager.getEntityComponentInstance(i, attackCollider.getClass());
        
        Rectangle wpnRect = new Rectangle((int) (wpnTrans.position.x + wpnColl.relativePosition.x), (int) (wpnTrans.position.y + wpnColl.relativePosition.y), (int) wpnColl.hitbox.x, (int) wpnColl.hitbox.y);
        
        //position of the collidable entity
        Transform collTrans = scene.entityManager.getEntityComponentInstance(j, transform.getClass());
        Collidable collColl = scene.entityManager.getEntityComponentInstance(j, collidable.getClass());
        
        Rectangle collRect = new Rectangle((int) collTrans.position.x, (int) collTrans.position.y, (int) collColl.hitbox.x, (int) collColl.hitbox.y);
        
        if(wpnRect.intersects(collRect)) {
            result = true;
        }
        
        else {
            result = false;
        }
        
        return result;
    }
    
}
