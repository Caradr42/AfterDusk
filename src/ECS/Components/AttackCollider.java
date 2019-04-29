/**
 * 
 * It is called AttackComponent because there are two components in an attack
 * The one that attacks, and the attacked entity(if any)
 * 
 */
package ECS.Components;

import ECS.Component;
import Maths.Vector3;

/**
 *
 * @author pablo
 */
public class AttackCollider extends Component {

    
    /**
     * The x of hitbox is the width of the collider
     * the y of hitox is the height of the collider
     */
    public Vector3 hitbox;
    
    //a is the width of the collider if the entity with the weapon is looking to the right
    public double a;
    
    //is the height if looking to the right
    public double b;
    
    //position relative to the entity that holds the weapon
    public Vector3 relativePosition;
    
    public boolean active;
    
    public AttackCollider() {
        active = true;
    }
    
    public AttackCollider(Vector3 hitbox, Vector3 relPos) {
        this.hitbox = hitbox;
        relativePosition = relPos;
        active = true;
        this.a = hitbox.x;
        this.b = hitbox.y;
        
    }
    
    public AttackCollider(Vector3 hitbox, Vector3 relPos, double a, double b) {
        this.hitbox = hitbox;
        relativePosition = relPos;
        active = true;
        this.a = a;
        this.b = b;
    }
}
