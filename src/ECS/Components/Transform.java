package ECS.Components;

import ECS.Component;
import Maths.Vector3;
import Maths.Vector2;

/**
 * Component that contain the position data of the entity
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class Transform extends Component{
    
    public Vector3 position;

    public Transform() {
    }

    public Transform(Vector3 position) {
        this.position = position;
    }    
}
