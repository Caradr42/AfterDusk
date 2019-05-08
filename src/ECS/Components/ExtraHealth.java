package ECS.Components;

/**
 * Class containing the data passive extraHealth
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 05/05/2019
 * @version 1.0
 */
public class ExtraHealth extends Pasive {

    public int cost;

    /**
     * Constructor
     */
    public ExtraHealth() {
        pasivesSet.add(this.getClass());//adds the passives to the hashset of passives
        cost = 120; //pincrease the life
    }

}
