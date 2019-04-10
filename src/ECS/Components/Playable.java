/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import java.util.ArrayList;
import Maths.Vector3;

/**
 *
 * @author tanya
 */
public class Playable extends Component {
    
    int hp; 
    int inventory;
    public Vector3 velocity;

    public Playable(int hp, int inventory, Vector3 velocity) {
        this.hp = hp;
        this.inventory = inventory;
        this.velocity = velocity;
    }

    public Playable() {
    }
}
