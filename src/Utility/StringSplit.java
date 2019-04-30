package Utility;

import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class StringSplit {
    
    public static ArrayList<String> split(String string) {
        String[] words = string.split("\\s");//splits the string based on whitespace  
        //using java foreach loop to print elements of string array  
        ArrayList<String> result = new ArrayList<>();
        for (String w : words) {
            result.add(w);
        }
        return result;
    }
}
