package ECS.Components;

import ECS.Component;
import Maths.Vector3;

/**
 * Component of an entity that has a hit box
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class Collidable extends Component{
    /**
     * Denotes the box of an item or a playable entity.
     * Without considering its position in the space.
     * Normally it has the same measurements as the sprite.
     */
    public Vector3 hitbox;

    public Collidable() {
    }

    public Collidable(Vector3 hitbox) {
        this.hitbox = hitbox;
    }
     
     
}
