/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import ECS.Entity;
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
    
    public int hp; 


    public Integer inventory;

    public Vector3 velocity;
    
    /**
     * To indicate if the playable has a weapon.
     * This boolean does not work in the player. She has its own different 
     * booleans.
     * Other playables just have 1 weapon that is in the first position of the
     * inventory.
     */
    public boolean hasWeapon;
    
    //to indicate the direction of the playable
    public boolean right, left, up, down;



    public Playable(int hp, Integer inventory, Vector3 velocity) {
        this.hp = hp;
        this.inventory = inventory;
        this.velocity = velocity;
        hasWeapon = false;
    }

    public Playable(int hp, Integer inventory, Vector3 velocity, boolean hasWeapon) {
        this.hp = hp;
        this.inventory = inventory;
        this.velocity = velocity;
        this.hasWeapon = hasWeapon;
    }

    
    public Playable() {
        hasWeapon = false;
    }
}