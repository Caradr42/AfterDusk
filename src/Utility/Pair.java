package Utility;

import java.io.Serializable;

/**
 * Utility class for handling a pair of data
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 19/04/2019
 * @version 1.0
 * @param <A> the type of the first element
 * @param <B> the type of the second element
 */
public class Pair<A, B> implements Serializable{
    public A first;
    public B second;
    
    /**
     * Pair Constructor.
     *  
     * @param first the first element of the pair
     * @param second the second element of the pair
     */
    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }
}
