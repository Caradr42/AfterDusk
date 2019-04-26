package Assets;

import Utility.Pair;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import IO.ImageLoader;
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
    
    //A map containing all the sprite animations
    //the inner Pair has as first: a BufferedImage animation, and as second: the number of frames of such animation
    public static HashMap<String, Pair<BufferedImage[], Integer>> animations;
    
    public static void init(){
        
        animations = new HashMap<>();
        
        //ALL animmations goes here
        animations.put("player_down",   new Pair<>(crop(32, 32, 4, "/Resources/Images/player_down.png", false)          , 4));
        animations.put("player_up",     new Pair<>(crop(32, 32, 4, "/Resources/Images/player_up.png", false)            , 4));
        animations.put("player_left",   new Pair<>(crop(32, 32, 4, "/Resources/Images/player_left.png", false)          , 4));
        animations.put("player_right",  new Pair<>(crop(32, 32, 4, "/Resources/Images/player_right.png", false)         , 4));
        animations.put("enemy",         new Pair<>(crop(64, 80, 1, "/Resources/Images/enemy.png", false)                , 1));
        //tiles
        animations.put("grass",         new Pair<>(crop(16, 16, 1, "/Resources/Images/grass.png", false)                , 1));
        //items
        animations.put("weird",         new Pair<>(crop(16, 16, 1, "/Resources/Images/weird.png", false)                , 1));
        animations.put("shield",        new Pair<>(crop(16, 16, 1, "/Resources/Images/shield.png", false)               , 1));
        //GUI
        animations.put("inventory",     new Pair<>(crop(195, 135, 1, "/Resources/Images/Inventory.png", false)          , 1));
        animations.put("3x3Slots_dark", new Pair<>(crop(52, 52, 1, "/Resources/Images/3x3Slots_dark.png", false)        , 1));
        animations.put("1x3Slots_light",new Pair<>(crop(52, 18, 1, "/Resources/Images/1x3Slots_light.png", false)       , 1));
        animations.put("1x3Slots_dark", new Pair<>(crop(52, 18, 1, "/Resources/Images/1x3Slots_dark.png", false)        , 1));
        animations.put("1x6Slots_light",new Pair<>(crop(103, 18, 1, "/Resources/Images/1x6Slots_light.png", false)      , 1));
        animations.put("1x9Slots_dark", new Pair<>(crop(154, 18, 1, "/Resources/Images/1x9Slots_dark.png", false)       , 1));
        animations.put("1x2Slots_dark", new Pair<>(crop(35, 18, 1, "/Resources/Images/1x2Slots_dark.png", false)        , 1));
        
        animations.put("actives_bar",   new Pair<>(crop(160, 32, 1, "/Resources/Images/actives_bar.png", false)         , 1));
        animations.put("RL_bar",        new Pair<>(crop(48, 32, 1, "/Resources/Images/RL_bar.png", false)               , 1));
        animations.put("menu_game",     new Pair<>(crop(278, 150, 1, "/Resources/Images/menu_game.png", false)          , 1));
        animations.put("menu_map",     new Pair<>(crop(278, 150, 1, "/Resources/Images/menu_map.png", false)            , 1));
        
        animations.put("Button_48_selected",     new Pair<>(crop(48, 10, 1, "/Resources/Images/Button_48_selected.png", true), 1));
        //Button_48_selected
        //System.out.println("found: " + animations.get("actives_bar").first);
    }
    
    /**
     * Crop
     * Method that crops sprites from spritesheets
     * 
     * @param width width of the sprite
     * @param height height of the sprite
     * @param N number of sprites
     * @param path the path in the game file path
     * @return animation returns a BufferesImage[], this is an animation
     */
    private static BufferedImage[] crop(int width, int height, int N, String path, boolean translucid){
        BufferedImage temp = ImageLoader.loadImage(path, translucid);  
        BufferedImage[] animation = new BufferedImage[N];
        
        for(int sprite = 0; sprite < N; ++sprite){
            int x = width * sprite;
            int y = 0;
            animation[sprite] = temp.getSubimage(x, y, width, height);
        }
        return animation;
    }
}