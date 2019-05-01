/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import Utility.Pair;
import java.util.ArrayList;

/**
 *
 * @author tanya
 */
public class Electricity extends Active{
    public int cost;

    public Electricity() {
        activesSet.add(this.getClass());
        //System.out.println("set: " + activesSet.size());
        cost = 10;
    }

}
