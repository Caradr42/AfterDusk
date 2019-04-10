/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import java.util.ArrayList;
import Maths.Vector3;

/**
 *
 * @author tanya
 */
public class Playable extends Component {
    
    int hp;
    public Vector3 position;
    int inventory;
    public Vector3 hitbox;
    public ArrayList<String> animations;
    public Vector3 velocity;

    public Playable(int hp, Vector3 position, int inventory, Vector3 hitbox, ArrayList<String> animations, Vector3 velocity) {
        this.hp = hp;
        this.position = position;
        this.inventory = inventory;
        this.hitbox = hitbox;
        this.animations = animations;
        this.velocity = velocity;
    }

    public Playable() {
    }
}
