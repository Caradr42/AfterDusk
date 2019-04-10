package ECS.Components;

import ECS.Component;
import Maths.Vector3;
import Maths.Vector2;

/**
 *
 * @author carlo
 */
public class Transform extends Component{
    
    public Vector3 position;

    public Transform() {
    }

    public Transform(Vector3 position) {
        this.position = position;
    }    
}
