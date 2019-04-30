/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author carlo
 */
public class MSG {
    boolean finished = false;
    
    public synchronized boolean getFinished(){
        return finished;
    }
    
    public synchronized void setFinished(boolean f){
        finished = f;
    }
    
}
