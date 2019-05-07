package ECS.Components;

import ECS.Component;
import ECS.Entity;
import java.util.ArrayList;
import java.util.Collections;

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
    public Integer nextInventory;
    
    //max 6
    //if it has 0, do nothing
    public ArrayList<Integer> slots = new ArrayList<>(9);
    
    public int size = 9;

    public Inventory() {
    }
    
    public Inventory(Integer nextInventory, int size) {
        this.size = (size > 9) ? 9 : size;        
        this.nextInventory = nextInventory;
        this.slots = new ArrayList<>(Collections.nCopies(size, 0));
    }
    
    public Inventory(Integer nextInventory, int size, ArrayList<Integer> slots) {
        this.size = (size > 9) ? 9 : size;        
        this.nextInventory = nextInventory;
        this.slots = slots;
        
        for(int i = slots.size(); i < size; ++i){
            this.slots.add(0);
        }
    }
}
