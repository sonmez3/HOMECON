
package Classes;

import Frames.NeedsFrame;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import java.sql.*;



public class Needs {
    
    public String[] arr = new String[100] ;
    Statement statement ;
    PreparedStatement state ;
    ResultSet resultSet;
    Connection connection =null;
    dbHelper dbHelper1 = new dbHelper();
  
    public void NeedAddToDB(String textData){
        
        String sql = "INSERT INTO need(NeedsName) VALUES(?)";
        try {
           
            connection = dbHelper1.getConnection();
            state = connection.prepareStatement(sql);
            state.setString(1, textData);
            
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(NeedsFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                state.executeUpdate();
                state.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(NeedsFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }
    }
    
    
    public void tableUpdate(){
        int i=0;
        try {
          connection = dbHelper1.getConnection();
            statement = connection.createStatement();
            System.out.println("tableupdate");
            resultSet = statement.executeQuery("select * from need");
            
            while(resultSet.next()){
                String nName = resultSet.getString("NeedsName");
                System.out.println(nName);
                arr[i] = nName;
                 i++;
                
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            
        }
        
        
    }
    
  
    
}
