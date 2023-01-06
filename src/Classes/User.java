package Classes;

import Frames.AddNewHousemateFrame;
import Frames.AdminFrame;
import Frames.AdminPasswordFrame;
import Frames.LoginFrame;
import Frames.MainMenuFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User extends AUser {

    Connection connection = null;
    dbHelper dbHelper1 = new dbHelper();
    Statement statement;
    ResultSet resultSet;
    AdminFrame admin = new AdminFrame();
    AdminPasswordFrame APF = new AdminPasswordFrame();
    LoginFrame logFrame = new LoginFrame();
    MainMenuFrame main = new MainMenuFrame();

    public User(String name, String surname, String userName, String password) {
        super(name, surname, userName, password);

    }

    public void logInCheck(String name, String password) {
        try {
            connection = dbHelper1.getConnection();
            statement = connection.createStatement();

            if (name.equals("admin")) {
                resultSet = statement.executeQuery("select * from admin");
                while (resultSet.next()) {
                    String pass = resultSet.getString("password");
                    if (password.equals(pass)) {
                         main.setVisible(true);
                         LoginFrame.bool = false;
                    }
                }

            } else {
                 resultSet = statement.executeQuery("select * from User");
                while (resultSet.next()) {

                    String a = resultSet.getString("UserName");
                    
                    String b = resultSet.getString("Password");

                    System.out.println(a + b);
                    if (name.equals(a) && password.equals(b)) {
                        main.setVisible(true);
                        System.out.println("Classes.User.logInCheck()");
                         
                        LoginFrame.bool = false;
                        break;

                    }else{
                        main.setVisible(false);
                        LoginFrame.bool = true;
                        
                        
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(  e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void AdminCheck(String password) {
        try {
            connection = dbHelper1.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from admin");
            

            while (resultSet.next()) {

                String pass = resultSet.getString("password");

                if (password.equals(pass)) {
                    admin.setVisible(true);
                    AdminPasswordFrame.bool=false;
                    break;

                }else{
                    admin.setVisible(false);
                     AdminPasswordFrame.bool=true;
                    //System.out.println("hata");
                }
            }
        } catch (SQLException e) {
            System.out.println( e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
