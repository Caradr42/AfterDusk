package ECS.Components;

import ECS.Component;
import java.util.ArrayList;
import maths.Vector2;

/**
 *
 * @author carlo
 */
public class Tile extends Component{
    //Array of textures to be rendered one avobe the other
    public ArrayList<String> topTextures;
    public ArrayList<String> sideTextures;
    
    public Vector2 position;
    String name;

    public Tile(String name, ArrayList<String> topTextures, ArrayList<String> sideTextures, Vector2 position) {
        this.topTextures = topTextures;
        this.sideTextures = sideTextures;
        this.position = position;
        this.name = name;
    }
}
