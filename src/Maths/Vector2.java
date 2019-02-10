package maths;

/**
 *
 * @author carlo
 */
public class Vector2 {
    private double x;
    private double y;

    public Vector2() {
        x = 0.0f;
        y = 0.0f;
    }
    
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public double dist(Vector2 q){
        return  Math.sqrt(Math.pow(this.getX() - q.getX(), 2) + Math.pow(this.getY() - q.getY(), 2));
    }
    
    public Vector2 dif(Vector2 q){
        return  new Vector2(q.getX() - getX(), q.getY() - getY());
    }

    public Vector2 add(Vector2 q){
        return  new Vector2(q.getX() + getX(), q.getY() + getY());
    }
    
    public static double distance(Vector2 p, Vector2 q){
        return  Math.sqrt(Math.pow(p.getX() - q.getX(), 2) + Math.pow(p.getY() - q.getY(), 2));
    }       
    
    public IntVector2 toIntVector2(){
        return new IntVector2((int)x,(int)y);
    }
}
