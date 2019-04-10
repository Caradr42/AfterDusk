package ECS.Components;

import ECS.Component;
import Utility.Pair;
import java.util.ArrayList;


/**
 *
 * @author pablo
 */
public class Player extends Component{
    public int energy;
    public int maxEnergy;
    public int leftHand;
    public int rightHand;
    //current size of te actives (the maximum is 9)
    public int activesSize;
    public int passivesSize;
    /////
    public Pair<Pair<Integer,ArrayList<Integer>>, Pair<Integer,ArrayList<Integer>>> actives;
    public ArrayList<Integer> pasives;
    
    public String name = "INeedAName";

    public Player(String name) {
        leftHand = 0;
        rightHand = 0;
        activesSize = 2;
        passivesSize = 2;
        maxEnergy = 100;
        //spells that are held by the player with the right weapon
        //spells that are held by the player with the left weapon
        actives = new Pair<>(new Pair<>(leftHand, new ArrayList<Integer>(activesSize)), new Pair<>(rightHand, new ArrayList<Integer>(activesSize)));        
        pasives = new ArrayList<>(passivesSize);
    }

    public Player() {
    }    
}
