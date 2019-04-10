/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import Maths.Vector3;

/**
 *
 * @author pablo
 */
public class WorldEntity extends Component{
     public Vector3 position;

    public WorldEntity() {
    }

    public WorldEntity(Vector3 position) {
        this.position = position;
    }
     
     
}
