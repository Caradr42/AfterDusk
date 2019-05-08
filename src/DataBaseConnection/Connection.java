package DataBaseConnection;

import javax.swing.*;
import java.sql.*;
import java.sql.DriverManager;
import static javax.management.remote.JMXConnectorFactory.connect;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.JOptionPane;


/**
 * Connect to the database
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 25/01/2018 
 * @versión 1.0 
 */
public class Connection {
    static Connection connect = null;
    
    /**
     * Constructor
     */
    public Connection(){
    }
    
    public static Connection conexion(){
        
    try{
        Class.forName("com.mysql.jdbc.Driver");
        //Load Driver MySQL
        connect=  (Connection) DriverManager.getConnection("jdbc:mysql://remotemysql.com/UenUhgqeHb","UenUhgqeHb","uGStDaKrpw"); 
    }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error "+e);
    }
        return connect;
    }
}
