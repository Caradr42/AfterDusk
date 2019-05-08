package ECS.Components;

import ECS.Component;
import Maths.Vector3;

/**
 * Component that allows the movement
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class Movement extends Component {

    public Vector3 velocity;
    public Vector3 resistance;

    /**
     * Constructor
     *
     * @param velocity
     */
    public Movement(Vector3 velocity) {
        this.velocity = velocity;
        resistance = new Vector3(velocity.x * 0.8, velocity.y * 0.8, 0);
    }

    /**
     * Constructor
     */
    public Movement() {
        velocity = new Vector3(2, 2, 0);
        resistance = new Vector3(1.6, 1.6, 0);
    }

}
