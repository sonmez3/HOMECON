package Classes;

import java.io.File;
import java.sql.*;

/**
 *
 * @author kadir
 */
public class dbHelper {

    private String ConUrl = "jdbc:sqlite:C:/sqlite1234/Homecon.db";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConUrl);
    }

    public void showErrorMessage(SQLException e) {
        System.out.println("Error:" + e.getMessage());
    }

    public void createDatabase() {
        String url = "jdbc:sqlite:C:/sqlite1234/Homecon.db";
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("the driver name is:" + meta.getDriverName());
                System.out.println("Database created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createAllTable() {
        String url = "jdbc:sqlite:C:/sqlite1234/Homecon.db";
        String sql1 = "CREATE TABLE IF NOT EXISTS User\n"
                + "(UserID INTEGER UNIQUE,\n"
                + "UserName TEXT UNIQUE,\n"
                + "Password TEXT,\n"
                + "Name TEXT,\n"
                + "Surname TEXT,\n"
                + "PRIMARY KEY(UserID AUTOINCREMENT))";
        String sql2 = "CREATE TABLE IF NOT EXISTS admin\n"
                + "(password TEXT,\n"
                + "adminID INTEGER,\n"
                + "UNIQUE(adminID))";
        String sql3 = "CREATE TABLE IF NOT EXISTS expenses\n"
                + "(User TEXT,\n"
                + "ExpenseType TEXT,\n"
                + "ExpenseName TEXT,\n"
                + "Amount INTEGER,\n"
                + "Date TEXT,\n"
                + "Price INTEGER\n"
                + " )";
        String sql4 = "CREATE TABLE IF NOT EXISTS need\n "
                + "(NeedsName TEXT UNIQUE,\n"
                + "id INTEGER NOT NULL, PRIMARY KEY(id AUTOINCREMENT))";     
        String sql5 = "INSERT INTO admin(password,adminID)\n" 
                +"SELECT 12345, 1 \n" 
                +"WHERE NOT EXISTS(SELECT 1 FROM admin WHERE adminID = 1 AND password = 12345); ";
       
       try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
            stmt.execute(sql5);
            stmt.executeUpdate(sql5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    public void createFolder(){
        new File("/C:/sqlite1234").mkdirs();
    }

}
