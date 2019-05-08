package Utility;

import java.util.ArrayList;

/**
 * Utility class for handling a stringSplit
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/02/2019
 * @version 1.1
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
