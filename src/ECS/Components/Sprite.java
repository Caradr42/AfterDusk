package ECS.Components;

import ECS.Component;
import java.awt.image.BufferedImage;
import videoGame.ImageLoader;

/**
 * Example ECS component
 * 
 * a ECS component contains 
 * //data
 * 
 * //code to initialize the data
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public class Sprite extends Component{
    //data
    public String name;
    public BufferedImage bi;
    public int width;
    public int height;
    
    //code to initialize the data
    public Sprite(String name, int width, int height, String path) {
        this.name = name;
        bi = ImageLoader.loadImage(path);
        this.width = width;
        this.height = height;
    }

    public Sprite() {
    }
    
}
