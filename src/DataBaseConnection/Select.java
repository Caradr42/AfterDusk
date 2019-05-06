/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConnection;

import Utility.Pair;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author MARTHA
 */
public class Select {
    
    public Select() {
        
    }

    public static ArrayList<Pair> selectEntities(String query) {
        
        ArrayList<Pair> entities = new ArrayList<>();
        
        try {
            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://remotemysql.com/UenUhgqeHb";
            Class.forName(myDriver);
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/UenUhgqeHb", "UenUhgqeHb", "uGStDaKrpw");

            // create a sql date object so we can use it in our INSERT statement
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            //String query = "SELECT * FROM Entity";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                int id = rs.getInt("Entity_ID");
                String entityName = rs.getString("Entity_Name");

                // print the results
                //System.out.format("ID: " + id);
                //System.out.format("EntityName: " + entityName);
                
                entities.add(new Pair(id, entityName));
            }
            st.close();
        } catch (Exception e2) {
            System.err.println("Got an exception! ");
            System.err.println(e2.getMessage());
        }
        
        return entities;
    }

}
