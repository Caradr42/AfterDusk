package ECS.Components;

import ECS.Component;
import ECS.Entity;
import java.util.ArrayList;

/**
 * Component of an entity that is an inventory containing Items 
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class Inventory extends Component{
    //points to the next inventory
    public Entity nextInventory;
    
    //max 6
    //if it has 0, do nothing
    public ArrayList<Entity> slots = new ArrayList<>(6);
    
    public int size = 6;

    public Inventory() {
    }
    
    public Inventory(Entity nextInventory, int size) {
        this.size = (size > 6) ? 6 : size;        
        this.nextInventory = nextInventory;
    }
    
    public Inventory(Entity nextInventory, int size, ArrayList<Entity> slots) {
        this.size = (size > 6) ? 6 : size;        
        this.nextInventory = nextInventory;
        this.slots = slots;
    }
}
