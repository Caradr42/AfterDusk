/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class Inventory extends Component{
    //points to the next inventory
    public int nextInventory;
    
    //max 6
    //if it has 0, do nothing
    public ArrayList<Integer> slots = new ArrayList<>();
    
    public int availableSlots = 6;

    public Inventory() {
    }

    public Inventory(int nextInventory) {
        this.nextInventory = nextInventory;
    }
    
    
}
