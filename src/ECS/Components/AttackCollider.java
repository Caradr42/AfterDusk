package ECS.Components;

import ECS.Component;
import Maths.Vector3;
import java.util.ArrayList;

/**
 * Class containing the colliders of the attacks
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class AttackCollider extends Component {

    /**
     * The x of hitbox is the width of the collider the y of hitox is the height
     * of the collider
     */
    public Vector3 hitbox;

    //a is the width of the collider if the entity with the weapon is looking to the right
    public double a;

    //is the height if looking to the right
    public double b;

    //position relative to the entity that holds the weapon
    public Vector3 relativePosition;

    public boolean active;

    //true if the collider is an area collider
    public boolean areaAttack;

    /**
     * Constructor
     */
    public AttackCollider() {
        active = true;
    }

    //a list of the IDs of the entitities that are colliding with the instance of AttackCollider
    public ArrayList<Integer> collidesWith;

    /**
     * Constructor
     *
     * @param hitbox
     * @param relPos
     */
    public AttackCollider(Vector3 hitbox, Vector3 relPos) {
        this.hitbox = hitbox;
        relativePosition = relPos;
        active = true;
        this.a = hitbox.x;
        this.b = hitbox.y;
        areaAttack = false;
        collidesWith = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param hitbox
     * @param relPos
     * @param a
     * @param b
     */
    public AttackCollider(Vector3 hitbox, Vector3 relPos, double a, double b) {
        this.hitbox = hitbox;
        relativePosition = relPos;
        active = true;
        this.a = a;
        this.b = b;
        areaAttack = false;
        collidesWith = new ArrayList<>();
    }
}
