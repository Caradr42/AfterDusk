package ECS.Components;

import ECS.Component;
import maths.Vector2;

/**
 *
 * @author carlo
 */
public class Transform extends Component{
    
    public Vector2 position;

    public Transform() {
    }

    public Transform(Vector2 position) {
        this.position = position;
    }    
}
