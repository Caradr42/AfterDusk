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
 *
 * @author tanya
 */
public abstract class Active extends Component {
    boolean isActive = false;
    boolean isUnlocked = false;
    
    ArrayList <Pair<String, Integer>> effects;
    ArrayList <Pair<String, Integer>> costs;
    
}
