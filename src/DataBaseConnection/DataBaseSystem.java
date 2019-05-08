/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConnection;

import ECS.Components.AttackComponent;
import ECS.Components.Collidable;
import ECS.Components.Electricity;
import ECS.Components.Enemy;
import ECS.Components.ExtraHealth;
import ECS.Components.Item;
import ECS.Components.Movement;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Talkative;
import ECS.Components.Tool;
import ECS.Components.Transform;
import ECS.Components.WorldEntity;
import ECS.EntityManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.Spring;

/**
 *
 * @author pepe_
 */
public class DataBaseSystem {

    public DataBaseSystem() {
    }
    
    public PreparedStatement insertObjects(int id, Object myObject, String sClass, java.sql.Connection conn, PreparedStatement ps) throws IOException{
        //System.out.println(id + " " + sClass);
        try{
            //Pass information to bytes
            ByteArrayOutputStream byteArray= new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteArray);
            oos.writeObject(myObject);
            //load command of mysql
            
            //Set values 
            //ps.clearParameters();
            ps.setInt(1, id);
            ps.setBytes(2, byteArray.toByteArray());
            ps.setString(3, sClass);
            ps.addBatch();
            
        }catch (Exception e){
            System.err.println("Error serialization insert "+e);
        }
        return ps;
    } 
    
    public void selectObjects(java.sql.Connection conn, java.sql.PreparedStatement ps,EntityManager em) throws SQLException, IOException, ClassNotFoundException{
        Object myObject=new Object();
        int id;
        String sClass;
        
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                //Se obtiene el campo blob
                id = rs.getInt("id");
                Blob blob = rs.getBlob("component");
                sClass = rs.getString("class");
                
                //Reconstrucción del objeto
                ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());
                
                myObject = (Object) ois.readObject();
                
                if("ECS.Components.Talkative".equals(sClass)){
                    em.addComponetToEntity((int)id, (Talkative)myObject);
                }
                else if("ECS.Components.Player".equals(sClass)){
                    em.addComponetToEntity((int)id, (Player)myObject);
                }
                else if("ECS.Components.Movement".equals(sClass)){
                    em.addComponetToEntity((int)id, (Movement)myObject);
                }
                else if("ECS.Components.Item".equals(sClass)){
                    em.addComponetToEntity((int)id, (Item)myObject);
                }
                else if("ECS.Components.Tool".equals(sClass)){
                    em.addComponetToEntity((int)id, (Tool)myObject);
                }
                else if("ECS.Components.Transform".equals(sClass)){
                    em.addComponetToEntity((int)id, (Transform)myObject);
                }
                else if("ECS.Components.WorldEntity".equals(sClass)){
                    em.addComponetToEntity((int)id, (WorldEntity)myObject);
                }
                else if("ECS.Components.Sprite".equals(sClass)){
                    //em.addComponetToEntity((int)id, (Sprite)myObject);
                }
                else if("ECS.Components.Electricity".equals(sClass)){
                    em.addComponetToEntity((int)id, (Electricity)myObject);
                }
                else if("ECS.Components.Enemy".equals(sClass)){
                    em.addComponetToEntity((int)id, (Enemy)myObject);
                }
                else if("ECS.Components.AttackComponent".equals(sClass)){
                    em.addComponetToEntity((int)id, (AttackComponent)myObject);
                }
                else if("ECS.Components.ExtraHealth".equals(sClass)){
                    em.addComponetToEntity((int)id, (ExtraHealth)myObject);
                }
                else if("ECS.Components.Playable".equals(sClass)){
                    em.addComponetToEntity((int)id, (Playable)myObject);
                }
                else if("ECS.Components.Collidable".equals(sClass)){
                    em.addComponetToEntity((int)id, (Collidable)myObject);
                }
                
                //System.out.println(((Transform)myObject));
                
                System.out.println(myObject);
            }
    }
    
    public void insertSerialization(Object myObject) throws IOException{
        
        try{
            // create a mysql database connection
                    String myDriver = "com.mysql.jdbc.Driver";
                    String myUrl = "jdbc:mysql://remotemysql.com/UenUhgqeHb";
                    Class.forName(myDriver);
                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/UenUhgqeHb?rewriteBatchedStatements=true","UenUhgqeHb","uGStDaKrpw");
                    
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
    
