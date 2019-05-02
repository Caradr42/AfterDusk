/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import Utility.Pair;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author tanya
 */
public abstract class Pasive extends Component{
        
    public boolean isActive = false;
    public int idSprite;

    public ArrayList <Pair<String, Integer>> costs;
    
    public static HashSet <Class> pasivesSet = new HashSet<Class>();

    public Pasive() {

    }
    
    
}
