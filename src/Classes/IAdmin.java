package Classes;

import Frames.AddNewHousemateFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author baransonmez
 */
public interface IAdmin {
    void deleteAll();
    void deleteAllExpenses();
    void addNewHousemate(String name,String surname, String userName, String password);
     
}
