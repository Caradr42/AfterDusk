/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import ECS.Entity;
import Maths.Vector2;
import Maths.Vector3;

/**
 * Component of an entity that can be placed in an inventory
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class Item extends Component {
    
    public boolean isInInventory;
    //Entity inventory; //No longer needed

    public String name;
    

    /*public Item(String name, boolean bInventory, Entity inventory) {
        this.isInInventory = bInventory;
        this.inventory = inventory;
        this.name = name;
    }*/
    
    public Item(String name, boolean isInInventory) {
        this.isInInventory = isInInventory;
        this.name = name;
    }
    
    public Item() {
    }
}
