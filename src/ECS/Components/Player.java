/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import java.util.ArrayList;
import java.util.Vector;
import javafx.util.Pair;

/**
 *
 * @author pablo
 */
public class Player {
    public int energy;
    
    public int leftObject;
    
    public int rightObject;
    
    public int leftHand = 0;
    
    public int rightHand = 0;
    
    //current size of te actives (the maximum is 9)
    public int activesSize = 2;
    
    public int passivesSize = 2;
    /////
    ArrayList<Integer> arrayList1;
    ArrayList<Integer> arrayList2;
    
    public Pair p1, p2, p3;
    
    public String name = "INeedAName";

    public Player() {
        this.arrayList1 = new ArrayList<>();
        this.arrayList2 = new ArrayList<>();
        
        //spells that are held by the player with the right weapon
        p1 = new Pair(rightObject, arrayList1);
        
        //spells that are held by the player with the left weapon
        p2 = new Pair(leftObject, arrayList2);
        p3 = new Pair(p1, p2);
    }
}
