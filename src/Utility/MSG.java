package Utility;

/**
 * Utility class for handling a message
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/02/2019
 * @version 1.1
 */
public class MSG {
    boolean finished = false;
    
    /**
     * returns if is finished
     * @return 
     */
    public synchronized boolean getFinished(){
        return finished;
    }
    /**
     * sets the boolean finished value
     * @param f 
     */
    public synchronized void setFinished(boolean f){
        finished = f;
    }
    
}
