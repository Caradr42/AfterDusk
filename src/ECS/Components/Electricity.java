package ECS.Components;

import Utility.Pair;
import java.util.ArrayList;

/**
 * Class containing data of the active electricity
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 02/05/2019
 * @version 1.0
 */
public class Electricity extends Active {

    public int cost;

    /**
     * Constructor
     */
    public Electricity() {
        activesSet.add(this.getClass()); //adds the actives to the hashset of actives
        cost = 10; //damage of the active
    }

}
