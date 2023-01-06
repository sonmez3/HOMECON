package Classes;

import Frames.AddNewHousemateFrame;
import Frames.LoginFrame;
import Frames.MainMenuFrame;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author baransonmez
 */
public class Admin extends AUser implements IAdmin {

    Connection connection = null;
    dbHelper dbHelper1 = new dbHelper();
    Statement statement;
    ResultSet resultSet;
    MainMenuFrame main = new MainMenuFrame();
    LoginFrame log = new LoginFrame();

    public Admin(String name, String surname, String userName, String password) {
        super(name, surname, userName, password);
    }

    @Override
    public void deleteAll() {
        String sql1 = "delete  from  User";
        String sql2 = "delete  from  need";
        String sql3 = "delete  from   expenses";
        try {

            connection = dbHelper1.getConnection();
            statement = connection.createStatement();
            statement.executeQuery(sql1);

            statement.executeQuery(sql2);

            statement.executeQuery(sql3);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                statement.executeUpdate(sql1);
                statement.executeUpdate(sql2);
                statement.executeUpdate(sql3);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }

    }
    
    @Override
    public void deleteAllExpenses() {
        
        String sql3 = "delete  from   expenses";
        
        try {

            connection = dbHelper1.getConnection();
            statement = connection.createStatement();
            
            statement.executeQuery(sql3);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                
                statement.executeUpdate(sql3);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }
    }

    public void addNewHousemate(String name, String surname, String userName, String password) {
        Connection connection = null;
        dbHelper dbHelper1 = new dbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        //!!!!!!!!! PERSON YERİNE ADMİN  !!!!!!!
        User person = new User(name, surname, userName, password);

        try {
            String username = userName;
            String pass = password;
            String nameIn = name;
            String surnameIn = surname;
            connection = dbHelper1.getConnection();
            String sql = "insert into User (UserName,Password,Name,Surname) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, pass);
            statement.setString(3, nameIn);
            statement.setString(4, surnameIn);
            statement.executeUpdate();
            System.out.println("AddNewHousemate.jButton1ActionPerformed() ekleme");
        } catch (Exception e) {
            System.out.println("hkullanıcı mevcut");
        } finally {
            try {
                connection.close();
                statement.close();

            } catch (SQLException ex) {
                System.out.println("Classes.Admin.addNewHousemate()");
            }

        }

    }

    

    

    
    public void changeAdminPass(String newAdminPass){
        Connection connection;
        dbHelper dbHelper1 = new dbHelper();
        Statement stm;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = dbHelper1.getConnection();
            stm = connection.createStatement();
            resultSet = stm.executeQuery("select * from admin");
            while (resultSet.next()) {

                String a = resultSet.getString("password");

                System.out.println(a);
                 

                    try {
                        String sql = "UPDATE admin SET password=? where adminID=?";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, newAdminPass);
                        statement.setString(2, "1");
                        statement.executeUpdate();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger("22" + Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }

    
        
    
    
}
