package ECS.Components;

import ECS.Component;
import java.util.ArrayList;
import Maths.Vector3;
import java.awt.image.BufferedImage;

/**
 *
 * @author carlo
 */
public class Tile extends Component{
    //Array of textures to be rendered one avobe the other
    public ArrayList<String> topTextures;
    public ArrayList<String> sideTextures;
    public ArrayList<BufferedImage> topTextureRef;
    public ArrayList<BufferedImage> sideTextureRef;
    
    public Vector3 position;
    String name;

    public Tile(String name, ArrayList<String> topTextures, ArrayList<String> sideTextures, Vector3 position) {
        this.topTextures = topTextures;
        this.sideTextures = sideTextures;
        this.topTextureRef = new ArrayList<>(1);
        this.sideTextureRef = new ArrayList<>(1);
        this.position = position;
        this.name = name;
    }

    public Tile() {
    }
    
    
}
