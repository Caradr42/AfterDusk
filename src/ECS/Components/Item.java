/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import maths.Vector2;

/**
 *
 * @author tanya
 */
public class Item extends Component {
    
    boolean bInventory;
    public Vector2 position;
    int inventory;
    String animation;
    String name;

    public Item(String name, boolean bInventory, Vector2 position, int inventory, String animation) {
        this.bInventory = bInventory;
        this.position = position;
        this.inventory = inventory;
        this.animation = animation;
        this.name = name;
    }
}
