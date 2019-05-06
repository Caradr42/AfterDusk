/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConnection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Insert {
   
    public Insert() {

    }

    public static void insertEntity(int id, String name) {
        try {
            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://remotemysql.com/UenUhgqeHb";
            Class.forName(myDriver);
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/UenUhgqeHb", "UenUhgqeHb", "uGStDaKrpw");

            // create a sql date object so we can use it in our INSERT statement
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            // the mysql insert statement
            String query = " insert into Entity (Entity_ID, Entity_Name)"
                    + " values (?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, name);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Error " + e2);
        }
    }
}
