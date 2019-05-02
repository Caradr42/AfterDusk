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
            //player
        animations.put("player_down",   new Pair<>(crop(32, 32, 4, "/Resources/Images/player_down.png", false)          , 4));
        animations.put("player_up",     new Pair<>(crop(32, 32, 4, "/Resources/Images/player_up.png", false)            , 4));
        animations.put("player_left",   new Pair<>(crop(32, 32, 4, "/Resources/Images/player_left.png", false)          , 4));
        animations.put("player_right",  new Pair<>(crop(32, 32, 4, "/Resources/Images/player_right.png", false)         , 4));
            //father
        animations.put("father_down",   new Pair<>(crop(36, 36, 4, "/Resources/Images/PAPADOWN.png", false)             , 4));
        animations.put("father_up",     new Pair<>(crop(36, 36, 4, "/Resources/Images/PAPAUP.png", false)               , 4));
        animations.put("father_left",   new Pair<>(crop(36, 36, 4, "/Resources/Images/PAPALEFT.png", false)             , 4));
        animations.put("father_right",  new Pair<>(crop(36, 36, 4, "/Resources/Images/PAPARIGHT.png", false)            , 4));
            //villian
        animations.put("villian_down",  new Pair<>(crop(36, 36, 4, "/Resources/Images/VILLIANFRONT.png", false)         , 4));
        animations.put("villian_up",    new Pair<>(crop(36, 36, 4, "/Resources/Images/VILLIANBACK.png", false)          , 4));
        animations.put("villian_left",  new Pair<>(crop(36, 36, 4, "/Resources/Images/VILLIANLEFT.png", false)          , 4));
        animations.put("villian_right", new Pair<>(crop(36, 36, 4, "/Resources/Images/VILLIANRIGHT.png", false)         , 4));
        
            //enemy
        animations.put("ball",          new Pair<>(crop(64, 64, 4, "/Resources/Images/BALL_STATIC.png", false)          , 4));
        animations.put("ball_down",     new Pair<>(crop(64, 64, 4, "/Resources/Images/BALL_DOWN.png", false)            , 4));
        animations.put("ball_up",       new Pair<>(crop(64, 64, 4, "/Resources/Images/BALL_UP.png", false)              , 4));
        animations.put("ball_left",     new Pair<>(crop(64, 64, 4, "/Resources/Images/BALL_LEFT.png", false)            , 4));
        animations.put("ball_right",     new Pair<>(crop(64, 64, 4, "/Resources/Images/BALL_RIGHT.png", false)          , 4));
        
        //TILES

        
        animations.put("log",           new Pair<>(crop(16, 16, 1, "/Resources/Images/log.png",false)                   , 1));

        animations.put("grass",         new Pair<>(crop(16, 16, 1, "/Resources/Images/grass.png", false)                , 1));
        animations.put("grassSide",     new Pair<>(crop(16, 16, 1, "/Resources/Images/grassSide.png", false)            , 1));
        
        animations.put("soilBG",        new Pair<>(crop(16, 16, 1, "/Resources/Images/soilBG.png", false)               , 1));

        
        animations.put("floorTile",     new Pair<>(crop(16, 16, 1, "/Resources/Images/floorTile.png", false)            , 1));
        animations.put("woodOctaFloor", new Pair<>(crop(16, 16, 1, "/Resources/Images/woodOctaFloor.png", false)        , 1));
        animations.put("window",        new Pair<>(crop(16, 16, 1, "/Resources/Images/window.png", false)               , 1));
        animations.put("blod_floor",    new Pair<>(crop(16, 16, 1, "/Resources/Images/blod_floor.png", false)           , 1));
        
        animations.put("beam",          new Pair<>(crop(16, 16, 1, "/Resources/Images/beam.png", false)                 , 1));
        animations.put("beamRight",     new Pair<>(crop(16, 16, 1, "/Resources/Images/beamRight.png", false)            , 1));
        animations.put("beamLeft",      new Pair<>(crop(16, 16, 1, "/Resources/Images/beamLeft.png", false)             , 1));
        animations.put("ledColumn",     new Pair<>(crop(16, 16, 1, "/Resources/Images/ledColumn.png", false)            , 1));
        animations.put("redWoodWall",   new Pair<>(crop(16, 16, 1, "/Resources/Images/redWoodWall.png", false)          , 1));
        animations.put("stone_brick",   new Pair<>(crop(16, 16, 1, "/Resources/Images/stone_brick.png", false)          , 1));
        animations.put("stoneWall",     new Pair<>(crop(16, 16, 1, "/Resources/Images/stoneWall.png", false)            , 1));
        animations.put("stoneColumn",   new Pair<>(crop(16, 16, 1, "/Resources/Images/stoneColumn.png", false)          , 1));
        animations.put("stoneColumnLeft",new Pair<>(crop(16, 16, 1, "/Resources/Images/stoneColumnLeft.png", false)     , 1));
        animations.put("stoneColumnRight",new Pair<>(crop(16, 16, 1, "/Resources/Images/stoneColumnRight.png", false)   , 1));
        
        //SCENARY
        
        animations.put("treeTall",      new Pair<>(crop(128, 272, 1, "/Resources/Images/treeTall.png", false)           , 1));
        
        //items
        animations.put("weird",         new Pair<>(crop(16, 16, 1, "/Resources/Images/card.png", false)                 , 1));
        animations.put("shield",        new Pair<>(crop(16, 16, 1, "/Resources/Images/shield.png", false)               , 1));
        animations.put("E_sword",       new Pair<>(crop(16, 16, 1, "/Resources/Images/E_sword.png", false)              , 1));
        animations.put("crossBow",       new Pair<>(crop(16, 16, 1, "/Resources/Images/crossBow.png", false)            , 1));
        
        
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
        
        animations.put("HP_bar",        new Pair<>(crop(64, 16, 1, "/Resources/Images/HP_bar.png", false)                , 1));
        animations.put("HP",            new Pair<>(crop(1, 1, 1, "/Resources/Images/HP.png", false)                , 1));
        animations.put("energy_bar",    new Pair<>(crop(64, 16, 1, "/Resources/Images/Energy_bar.png", false)            , 1));
        animations.put("energy",            new Pair<>(crop(1, 1, 1, "/Resources/Images/energy.png", false)                , 1));
        
        
        animations.put("menu_game",     new Pair<>(crop(278, 150, 1, "/Resources/Images/menu_game_clear.png", false)          , 1));
        animations.put("menu_map",     new Pair<>(crop(278, 150, 1, "/Resources/Images/Menu_map_clear.png", false)            , 1));
        animations.put("menu_options",     new Pair<>(crop(278, 150, 1, "/Resources/Images/menu_clear.png", false)            , 1));
        animations.put("menu_quests",     new Pair<>(crop(278, 150, 1, "/Resources/Images/menu_quests_clear.png", false)      , 1));
        //MISC
        animations.put("selected_transparency",     new Pair<>(crop(16, 16, 1, "/Resources/Images/selected_transparency.png", true), 1));
        animations.put("effect2",       new Pair<>(crop(1, 1, 32, "/Resources/Images/effect2.png", true), 32));
        animations.put("null",          new Pair<>(crop(1, 1, 1, "/Resources/Images/null.png", false), 1));
        animations.put("item_selector",     new Pair<>(crop(18, 18, 27, "/Resources/Images/ItemSelector.png", false), 27));
        
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