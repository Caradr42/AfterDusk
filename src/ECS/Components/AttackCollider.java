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

    
    public Vector3 hitbox;
    
    //position of the weapon
    public Vector3 relativePosition;
    
    public boolean active;
    
    public AttackCollider() {
        active = true;
    }
    
    public AttackCollider(Vector3 hitbox, Vector3 relPos) {
        this.hitbox = hitbox;
        relativePosition = relPos;
        active = true;
        
    }
}
