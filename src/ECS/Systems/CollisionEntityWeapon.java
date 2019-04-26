/*
 * CollisionEntityWeapon
 * Checks the collisions between the weapons and the other collidables
 */
package ECS.Systems;

import ECS.Components.AttackCollider;
import ECS.Components.AttackComponent;
import ECS.Components.Collidable;
import ECS.Components.Playable;
import ECS.Components.Tool;
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
    private Tool tool;

   
    private ArrayList<Integer> arrAttack;
    private ArrayList<Integer>arrCollidable;
    
    

    public CollisionEntityWeapon(Scene scene) {
        super(scene);
    }

    @Override
    public void update() {
        
        //For each entity with the AttackComponent
        for (Integer i : arrAttack) {

            //if the weapon has not a -1
            if (scene.entityManager.getEntityComponentInstance(i, tool.getClass()).currentActive != - 1) {

                //Check if it collides with a collidable entity
                for (Integer j : arrCollidable) {
                    //if they are not the same
                    if (!Objects.equals(i, j)) {

                        //for each collider  that collides with the entity
                        for (AttackCollider k : checkAttack(i, j)) {
                            //Do something to the collidable entity
                            executeAttack(i, j);
                        }
                    }
                }
            }
        }
        initializeEntities();
    }

    @Override
    public void init() {
        attackCollider = new AttackCollider();
        transform = new Transform();
        collidable = new Collidable();
        attackComponent = new AttackComponent();
        tool = new Tool();
        
        initializeEntities();
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

        arrAttack = scene.entityManager.getEntitiesWithComponents(attackComponent.getClass(), tool.getClass());

        arrCollidable = scene.entityManager.getEntitiesWithComponents(collidable.getClass());
        

        for(int i = 0; i < arrAttack.size(); i++) {
            
            tool = scene.entityManager.getEntityComponentInstance(arrAttack.get(i), tool.getClass());
            
            //A -1 means that the entity is not attacking, at least with that weapon
            if(tool.currentActive != -1) {
                entities.add(arrAttack.get(i));
            }
        }
        
  
        
        
        //adding collidables to entities
        for(int i = 0; i < arrCollidable.size(); i++) {
            entities.add(arrCollidable.get(i));
        }
    }
    
    /**
     * 
     * @param i is the entity ID of the tool that is attacking
     * @param j ID of the entity being attacked by i
     * @return 
     */
    private ArrayList<AttackCollider> checkAttack(int i, int j) {
        
        Transform wpnTrans = new Transform();
        
        //List of the AttackColliders that are colliding with the entity j
        ArrayList<AttackCollider> areColliding = new ArrayList<>();
        
        
        //AttackComponent that contains all the AttackColliders of the attacker
        AttackComponent attacks = scene.entityManager.getEntityComponentInstance(i, attackComponent.getClass());
        
        //Get the transform of each entity that has the weapon
        wpnTrans = scene.entityManager.getEntityComponentInstance(i, transform.getClass());
        
        //position of the collidable entity
        Transform collTrans = scene.entityManager.getEntityComponentInstance(j, transform.getClass());
        Collidable collColl = scene.entityManager.getEntityComponentInstance(j, collidable.getClass());
        
        Rectangle collRect = new Rectangle((int) collTrans.position.x, (int) collTrans.position.y, (int) collColl.hitbox.x, (int) collColl.hitbox.y);
        
        //for each collider of the weapon
        for (AttackCollider arrCollider : attacks.arrColliders) {
        
            //Get the rectangle of the weapon/attack in that collider
            Rectangle wpnRect = new Rectangle((int) (wpnTrans.position.x + arrCollider.relativePosition.x), (int) (wpnTrans.position.y + arrCollider.relativePosition.y), (int) arrCollider.hitbox.x, (int) arrCollider.hitbox.y);

            if (wpnRect.intersects(collRect)) {
                areColliding.add(arrCollider);
            }
        }

        return areColliding;
    }
    
    
     /**
     * 
     * @param weapon is the entity ID of the tool that is attacking
     * @param j ID of the entity being attacked by i
     * @return 
     */
    private void executeAttack(int weapon, int entity) {
        //get the name of the tool
        String weaponName = scene.entityManager.getEntityByID(weapon).getName();
        
        //Tool component that is attacking
        tool = scene.entityManager.getEntityComponentInstance(weapon, tool.getClass());
        
        
        //get the active that is being executed(this has to be updated in other part)
        int currentActive = scene.entityManager.getEntityComponentInstance(weapon, tool.getClass()).currentActive;

        //Playable of the entity being attacked
        Playable attackedPlay = new Playable();
        attackedPlay = scene.entityManager.getEntityComponentInstance(entity, attackedPlay.getClass());

        if ("Sword1".equals(weaponName)) {
            switch(currentActive) {
                //case 0 is always the basic attack
                case 0:
                    attackedPlay.hp -= 10;                    
                    break;
                    
                    
                case 1:
                    
                    break;
                    
                case 2:
                    
                    break;
            }
        }
        
        //The attack has been done
        tool.currentActive = -1;
        System.out.println("Attack done");
    }
    
}
