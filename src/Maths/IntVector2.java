package maths;

/**
 * A maths utility class.
 * provides a integer vector with vector operations like: addition, subtraction, 
 * subtraction, distance, etc.
 * 
 * A vector is composed of a tuple of two values. 
 * 
 * @author Carlos Adrian Guerra Vazquez A00823198
 * @date 12/02/2019
 * @version 1.0
 */
public class IntVector2 {
    
    //the vector touple of integer values x and y.
    private int x;
    private int y;
    
    /**
     * vector constructor.
     * by default initializes the vector at (0,0)
     */
    public IntVector2() {
        x = 0;
        y = 0;
    }
    
    /**
     * vector constructor.
     * initializes the vector tuple at x and y.
     * @param x <b>x</b> value of the vector
     * @param y <b>y</b> value of the vector
     */
    public IntVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * returns vector's x value
     * @return x double
     */
    public int getX() {
        return x;
    }
    
    /**
     * returns vector's y value
     * @return y double
     */
    public int getY() {
        return y;
    }
    
    /**
     * sets the vector's x value
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * sets the vector's y value
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * sets both x and y values from the vector
     * @param x
     * @param y 
     */
    public void set(int x, int y){
        setX(x);
        setY(y);
    }
    
    /**
     * sets the vectors x and y values as the same as the parameter vector ones.
     * gets the x and by value.
     * @param vect the vector from which x and y will be equaled
     */
    public void set(IntVector2 vect){
        setX(vect.getX());
        setY(vect.getY());
    }
    
    /**
     * Measures the distance to another vector.
     * @param q the other vector to which the distance will be measured
     * @return the distance as absolute value
     */
    public double dist(IntVector2 q){
        return  Math.sqrt(Math.pow(this.getX() - q.getX(), 2) + Math.pow(this.getY() - q.getY(), 2));
    }
    
    /**
     * Returns the subtraction of other vector with this.
     * @param q the vector to be subtracted.
     * @return a new vector product of the subtraction.
     */
    public IntVector2 dif(IntVector2 q){
        return  new IntVector2(q.getX() - getX(), q.getY() - getY());
    }
    
    /**
     * Returns the addition of other vector with this.
     * @param q the vector to be added.
     * @return a new vector product of the addition.
     */
    public IntVector2 add(IntVector2 q){
        return  new IntVector2(q.getX() + getX(), q.getY() + getY());
    }
    
    /**
     * Returns the subtraction of two vectors.
     * @param p the vector to subtract from
     * @param q the vector to be subtracted.
     * @return a new vector product of the subtraction.
     */
    public static double distance(IntVector2 p, IntVector2 q){
        return  Math.sqrt(Math.pow(p.getX() - q.getX(), 2) + Math.pow(p.getY() - q.getY(), 2));
    }     
    
    /**
     * Converts this to a DouVector
     * @return a new DouVector equal to this by value
     */
    public Vector2 toDouVector2(){
        return new Vector2((double)x,(double)y);
    }
    
}
