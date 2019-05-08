package ECS.Components;

import ECS.Component;
import Maths.Vector2;
import Maths.Vector3;


/**
 * Class containing the data of enemy
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 02/05/2019
 * @version 1.0
 */
public class Enemy extends Component{
    public int healthBar = 0;
    public int hud = 0;
    
    public int idAttack;
    
    public Enemy() {
        idAttack = 0;
    }
    
    public Enemy(int idAttack) {
        this.idAttack = idAttack;
    }
    
    //vector to store the last distance vector that moved the enemy
    public Vector2 prev = new Vector2();
}
