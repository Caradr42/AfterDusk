/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConnection;

import javax.swing.*;
import java.sql.*;
import java.sql.DriverManager;
import static javax.management.remote.JMXConnectorFactory.connect;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.JOptionPane;


/**
 *
 * @author pepe_
 */
public class Connection {
    static Connection connect = null;
    
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
