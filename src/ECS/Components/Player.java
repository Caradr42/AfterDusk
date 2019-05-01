package ECS.Components;

import ECS.Component;
import ECS.Entity;
import Utility.Pair;
import java.util.ArrayList;

/**
 * Component of the player as an entity
 * This is the class player. Here we have all the variables needed for the player.
 * Since it is an entity of the entity-component-system model, it does not need
 * anything else. The player just have by itself; weapons, energy, HP, actives, passives,
 * and a name 
 * 
 * @authors:
 * 
 *   José Alberto González Arteaga [A01038061]
 *   Tanya Yaretzi González Elizondo [A00823408]
 *   Pablo Moreno Tamez [A00823402]
 *   Carlos Adrián Guerra Vázquez [A00823198]
 * 
 *  * @date 12/04/2019
 * @version 1.0
 */
 
public class Player extends Component{
    
    //current energy of the player
    public int energy;
    
    //max energy that the player can have
    public int maxEnergy;
    
    //It is better to have booleans too to avoid a possible error in the random assignation of IDs
    //These are to know if the player has a tool equiped in any hand
    public boolean boolRight;
    public boolean boolLeft;

    public Integer LRInventory;
    
    //passives that are currently active
    public Integer pasivesInventory;
    
    public Integer activesInventory;
    
    //current size of the actives (the maximum is 9)
    public int activesSize;
    
    //current size of the passives (the maxium is 9)
    public int passivesSize;
        
    //The name of the player
    public String name = "INeedAName";
        
    //passives that are currently active
    //public ArrayList<Integer> pasives;


    public Player(String name) {
        //Initializing the values for a new player
        //leftHand = new Entity(0);
        
        //rightHand = new Entity(0);
        
        activesSize = 2;
        
        passivesSize = 2;
        
        maxEnergy = 100;
        
        //actives = new Pair<>(new Pair<>(leftHand, new ArrayList<Integer>(activesSize)), new Pair<>(rightHand, new ArrayList<Integer>(activesSize))); 
        
        //pasives = new ArrayList<>(passivesSize);
        
        boolRight = true;
    }

    public Player(String name, Integer LRInventory, Integer pasivesInventory, Integer activesInventory) {
        //Initializing the values for a new player        
        maxEnergy = 100;
        energy = maxEnergy;
               
        this.LRInventory = LRInventory;
        this.pasivesInventory = pasivesInventory;
        this.activesInventory = activesInventory;
        
        boolRight = true;

    }

    public Player() {
        boolRight = true;
    }    
}