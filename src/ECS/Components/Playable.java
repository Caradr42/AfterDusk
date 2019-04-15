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
 * Component of an entity that is similar in behabiour to the player, example:
 * NPC, enemies, animals, etc.
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 09/02/2019
 * @version 1.0
 */
public class Playable extends Component {
    
    int hp; 
    int inventory;
    public Vector3 velocity;
    public int collidable;



    public Playable(int hp, int inventory, Vector3 velocity, int collidable) {

        this.hp = hp;
        this.inventory = inventory;
        this.velocity = velocity;
        this.collidable = collidable;
    }

    public Playable(int hp, int inventory, Vector3 velocity) {

        this.hp = hp;
        this.inventory = inventory;
        this.velocity = velocity;
    }
    
    public Playable() {
    }
}