/**
 * Several AttackColliders for the same entity
 */
package ECS.Components;

import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class AttackComponent {
    public ArrayList<Integer> arrColliders;
    
    public AttackComponent() {
        arrColliders = new ArrayList<>();
    }
   
}
