/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author pablo
 */
public class Point {
    public double x;
    
    public double y;

    public Point(double x, double y) {
        this.x = x;
        
        this.y = y;
    }
    
    /**
     * isBetween
     * 
     * Function that determines if the point p1 is between the point p2 and p3
     * in the axis 'axis'. The axis can be x or y.
     * 
     * @param p1 Base point
     * @param p2 Lower/Left limit point
     * @param p3 Upper/Right limit point
     * @param axis Axis in which to make the comparison
     * @return whether p1 is between p2 in the axis 'axis'
     */
    public static boolean isBetween(Point p1, Point p2, Point p3, char axis) {
        boolean result = false;
        
        if(axis == 'x') {
            if(p1.x >= p2.x && p1.x <= p3.x) {
                result = true;
            }
        }
        
        else if(axis == 'y') {
            if(p1.y >= p3.y && p1.y <= p2.y) {
                result = true;
            }
        }
        
        return result;
    }
    
}
