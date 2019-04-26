
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
    
    public ArrayList<Integer> activos;
    Entity pasiva;
    public ArrayList<Integer> activosDesbloqueados ;
    
    //Current active/attack being used. If nothing is used, it has a -1
    public int currentActive;

    public Tool( Entity pasiva, ArrayList<Integer> activos, ArrayList<Integer> activosDesbloqueados) {
        this.activos = activos;
        this.pasiva = pasiva;
        this.activosDesbloqueados = activosDesbloqueados;
        currentActive = -1;
    }
    
    public Tool() {
        
    }

    
    
}
