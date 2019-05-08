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
    

    
    //It is better to have booleans too to avoid a possible error in the random assignation of IDs
    //These are to know if the player has a tool equiped in any hand
    public boolean boolRight;
    public boolean boolLeft;
    
    //true if right
    public boolean rightOrLeft = false;

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
    
    public Integer uiText;
    public UIText _UIText;
        
    public int idAttack;
    //passives that are currently active
    //public ArrayList<Integer> pasives;


    public Player(String name) {
        //Initializing the values for a new player       
        activesSize = 2;
        passivesSize = 2;       
        boolRight = true;
        uiText = 0;
        _UIText = null;
    }

    public Player(String name, Integer LRInventory, Integer pasivesInventory, Integer activesInventory, Integer uiText, Integer idAttack) {
        //Initializing the values for a new player                       
        this.LRInventory = LRInventory;
        this.pasivesInventory = pasivesInventory;
        this.activesInventory = activesInventory;
        this.idAttack = idAttack;
        this.uiText = uiText;
        boolRight = false;
        
    }

    public Player() {
        boolRight = false;
    }    
}