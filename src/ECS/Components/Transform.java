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
public class Transform extends Component{
    
    public Integer parent;
    public ArrayList<Integer> childs;
    
    public Vector3 previousPosition;
    public Vector3 position;
    public Vector3 relativePosition;

    public Transform() {
    }
    
    /**
     * Constructor for stand alone Transform
     * @param position 
     */
    public Transform(Vector3 position) {
        this.position = position;
        parent = 0;
        childs = new ArrayList<>();
    }
    
    public Transform(double x, double y, double z) {
        this.position = new Vector3(x, y, z);
        parent = 0;
        childs = new ArrayList<>();
    }
    
    public Transform(double x, double y) {
        this.position = new Vector3(x, y, 0);
        parent = 0;
        childs = new ArrayList<>();
    }
    
    /**
     * Constructor for root parents
     * @param position
     * @param childs 
     */
    public Transform(Vector3 position, ArrayList<Integer> childs) {
        if(childs != null){
            this.childs = childs;
        }else{
            this.childs = new ArrayList<>();
        }
        this.position = position;
        parent = 0;
    }
    
    /**
     * Constructor for child
     * @param relativePosition
     * @param parent
     * @param childs 
     */
    public Transform(Vector3 relativePosition, Integer parent, ArrayList<Integer> childs) {
        if(childs != null){
            this.childs = childs;
        }else{
            this.childs = new ArrayList<>();
        }
        this.relativePosition = relativePosition;
        this.position = relativePosition;
        this.parent = parent;
    }
}
