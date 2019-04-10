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
    public boolean active;
    
    //code to initialize the data
    public Sprite(String name, int width, int height, BufferedImage bi) {
        this.name = name;
        this.bi = bi;
        this.width = width;
        this.height = height;
        active = true;
    }

    public Sprite() {
    }
    
}
