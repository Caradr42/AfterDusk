package DataBaseConnection;

import Utility.Pair;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Select in the database
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 25/01/2018 
 * @versión 1.0 
 */
public class Select implements Runnable {
    
    public String methodKey;
    public String query;
    public Thread t;
    public ResultSet resSet;
    
    public boolean selectGoing;
    public boolean selectFinished;
    /**
     * Constructor
     */
    public Select() {
        methodKey = "";
        query = "";

    }
    /**
     * makes the selection in the database
     * @param methodKey
     * @param query 
     */
    public void makeSelect(String methodKey, String query) {
        this.methodKey = methodKey;
        this.query = query;
        selectGoing = false;
        selectFinished = false;
        t = new Thread(this, "insertThread");
        t.start();
    }

    public void selectEntities() {
        ResultSet rs = null;
        
        //ArrayList<Pair> entities = new ArrayList<>();
        
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
            resSet = st.executeQuery(query);

            // iterate through the java resultset
            while (resSet.next()) {
                int id = resSet.getInt("Entity_ID");
                String entityName = resSet.getString("Entity_Name");

                // print the results
                //System.out.println("ID: " + id);
                //System.out.println("EntityName: " + entityName);
                
                //entities.add(new Pair(id, entityName));
            }
            st.close();
        } catch (Exception e2) {
            System.err.println("Got an exception! ");
            System.err.println(e2.getMessage());
        }
        
        //return resSet;
    }

    @Override
    public void run() {
        
        selectGoing = true;
        
        if(methodKey == "") {
            
        }
        
        else if(methodKey.equals("selectEntities")) {
            selectEntities();
            
            methodKey = "";
            query = "";       
        }
        
        selectFinished = true;
        selectGoing = false;
    }

}
