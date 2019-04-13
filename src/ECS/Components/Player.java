package ECS.Components;

import ECS.Component;
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
    
    //references the weapon that the player has equiped in the left hand, if any
    public int leftHand;
    
    //references the weapon that the player has equiped in the right hand, if any
    public int rightHand;
    
    //current size of the actives (the maximum is 9)
    public int activesSize;
    
    //current size of the passives (the maxium is 9)
    public int passivesSize;
    
    /**
     * The first element of the pair is for the left hand, the second for the right hand.
     * It is an integer referencing the weapon ID, and the actives selected from the weapon
     */
    public Pair<Pair<Integer,ArrayList<Integer>>, Pair<Integer,ArrayList<Integer>>> actives;
    
    //passives that are currently active
    public ArrayList<Integer> pasives;
    
    //The name of the player
    public String name = "INeedAName";

    public Player(String name) {
        //Initializing the values for a new player
        leftHand = 0;
        
        rightHand = 0;
        
        activesSize = 2;
        
        passivesSize = 2;
        
        maxEnergy = 100;
        
        actives = new Pair<>(new Pair<>(leftHand, new ArrayList<Integer>(activesSize)), new Pair<>(rightHand, new ArrayList<Integer>(activesSize))); 
        
        pasives = new ArrayList<>(passivesSize);
    }

    public Player() {
    }    
}