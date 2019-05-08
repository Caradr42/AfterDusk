package ECS.Components;

import ECS.Component;
import Utility.Pair;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Abstract class of active
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public abstract class Active extends Component {

    public boolean isActive = false;
    public int idSprite;
    //contains the effects and costs
    ArrayList<Pair<String, Integer>> effects;
    ArrayList<Pair<String, Integer>> costs;
    //Contains the actives of an entity
    public static HashSet<Class> activesSet = new HashSet<Class>();

    /**
     * Constructor
     */
    public Active() {
    }

}
