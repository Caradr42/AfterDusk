package ECS.Systems;

import ECS.Components.Collidable;
import ECS.Components.Inventory;
import ECS.Components.Item;
import ECS.Components.Movement;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.SystemJob;
import static ECS.SystemJob.scene;
import Maths.Vector3;
import Scene.Scene;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Manages the movement System
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class MovementSystem extends SystemJob {

    Transform transform;
    Movement movement;
    Player player;
    Sprite sprite;
    Playable playable;
    //borders
    int leftBorder = 80 - 32;
    int rightBorder = 80;
    int upperBorder = 50;
    int downBorder = 50 - 32;

    /**
     * Constructor
     *
     * @param scene
     * @param active
     */
    public MovementSystem(Scene scene, boolean active) {
        super(scene, active);
        entities = new ArrayList<>();
        transform = new Transform();
        player = new Player();
        sprite = new Sprite();
        movement = new Movement();
        //rightHand = new Tool();
        playable = new Playable();
    }

    @Override
    public void update() {
        Collidable collision = new Collidable();
        for (Integer e : entities) {
            player = scene.entityManager.getEntityComponentInstance(e, player.getClass());
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            playable = scene.entityManager.getEntityComponentInstance(e, playable.getClass());
            movement = scene.entityManager.getEntityComponentInstance(e, movement.getClass());

            collision = scene.entityManager.getEntityComponentInstance(e, collision.getClass());
            boolean CollisionCheck = false;
            //Check if is empty the HashSet with the ids of collision bw entity-tiles

            if (!collision.setCollidable.isEmpty()) {
                CollisionCheck = true;
            }

            Vector3 v3 = new Vector3();
            v3 = movement.velocity;

            //Check collision
            if (CollisionCheck) {
                v3.x = (v3.x * -.5);
                v3.y = (v3.y * -.5);
                
                if (collision.collisionLeft) {
                    transform.position.x = transform.position.x - 1;
                }
                if (collision.collisionRight) {
                    transform.position.x = transform.position.x + 1;
                }
                if (collision.collisionTop) {
                    transform.position.y = transform.position.y - 1;
                }
                if (collision.collisionDown) {
                    transform.position.y = transform.position.y + 1;
                }
                Assets.Assets.collisionWood.play();
            }
            
            boolean check=true;
                    
            if(!scene.display.keyManager.right&&!scene.display.keyManager.up&&!scene.display.keyManager.down&&!scene.display.keyManager.left){
                check=false;
            }
                    
            if(scene.display.keyManager.right&&scene.display.keyManager.up){
                check=true;
            }
            if(check&&scene.display.keyManager.left){
                v3.x = -(v3.x * -.5)*1.9;
            }

            transform.position.set(transform.position.add(v3));
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(transform.getClass(), player.getClass(), sprite.getClass());
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

}
