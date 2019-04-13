/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import Maths.Vector2;
import Maths.Vector3;

/**
 *
 * @author tanya
 */
public class Item extends Component {
    
    boolean isInInventory;
    int inventory;
    String name;

    public Item(String name, boolean bInventory, int inventory) {
        this.isInInventory = bInventory;
        this.inventory = inventory;
        this.name = name;
    }
}
