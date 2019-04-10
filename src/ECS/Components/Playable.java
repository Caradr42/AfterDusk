/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import java.util.ArrayList;
import maths.Vector2;

/**
 *
 * @author tanya
 */
public class Playable extends Component {
    
    int hp;
    public Vector2 position;
    int inventory;
    public Vector2 hitbox;
    public ArrayList<String> animations;
    public Vector2 velocity;

    public Playable(int hp, Vector2 position, int inventory, Vector2 hitbox, ArrayList<String> animations, Vector2 velocity) {
        this.hp = hp;
        this.position = position;
        this.inventory = inventory;
        this.hitbox = hitbox;
        this.animations = animations;
        this.velocity = velocity;
    }
}
