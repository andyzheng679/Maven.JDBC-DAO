import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static String username = "";
    static String password = "";
    static String dbUrl = "jdbc:mysql://localhost:3306/";
    static Logger demoLog =  Logger.getLogger("demoJDBC");


    public static Connection getConnection()
    {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public static void main(String[] argv) {
        demoLog.log(Level.INFO, "somestuff");
        System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);

            System.out.println("SQL Connection to database established!");

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection Failed! Check output console");
            return;
        }
        finally {
            try
            {
                if(connection != null)
                    connection.close();
                System.out.println("Connection closed !!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


