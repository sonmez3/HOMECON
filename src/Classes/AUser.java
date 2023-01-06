package Classes;

import Frames.LoginFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author baransonmez
 */
public abstract class AUser {
    
    public String name;
    public String surname;
    public String userName;
    public String password;

    public AUser(String name, String surname, String userName, String password) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
    }
    
    public void changeName(String newName) {
        Connection connection;
        dbHelper dbHelper1 = new dbHelper();
        Statement stm;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = dbHelper1.getConnection();
            stm = connection.createStatement();
            resultSet = stm.executeQuery("select * from User");
            while (resultSet.next()) {

                String a = resultSet.getString("UserName");

                System.out.println(a);
                if (LoginFrame.UserName.equals(a)) {

                    try {
                        String sql = "UPDATE User SET UserName=? where UserName=?";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, newName);
                        statement.setString(2, LoginFrame.UserName);
                        statement.executeUpdate();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger("22" + Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }
    
    
    public void changePass(String newPass) {
        Connection connection;
        dbHelper dbHelper1 = new dbHelper();
        Statement stm;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = dbHelper1.getConnection();
            stm = connection.createStatement();
            resultSet = stm.executeQuery("select * from User");
            while (resultSet.next()) {

                String a = resultSet.getString("UserName");

                System.out.println(a);
                if (LoginFrame.UserName.equals(a)) {

                    try {
                        String sql = "UPDATE User SET Password=? where UserName=?";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, newPass);
                        statement.setString(2, LoginFrame.UserName);
                        statement.executeUpdate();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger("22" + Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }
    
}
