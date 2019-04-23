package Assets;

import Utility.Pair;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import videoGame.ImageLoader;
 /**
 * Class containing statically all the game assets
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class Assets {
    public static BufferedImage coso, inventory;
    
    public static HashMap<String, Pair<BufferedImage[], Integer>> animations;
    
    public static void init(){
        inventory = ImageLoader.loadImage("/Resources/Images/Inventory.png");
        animations = new HashMap<>();
        
        //ALL animmations goes here
        animations.put("player_down", new Pair<>(crop(32, 32, 4, "/Resources/Images/player_down.png"), 4));
        animations.put("player_up", new Pair<>(crop(32, 32, 4, "/Resources/Images/player_up.png"), 4));
        animations.put("player_left", new Pair<>(crop(32, 32, 4, "/Resources/Images/player_left.png"), 4));
        animations.put("player_right", new Pair<>(crop(32, 32, 4, "/Resources/Images/player_right.png"), 4));
        animations.put("grass", new Pair<>(crop(16, 16, 1, "/Resources/Images/grass.png"), 1));
        animations.put("inventory", new Pair<>(crop(240, 135, 1, "/Resources/Images/Inventory.png"), 1));
        animations.put("weird", new Pair<>(crop(16, 16, 1, "/Resources/Images/weird.png"), 1));
        animations.put("shield", new Pair<>(crop(16, 16, 1, "/Resources/Images/shield.png"), 1));
        
    }
    
    /**
     * Crop
     * 
     * Cuts the images 
     * @param width
     * @param height
     * @param N
     * @param path
     * @return animation
     */
    private static BufferedImage[] crop(int width, int height, int N, String path){
        BufferedImage temp = ImageLoader.loadImage(path);  
        BufferedImage[] animation = new BufferedImage[N];
        
        for(int sprite = 0; sprite < N; ++sprite){
            int x = width * sprite;
            int y = 0;
            animation[sprite] = temp.getSubimage(x, y, width, height);
        }
        return animation;
    }
}