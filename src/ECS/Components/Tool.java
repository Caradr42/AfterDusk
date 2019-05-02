
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

    //public ArrayList<Integer> actives;
    Entity pasive;
    public ArrayList<Integer> unlockedActives;
    //Current active/attack being used. If nothing is used, it has a -1
    public int currentActive;
    public ArrayList <Class> arrClasses; 
    public ArrayList <Active> arrActives; 
    public ArrayList <Pasive> arrPasives; 
    

    public Tool( Entity pasive, ArrayList<Integer> actives, ArrayList<Integer> unlockedActives) {
        //this.actives = actives;
        this.pasive = pasive;
        this.unlockedActives = unlockedActives;
        this.arrActives = new ArrayList<Active>();
        this.arrPasives = new ArrayList<Pasive>();
        this.arrClasses = new ArrayList<Class>();
        
        currentActive = -1;
    }
    
    public Tool() {
        this.unlockedActives = unlockedActives;
        this.arrActives = new ArrayList<Active>();
        this.arrPasives = new ArrayList<Pasive>();
        this.arrClasses = new ArrayList<Class>();
    }
    
    public Tool(int currentActive) {
        this.unlockedActives = unlockedActives;
        this.arrActives = new ArrayList<Active>();
        this.arrPasives = new ArrayList<Pasive>();
        this.arrClasses = new ArrayList<Class>();
        this.currentActive = currentActive;
    }

}


