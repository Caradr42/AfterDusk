package videoGame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * class in charge of fetching images from a given path
 * 
 * @author Carlos Adrian Guerra Vazquez A00823198
 * @date 28/01/2019
 * @version 1.0
 */
public class ImageLoader {
    /**
     * returns the image it obtained and converted from a given path to a file.
     * paths are relative to the project's path.
     * @param path a string of a path relative to the project's path
     * @return a <b>BufferedImage</b> of the type fetched from the path.
     */
    public static BufferedImage loadImage(String path){
        BufferedImage bi = null;
        try{
            bi = ImageIO.read(ImageLoader.class.getResource(path));
        }catch(IOException ioe){ //catch if no image was found at dir path
            System.out.println("Error Loading Image " + path + ioe.toString() );
            System.exit(1);
        }
        return bi;
    }
}
