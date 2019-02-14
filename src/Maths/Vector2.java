package maths;

/**
 * A maths utility class.
 * provides a double vector with vector operations like: addition, subtraction, 
 * subtraction, distance, etc.
 * 
 * A vector is composed of a tuple of two values. 
 * 
 * @author Carlos Adrian Guerra Vazquez A00823198
 * @date 12/02/2019
 * @version 1.1
 */
public class Vector2 {
    
    //the vector touple of double values x and y.
    private double x;
    private double y;
    
    /**
     * vector constructor.
     * by default initializes the vector at (0,0)
     */
    public Vector2() {
        x = 0;
        y = 0;
    }
    
    /**
     * vector constructor.
     * initializes the vector tuple at x and y.
     * @param x <b>x</b> double value of the vector
     * @param y <b>y</b> double value of the vector
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * returns vector's x value
     * @return x 
     */
    public double getX() {
        return x;
    }
    
    /**
     * returns vector's y value
     * @return y 
     */
    public double getY() {
        return y;
    }
    
    /**
     * sets the vector's x value
     * @param x 
     */
    public void setX(double x) {
        this.x = x;
    }
    
    /**
     * sets the vector's y value
     * @param y 
     */
    public void setY(double y) {
        this.y = y;
    }
    
    /**
     * sets both x and y values from the vector
     * @param x
     * @param y 
     */
    public void set(double x, double y){
        setX(x);
        setY(y);
    }
    
    /**
     * sets the vectors x and y values as the same as the parameter vector ones.
     * gets the x and by value.
     * @param vect the vector from which x and y will be equaled
     */
    public void set(Vector2 vect){
        setX(vect.getX());
        setY(vect.getY());
    }
    
    /**
     * Measures the distance to another vector.
     * @param q the other vector to which the distance will be measured
     * @return the distance as absolute value
     */
    public double dist(Vector2 q){
        return  Math.sqrt(Math.pow(this.getX() - q.getX(), 2) + Math.pow(this.getY() - q.getY(), 2));
    }
    
    /**
     * Measures the distance to an IntVector.
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
    public Vector2 dif(Vector2 q){
        return  new Vector2(q.getX() - getX(), q.getY() - getY());
    }
    
    /**
     * Returns the addition of other vector with this.
     * @param q the vector to be added.
     * @return a new vector product of the addition.
     */
    public Vector2 add(Vector2 q){
        return  new Vector2(q.getX() + getX(), q.getY() + getY());
    }
    
    /**
     * Returns the subtraction of two vectors.
     * @param p the vector to subtract from
     * @param q the vector to be subtracted.
     * @return a new vector product of the subtraction.
     */
    public static double distance(Vector2 p, Vector2 q){
        return  Math.sqrt(Math.pow(p.getX() - q.getX(), 2) + Math.pow(p.getY() - q.getY(), 2));
    }       
    
    /**
     * Converts this to an IntVector
     * @return a new IntVector equal to this by value
     */
    public IntVector2 toIntVector2(){
        return new IntVector2((int)x,(int)y);
    }
}
