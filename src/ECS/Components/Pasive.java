package ECS.Components;

import ECS.Component;
import Utility.Pair;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Abstact class of passive
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public abstract class Pasive extends Component {

    public boolean isActive = false;
    public int idSprite;
    //contains the costs
    public ArrayList<Pair<String, Integer>> costs;
    //Contains the passives of an entity
    public static HashSet<Class> pasivesSet = new HashSet<Class>();

    public Pasive() {

    }

}
