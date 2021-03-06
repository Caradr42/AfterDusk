package ECS.Components;

import ECS.Component;
import Maths.Vector3;

/**
 * Component of an entity to considered as part of the game world
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class WorldEntity extends Component {

    public boolean active;

    /**
     * Constructor
     */
    public WorldEntity() {
        active = true;

    }

    /**
     * Constructor
     *
     * @param active
     */
    public WorldEntity(boolean active) {
        this.active = active;
    }
}
