package ECS.Components;

import ECS.Component;
import java.util.ArrayList;

/**
 * Class containing several AttackColliders for the same entity.
 *
 * Just a tool can have this component
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class AttackComponent extends Component {

    public ArrayList<AttackCollider> arrColliders;

    /**
     * Constructor
     */
    public AttackComponent() {
        arrColliders = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param arrColliders
     */
    public AttackComponent(ArrayList<AttackCollider> arrColliders) {
        this.arrColliders = arrColliders;
    }

}
