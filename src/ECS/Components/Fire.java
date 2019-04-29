/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import Utility.Pair;
import java.util.ArrayList;

/**
 *Aquí se guardan las activas que tiene un determinado tool
 * @author tanya
 */
public class Fire extends Active{
    double level;

    public Fire(double level) {
        this.level = level;
    }
    
    public Fire(ArrayList <Pair<String,Integer>> effects, ArrayList <Pair<String,Integer>> costs ) {
        this.effects = effects;
        this.costs = costs;
    }        
}
