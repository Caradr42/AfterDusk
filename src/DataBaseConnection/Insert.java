package DataBaseConnection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 * Insert to the database
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 25/01/2018 
 * @versión 1.0 
 */
public class Insert implements Runnable {

    public int id = 0;
    public String name = "";
    public String methodKey = "";
    public Thread t;
    
    public boolean insertGoing;
    public boolean insertFinished;

    /**
     * class MyThread implements Runnable { String name; Thread t; MyThread
     * String thread){ name = threadname; t = new Thread(this, name);
     * System.out.println("New thread: " + t); t.start(); }
     *
     * @param methodKey
     * @param id
     * @param name
     */
    public Insert() {

    }

    /**
     * makes the insertion
     * @param methodKey
     * @param id
     * @param name 
     */
    public void makeInsert(String methodKey, int id, String name) {
        this.id = id;
        this.name = name;
        this.methodKey = methodKey;
        insertFinished = false;
        insertGoing = false;
        t = new Thread(this, "insertThread");
        t.start();
    }

    public void start() {


    }

    /**
     * When the run method finishes, the thread dies
     */
    @Override
    public void run() {
        
        insertGoing = true;

        //System.out.println("fefe");
        //If method code is null, do nothing
        if (methodKey.equals("")) {
            //System.out.println("entered");
        } //else if method code == insertEntity, insert an entity
        else if (methodKey.equals("insertEntity")) {
            //System.out.println(name);
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

            //Cleaning static variables
            methodKey = "";
            id = 0;
            name = "";
        }
        
        insertFinished = true;
        insertGoing = false;

    }
}
