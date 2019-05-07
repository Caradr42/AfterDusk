/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

/**
 *
 * @author tanya
 */
public class ExtraHealth extends Pasive{
    public int cost;

    public ExtraHealth() {
        pasivesSet.add(this.getClass());
        cost = 20;
    }
    
}
