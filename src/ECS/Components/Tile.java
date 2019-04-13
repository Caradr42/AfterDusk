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
    /*public ArrayList<String> topTextures;
    public ArrayList<String> sideTextures;
    public ArrayList<BufferedImage> topTextureRef;
    public ArrayList<BufferedImage> sideTextureRef;*/
    
    String name;

    public Tile(String name) {
        this.name = name;
    }

    public Tile() {
    }
    
    
}
