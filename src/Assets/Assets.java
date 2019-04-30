package Assets;

import Utility.Pair;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import IO.ImageLoader;
import IO.SoundClip;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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
    
    public static Font undefinedMedium;
    
    //A map containing all the sprite animations
    //the inner Pair has as first: a BufferedImage animation, and as second: the number of frames of such animation
    public static HashMap<String, Pair<BufferedImage[], Integer>> animations;
    public static SoundClip backgroundTheme;
    public static SoundClip houseTheme;
    public static SoundClip aldeaTheme;
    public static SoundClip battleTheme;
    public static SoundClip cityTheme;
    public static SoundClip bossTheme;
    public static SoundClip explorationTheme;
    public static SoundClip fatherTheme;
    public static SoundClip grassWalk;
    public static SoundClip pickUp;
    public static SoundClip menu;
    
            
    public static void init(){
        //load Fonts
        
        try {
            //URL url = Assets.class.getResource("/Resources/Fonts/undefined_medium.ttf");
            //File font = new File(Assets.class.getResource("/Resources/Fonts/undefined_medium.ttf").getPath());
            //create the font to use. Specify the size!
            undefinedMedium = Font.createFont(Font.TRUETYPE_FONT, new File(Assets.class.getResource("/Resources/Fonts/undefined_medium.ttf").getPath())).deriveFont(10f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(undefinedMedium);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        
        //Load animations
        animations = new HashMap<>();
        
        //ALL animmations goes here
        animations.put("player_down",   new Pair<>(crop(32, 32, 4, "/Resources/Images/player_down.png", false)          , 4));
        animations.put("player_up",     new Pair<>(crop(32, 32, 4, "/Resources/Images/player_up.png", false)            , 4));
        animations.put("player_left",   new Pair<>(crop(32, 32, 4, "/Resources/Images/player_left.png", false)          , 4));
        animations.put("player_right",  new Pair<>(crop(32, 32, 4, "/Resources/Images/player_right.png", false)         , 4));
        animations.put("enemy",         new Pair<>(crop(64, 80, 1, "/Resources/Images/enemy.png", false)                , 1));
        //tiles

        animations.put("grass",         new Pair<>(crop(16, 16, 1, "/Resources/Images/grass.png",false)           , 1));
        animations.put("log",         new Pair<>(crop(16, 16, 1, "/Resources/Images/log.png",false)           , 1));

        animations.put("grass",         new Pair<>(crop(16, 16, 1, "/Resources/Images/grass.png", false)                , 1));
        animations.put("grassSide",     new Pair<>(crop(16, 16, 1, "/Resources/Images/grassSide.png", false)            , 1));


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
        
        animations.put("menu_game",     new Pair<>(crop(278, 150, 1, "/Resources/Images/menu_game_clear.png", false)          , 1));
        animations.put("menu_map",     new Pair<>(crop(278, 150, 1, "/Resources/Images/Menu_map_clear.png", false)            , 1));
        animations.put("menu_options",     new Pair<>(crop(278, 150, 1, "/Resources/Images/menu_clear.png", false)            , 1));
        animations.put("menu_quests",     new Pair<>(crop(278, 150, 1, "/Resources/Images/menu_quests_clear.png", false)      , 1));
        //MISC
        animations.put("selected_transparency",     new Pair<>(crop(16, 16, 1, "/Resources/Images/selected_transparency.png", true), 1));
        animations.put("effect2",     new Pair<>(crop(1, 1, 1, "/Resources/Images/effect2.png", false), 1));
        animations.put("null",     new Pair<>(crop(1, 1, 1, "/Resources/Images/null.png", false), 1));
        //buttons
        animations.put("Button_32",     new Pair<>(crop(32, 10, 1, "/Resources/Images/Button_32.png", false), 1));
        animations.put("Button_48",     new Pair<>(crop(48, 10, 1, "/Resources/Images/Button_48.png", false), 1));
        animations.put("Button_long",     new Pair<>(crop(58, 10, 1, "/Resources/Images/Button_long.png", false), 1));
        animations.put("Tab_light",     new Pair<>(crop(48, 13, 1, "/Resources/Images/Tab_light.png", false), 1));
        animations.put("Tab_dark",     new Pair<>(crop(48, 13, 1, "/Resources/Images/Tab_dark.png", false), 1));
        
        //SOUND
        
        backgroundTheme = new SoundClip("/Resources/Sounds/Casa.wav"); //Background sound
        houseTheme = new SoundClip("/Resources/Sounds/Casa.wav");
        aldeaTheme = new SoundClip("/Resources/Sounds/Aldea_primitiva.wav");
        battleTheme = new SoundClip("/Resources/Sounds/batalla.wav");
        cityTheme = new SoundClip("/Resources/Sounds/ciudad.wav");
        bossTheme = new SoundClip("/Resources/Sounds/boss.wav");
        explorationTheme = new SoundClip("/Resources/Sounds/exploration.wav");
        fatherTheme = new SoundClip("/Resources/Sounds/father.wav");
        grassWalk = new SoundClip("/Resources/Sounds/grassWalk.wav");
        pickUp = new SoundClip("/Resources/Sounds/pickUpWoods.wav");
        menu = new SoundClip("/Resources/Sounds/Menu.wav");

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
    public static BufferedImage[] crop(int width, int height, int N, String path, boolean translucid){
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