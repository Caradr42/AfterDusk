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
    public int maxHp;
    
    public double speedScalar;

    public Integer inventory;

    public Vector3 velocity;
    
    public boolean isAlive;
    
    /**
     * To indicate if the playable has a weapon IN USE.
     */
    public boolean hasWeapon;
    
    //This should only be used is hasWeapon is true
    public int currentWeapon;
    
    //to indicate the direction of the playable
    public boolean right, left, up = true, down;

    //current energy of the player
    public int energy;

    //max energy that the player can have
    public int maxEnergy;



    public Playable(int hp, Integer inventory, double speedScalar) {
        this.hp = hp;
        maxHp = hp;
        this.inventory = inventory;
        this.velocity = new Vector3();
        hasWeapon = false;
        isAlive = true;
        maxEnergy = 100;
        energy = maxEnergy;
        this.speedScalar = speedScalar;
    }

    public Playable(int hp, Integer inventory, double speedScalar, boolean hasWeapon) {
        this.hp = hp;
        maxHp = hp;
        this.inventory = inventory;
        this.velocity = new Vector3();
        this.hasWeapon = hasWeapon;
        isAlive = true;
        maxEnergy = 100;
        energy = maxEnergy;
        this.speedScalar = speedScalar;
    }
    
        public Playable(int hp, Integer inventory, double speedScalar, boolean hasWeapon, int weapon) {
        this.hp = hp;
        maxHp = hp;
        this.inventory = inventory;
        this.velocity = new Vector3();
        this.hasWeapon = hasWeapon;
        isAlive = true;
        maxEnergy = 100;
        energy = maxEnergy;
        currentWeapon = weapon;
        this.speedScalar = speedScalar;
    }
    
    
    
        public Playable(int hp, Integer inventory, double speedScalar, int maxEnergy) {
        this(hp, inventory, speedScalar);
        
        this.maxEnergy = energy;
        isAlive = true;
        energy = maxEnergy;
    }

    
    public Playable() {
        hasWeapon = false;
        inventory = 0;
    }
}