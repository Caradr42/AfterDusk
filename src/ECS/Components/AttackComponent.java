/**
 * Several AttackColliders for the same entity.
 * 
 * Just a tool can have this component
 */
package ECS.Components;

import ECS.Component;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class AttackComponent extends Component{
    public ArrayList<AttackCollider> arrColliders;
    
    public AttackComponent() {
        arrColliders = new ArrayList<>();
    }
    
    public AttackComponent(ArrayList<AttackCollider> arrColliders) {
        this.arrColliders = arrColliders;
    }
   
}
