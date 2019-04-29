
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;
import ECS.Component;
import ECS.Entity;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Component of an entity considered as a tool usable by the player
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class Tool extends Component{
    
    public ArrayList<Integer> actives;
    Entity pasives;
    public ArrayList<Integer> unlockedActives;

    public Tool( Entity pasives, ArrayList<Integer> actives, ArrayList<Integer> unlockedActives) {
        this.actives = actives;
        this.pasives = pasives;
        this.unlockedActives = unlockedActives;
    }
    
    public Tool() {
    }
}


