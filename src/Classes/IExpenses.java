 
package Classes;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author baransonmez
 */
public interface IExpenses {
    
    public double calculateExpense(  ); 
    public double calculateSumExpense(  );
    public double calculateSpecialExpenseSum( String expName );
    void addExpenseToDB (String userName, String type,String ExpenseName ,int amount, String date, int price );
    void tableUpdate (DefaultTableModel model);
    
}
