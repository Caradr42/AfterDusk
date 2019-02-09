package maths;

/**
 *
 * @author Carlos Adrian Guerra Vazquez A00823198
 * @date 28/01/2019
 * @version 1.0
 */
public class IntVector2 {
    private int x;
    private int y;

    public IntVector2() {
        x = 0;
        y = 0;
    }
    
    public IntVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public double dist(IntVector2 q){
        return  Math.sqrt(Math.pow(this.getX() - q.getX(), 2) + Math.pow(this.getY() - q.getY(), 2));
    }
    
    public IntVector2 dif(IntVector2 q){
        return  new IntVector2(q.getX() - getX(), q.getY() - getY());
    }

    public IntVector2 add(IntVector2 q){
        return  new IntVector2(q.getX() + getX(), q.getY() + getY());
    }
    
    public static double distance(IntVector2 p, IntVector2 q){
        return  Math.sqrt(Math.pow(p.getX() - q.getX(), 2) + Math.pow(p.getY() - q.getY(), 2));
    }        
}
