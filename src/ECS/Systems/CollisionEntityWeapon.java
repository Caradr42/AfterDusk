package ECS.Systems;

import ECS.Components.AttackCollider;
import ECS.Components.AttackComponent;
import ECS.Components.Collidable;
import ECS.Components.Enemy;
import ECS.Components.Inventory;
import ECS.Components.Item;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Tool;
import ECS.Components.Transform;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * CollisionEntityWeapon Checks the collisions between the weapons and the other
 * collidables
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class CollisionEntityWeapon extends SystemJob {

    private AttackCollider attackCollider;
    private static AttackComponent attackComponent;
    private static Collidable collidable;
    private static Transform transform;
    private static Tool tool;

    private static boolean judge = false;

    private ArrayList<Integer> arrAttack;
    private ArrayList<Integer> arrCollidable;
    private static Rectangle rectangle;

    private int playerID;
    private Player player;

    //List to store the rectangles of the colliders
    private static ArrayList<Rectangle> rects;

    /**
     * Constructor
     *
     * @param scene
     * @param active
     */
    public CollisionEntityWeapon(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {

        //For each entity with the AttackComponent
        for (Integer i : arrAttack) {
            //System.out.println(scene.entityManager.getEntityByID(i).getName());

            //if the weapon has not a -1
            if (scene.entityManager.getEntityComponentInstance(i, Tool.class).currentActive != - 1) {

                ArrayList<AttackCollider> arrColliders = scene.entityManager.getEntityComponentInstance(i, attackComponent.getClass()).arrColliders;

                //Check if it collides with a collidable entity
                for (Integer j : arrCollidable) {
                    //if they are not the same and i is not a weapon of j, and if j is alive
                    if (!Objects.equals(i, j) && !isWeaponOf(i, j) && scene.entityManager.getEntityComponentInstance(j, Playable.class).isAlive) {

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
        rectangle = new Rectangle();
        player = new Player();
        initializeEntities();

        rects = new ArrayList<>();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    /**
     * initializeEntities
     *
     * Initializes all the entities
     */
    private void initializeEntities() {
        arrAttack = new ArrayList<>();
        arrCollidable = new ArrayList<>();
        entities = new ArrayList<>();

        arrAttack = scene.entityManager.getEntitiesWithComponents(attackComponent.getClass(), tool.getClass());

        arrCollidable = scene.entityManager.getEntitiesWithComponents(collidable.getClass(), Playable.class);

        for (int i = 0; i < arrAttack.size(); i++) {

            tool = scene.entityManager.getEntityComponentInstance(arrAttack.get(i), tool.getClass());

            //A -1 means that the entity is not attacking, at least with that weapon
            if (tool.currentActive != -1) {
                entities.add(arrAttack.get(i));
            }
        }

        //adding collidables to entities
        for (int i = 0; i < arrCollidable.size(); i++) {
            entities.add(arrCollidable.get(i));
        }
    }

    /**
     *
     * @param i is the entity ID of the tool that is attacking
     * @param j ID of the entity being attacked by i
     * @return
     */
    private static ArrayList<AttackCollider> checkAttack(int i, int j) {

        Transform wpnTrans = new Transform();

        //List of the AttackColliders that are colliding with the entity j
        ArrayList<AttackCollider> areColliding = new ArrayList<>();

        //AttackComponent that contains all the AttackColliders of the attacker
        AttackComponent attacks = scene.entityManager.getEntityComponentInstance(i, attackComponent.getClass());

        //Get the transform of each tool/weapon
        wpnTrans = scene.entityManager.getEntityComponentInstance(i, transform.getClass());

        //position of the collidable entity
        Transform collTrans = scene.entityManager.getEntityComponentInstance(j, transform.getClass());
        Collidable collColl = scene.entityManager.getEntityComponentInstance(j, collidable.getClass());

        Rectangle collRect = new Rectangle((int) collTrans.position.x, (int) collTrans._renderedY, (int) collColl.hitbox.x, (int) collColl.hitbox.y);

        //for each collider of the weapon
        for (AttackCollider arrCollider : attacks.arrColliders) {

            //Get the rectangle of the weapon/attack in that collider
            Rectangle wpnRect = new Rectangle((int) (wpnTrans.position.x + arrCollider.relativePosition.x), (int) (wpnTrans._renderedY + arrCollider.relativePosition.y), (int) arrCollider.hitbox.x, (int) arrCollider.hitbox.y);
            rectangle = wpnRect;
            judge = true;

            rects.add(wpnRect);

            if (wpnRect.intersects(collRect)) {
                areColliding.add(arrCollider);

                if (!scene.entityManager.hasComponent(j, Item.class) && scene.entityManager.getEntityComponentInstance(j, Playable.class).isAlive) {
                    arrCollider.collidesWith.add(j);
                }

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

    }

    //true if i is a weapon from the collidable j
    public boolean isWeaponOf(int i, int j) {
        boolean bJudge;

        bJudge = false;

        //if j is a player
        if (scene.entityManager.hasComponent(j, Player.class)) {
            player = scene.entityManager.getEntityComponentInstance(j, Player.class);

            //inventory of the two weapons in selection of the playe
            Inventory inventory = scene.entityManager.getEntityComponentInstance(player.LRInventory, Inventory.class);
            if (inventory.slots.get(0) == i || inventory.slots.get(1) == i) {
                bJudge = true;
            }
        } //if j is an enemy
        else if (scene.entityManager.hasComponent(j, Enemy.class)) {
            Playable playable = scene.entityManager.getEntityComponentInstance(j, Playable.class);

            Inventory inventory = scene.entityManager.getEntityComponentInstance(playable.inventory, Inventory.class);

            if (inventory.slots.get(0) == i) {
                bJudge = true;
            }
        }

        return bJudge;
    }

    @Override
    public void render(Graphics2D g) {

    }

}
