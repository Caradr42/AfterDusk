/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;
import ECS.Component;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author pepe_
 */
public class Tool extends Component{
    //we have max 9 actives
    public ArrayList<Integer> activos;
    
    int pasiva;
    
    public ArrayList<Integer> activosDesbloqueados ;

    public Tool(ArrayList<Integer> activos, int pasiva, ArrayList<Integer> activosDesbloqueados) {
        this.activos = activos;
        this.pasiva = pasiva;
        this.activosDesbloqueados = activosDesbloqueados;
    }

    
    
}
