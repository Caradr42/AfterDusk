package Maths;

/**
 *
 * @author carlo
 */
public class Vector3 {
    //the vector touple of double values x and y.
    public double x;
    public double y;
    public double z;
    
    /**
     * vector constructor.
     * by default initializes the vector at (0,0)
     */
    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }
    
    /**
     * vector constructor.
     * initializes the vector tuple at x and y.
     * @param x <b>x</b> double value of the vector
     * @param y <b>y</b> double value of the vector
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }        
        
    /**
     * sets both x and y values from the vector
     * @param x
     * @param y 
     * @param z
     */
    public void set(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * sets both x and y values from the vector
     * @param x
     * @param y 
     */
    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * sets the vectors x and y values as the same as the parameter vector ones.
     * gets the x and by value.
     * @param v the vector from which x and y will be equaled
     */
    public void set(Vector2 v){
        x = v.x;
        y = v.x;
    }
    
    /**
     * Measures the distance to another vector.
     * @param q the other vector to which the distance will be measured
     * @return the distance as absolute value
     */
    public double dist(Vector2 q){
        return  Math.sqrt(Math.pow(this.x - q.x, 2) + Math.pow(this.y - q.y, 2));
    }
        
    /**
     * Returns the subtraction of other vector with this.
     * @param q the vector to be subtracted.
     * @return a new vector product of the subtraction.
     */
    public Vector2 sub(Vector2 q){
        return  new Vector2(q.x - x, q.y - y);
    }
    
    /**
     * Returns the addition of other vector with this.
     * @param q the vector to be added.
     * @return a new vector product of the addition.
     */
    public Vector2 add(Vector2 q){
        return  new Vector2(q.x + x, q.y + y);
    }
    
    public Vector2 scalar(double c){
        return  new Vector2(x * c, y * c);
    }
    
    public Vector2 div(double c){
        return  new Vector2(x / c, y / c);
    }
    
    public double dot(Vector2 c){
        return (x * c.x) + (y * c.y);
    }
    
    public double dotDiv(Vector2 c){
        return (x / c.x) + (y / c.y);
    }
    
    public double mag(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    public Vector2 norm(){
        double m = this.mag();
        if(m > 0){
            return this.div(m);
        }
        return new Vector2(0,0);
    }    
    
    /**
     * Converts this to an IntVector
     * @return a new IntVector equal to this by value
     */
    public IntVector2 toIntVector2(){
        return new IntVector2((int)x,(int)y);
    }                 
}
