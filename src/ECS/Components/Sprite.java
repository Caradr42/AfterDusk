package ECS.Components;

import ECS.Component;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    public boolean active;
    
    /*public int mode; // 0 is no animation, 1 is animation one, n is animation n
    public BufferedImage currentFrame;
    public BufferedImage[] animation;
    public ArrayList<BufferedImage[]> animations;*/
    public BufferedImage bi;
    public int width;
    public int height;
    
    
    //code to initialize the data
    public Sprite(String name, boolean active,  int width, int height, BufferedImage bi) {
        this.name = name;
        this.active = active;
        this.bi = bi;
        this.width = width;
        this.height = height;
        active = true;
    }

    public Sprite() {
    }
    
}
