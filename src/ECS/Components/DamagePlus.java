package ECS.Components;

/**
 * Class containing the data of the passive damagePlus
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 05/05/2019
 * @version 1.0
 */
public class DamagePlus extends Pasive{
    public int cost;

    public DamagePlus() {
        pasivesSet.add(this.getClass());
        cost = 50;
    }
    
    
    
}
