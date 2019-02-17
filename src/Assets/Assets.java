/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assets;

import java.awt.image.BufferedImage;
import videoGame.ImageLoader;

/**
 *
 * @author carlo
 */
public class Assets {
    public static BufferedImage coso;
    
    public static void init(){
        coso = ImageLoader.loadImage("/Resources/Images/coso.png");
    }
}
