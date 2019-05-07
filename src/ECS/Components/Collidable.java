package ECS.Components;

import ECS.Component;
import Maths.Vector3;
import java.util.HashSet;
import java.util.Set;
import org.omg.PortableInterceptor.ACTIVE;

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
    public boolean active;
    public HashSet<Integer> setCollidable;
    public boolean collisionTop;
    public boolean collisionLeft;
    public boolean collisionRight;
    public boolean collisionDown;
    
    public Collidable() {
        super();
        setCollidable= new HashSet<>();
        collisionTop=false;
        collisionLeft=false;
        collisionRight=false;
        collisionDown=false;
    }

    public Collidable(Vector3 hitbox) {
        super();
        this.hitbox = hitbox;
        active = true;
        setCollidable= new HashSet<>();
        collisionTop=false;
        collisionLeft=false;
        collisionRight=false;
        collisionDown=false;
    }
     
     
}
