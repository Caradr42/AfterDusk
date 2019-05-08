package ECS.Components;

import ECS.Component;
import Maths.Vector3;
import Maths.Vector2;
import java.util.ArrayList;

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
public class Transform extends Component {
    
    public Integer parent;
    public ArrayList<Integer> childs;
    
    public Vector3 _previousPosition;
    public Vector3 position;
    public Vector3 relativePosition;
    public int _renderedY;
    public Vector3 _renderedPosition;

    public Transform() {
    }
    
    /**
     * Constructor for stand alone Transform
     *
     * @param position 
     */
    public Transform(Vector3 position) {
        this.position = position;
        this._previousPosition = position;
        _renderedY = (int) (position.y - position.z);
        parent = 0;
        childs = new ArrayList<>();
        _renderedPosition = new Vector3(position.x, _renderedY, position.z);
    }
    
    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param z
     */
    public Transform(double x, double y, double z) {
        this(new Vector3(x, y, z));        
    }
    
    /**
     * Constructor
     *
     * @param x
     * @param y
     */
    public Transform(double x, double y) {
        this(new Vector3(x, y, 0));        
    }
    
    /**
     * Constructor for root parents
     *
     * @param position
     * @param childs 
     */
    public Transform(Vector3 position, ArrayList<Integer> childs) {
        this(position);        
        if (childs != null) {
            this.childs = childs;
        } else {
            this.childs = new ArrayList<>();
        } 
    }
    
    /**
     * Constructor for child
     *
     * @param relativePosition
     * @param parent
     * @param childs 
     */
    public Transform(Vector3 relativePosition, Integer parent, ArrayList<Integer> childs) {
        if (childs != null) {
            this.childs = childs;
        } else {
            this.childs = new ArrayList<>();
        }
        this.relativePosition = relativePosition;
        this._previousPosition = relativePosition;
        this.position = new Vector3(relativePosition);
        _renderedY = (int)(position.y - position.z);
    
        this.position = relativePosition;
        this.parent = parent;
        _renderedPosition = new Vector3(position.x, _renderedY, position.z);
    }
    
    /**
     * Constructor
     *
     * @param relativePosition
     * @param parent
     */
    public Transform(Vector3 relativePosition, Integer parent) {
        this(relativePosition, parent, null);
    }
}
