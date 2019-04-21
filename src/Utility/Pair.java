package Utility;

/**
 * Utility class for handling a pair of data
 * @author Carlos Adrián Guerra Vázquez
 * @date 19/04/2019
 * @version 1.0
 * @param <A> the type of the first element
 * @param <B> the type of the second element
 */
public class Pair<A, B> {
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
