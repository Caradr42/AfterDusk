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
public abstract class Active extends Component {
    boolean isActive = false;
    //boolean isUnlocked = false;
    int idSprite;
    //int idTool;
    
    /*Crear un SET static ArrayList de clases, cada vez que una clase extiende Active; en su constructor 
    * vas a agregarle la vlase al arraylist
    */
    
    ArrayList <Pair<String, Integer>> effects;
    ArrayList <Pair<String, Integer>> costs;
    
    public static HashSet <Class> activesSet = new HashSet<Class>();

    public Active() {
    }
    
    
}
