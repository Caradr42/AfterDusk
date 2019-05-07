/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import Maths.Vector2;
import Maths.Vector3;



public class Enemy extends Component{
    
    //vector to store the last distance vector that moved the enemy
    public Vector2 prev = new Vector2();
}
