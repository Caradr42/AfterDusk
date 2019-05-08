package DataBaseConnection;

import ECS.Components.Transform;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 * Manages de database system
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 25/01/2018 
 * @versión 1.0 
 */
public class DataBaseSystem {

    /**
     * Constructor
     */
    public DataBaseSystem() {
    }
    
    public void loadDataBase(){
        try {
                    // create a mysql database connection
                    String myDriver = "com.mysql.jdbc.Driver";
                    String myUrl = "jdbc:mysql://remotemysql.com/UenUhgqeHb";
                    Class.forName(myDriver);
                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/UenUhgqeHb","UenUhgqeHb","uGStDaKrpw");
                    
                    // create a sql date object so we can use it in our INSERT statement
                    Calendar calendar = Calendar.getInstance();
                    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

                    // the mysql insert statement
                    String query = " insert into Entity (Entity_ID, Entity_Name)"
                            + " values (?, ?)";

                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1,2020);
                    preparedStmt.setString(2, "Pepe");

                    // execute the preparedstatement
                    preparedStmt.execute();

                    conn.close();
                }catch(Exception e2){
                    JOptionPane.showMessageDialog(null, "Error "+e2);
                }
    }
    
    public void insertSerialization(Object myObject) throws IOException{
        
        try{
            // create a mysql database connection
                    String myDriver = "com.mysql.jdbc.Driver";
                    String myUrl = "jdbc:mysql://remotemysql.com/UenUhgqeHb";
                    Class.forName(myDriver);
                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/UenUhgqeHb","UenUhgqeHb","uGStDaKrpw");
                    
                    // create a sql date object so we can use it in our INSERT statement
                    Calendar calendar = Calendar.getInstance();
                    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            
            ByteArrayOutputStream byteArray= new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteArray);
            oos.writeObject(myObject);
            PreparedStatement ps= conn.prepareStatement("REPLACE into objetos values (1000, ?, ?)");
            
            ps.setBytes(1, byteArray.toByteArray());
            ps.setString(2, "HelloWorld!");
            ps.execute();
        }catch (Exception e){
            System.err.println("Error serialization insert "+e);
        }
    } 
    
    public Object selectSerialization(){
        Object myObject=new Object();
        try{
            // create a mysql database connection
                    String myDriver = "com.mysql.jdbc.Driver";
                    String myUrl = "jdbc:mysql://remotemysql.com/UenUhgqeHb";
                    Class.forName(myDriver);
                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/UenUhgqeHb","UenUhgqeHb","uGStDaKrpw");
                    
                    // create a sql date object so we can use it in our INSERT statement
                    Calendar calendar = Calendar.getInstance();
                    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            
            PreparedStatement ps = conn.prepareStatement("select * from objetos");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                //Se obtiene el campo blob
                Blob blob = rs.getBlob("objeto");
                //Reconstrucción del objeto
                ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());
                myObject = (Object) ois.readObject();
                System.out.println(((Transform)myObject));
                System.out.println(myObject);
            }
        }catch(Exception e){
            System.err.println("error select serialization: "+e);
        }
        
        return myObject;
    }
}
    
