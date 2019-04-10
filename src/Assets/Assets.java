/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import videoGame.ImageLoader;

/**
 *
 * @author carlo
 */
public class Assets {
    public static BufferedImage coso, inventory;
    
    public static HashMap<String, BufferedImage[]> animations;
    
    public static void init(){
        coso = ImageLoader.loadImage("/Resources/Images/coso.png");
        inventory = ImageLoader.loadImage("/Resources/Images/Inventory.png");
        animations = new HashMap<>();
        
        animations.put("player_down", crop(32, 32, 1, "/Resources/Images/player_down.png"));
        animations.put("grass", crop(16, 16, 1, "/Resources/Images/grass.png"));
    }
    
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
