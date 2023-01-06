package Classes;

import Frames.LoginFrame;
import Frames.NeedsFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author baransonmez
 */
public class Expenses implements IExpenses {

    Statement statement;
    PreparedStatement state;
    ResultSet resultSet;
    Connection connection = null;
    dbHelper dbHelper1 = new dbHelper();
    double sum;
    double userSum;
    double sumAll;
    double specialSum;
    int counter;
    Shop shop;
    Bill bill;

    public int NumberOfHousemate() {
        try {
            counter = 0;
            connection = dbHelper1.getConnection();
            String sql = "select * from User ";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("UserName");
                counter++;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return counter;
    }

    public double calculateExpense() {
        try {
            sum = 0;
            
            sum = calculateSumExpense();

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            userSum = 0;
            connection = dbHelper1.getConnection();
            String sql = "select * from expenses where User = ?";
            state = connection.prepareStatement(sql);
            state.setString(1, LoginFrame.UserName);
            resultSet = state.executeQuery();

            while (resultSet.next()) {
                int price = resultSet.getInt("Price");
                userSum = userSum + price;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Classes.Expenses.calculateExpense()");
        }
        System.out.println(userSum);
        int num = NumberOfHousemate();
        double resultSum = ((sum) / num)-userSum;

        return resultSum;

    }

    public void addExpenseToDB(String userName, String type, String expenseName, int amount, String date, int price) {
        String sql = "INSERT INTO expenses(User,ExpenseType,ExpenseName,Amount,Date,Price) VALUES(?,?,?,?,?,?)";
        try {

            connection = dbHelper1.getConnection();
            state = connection.prepareStatement(sql);
            state.setString(1, userName);
            state.setString(2, type);
            state.setString(3, expenseName);
            state.setInt(4, amount);
            state.setString(5, date);
            state.setInt(6, price);

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

    @Override
    public void tableUpdate(DefaultTableModel model) {

        try {
            connection = dbHelper1.getConnection();
            statement = connection.createStatement();
            System.out.println("tableupdate");
            resultSet = statement.executeQuery("select * from expenses");

            while (resultSet.next()) {
                String UserName = resultSet.getString("User");
                String type = resultSet.getString("ExpenseType");
                int amount = resultSet.getInt("Amount");
                String date = resultSet.getString("Date");
                int price = resultSet.getInt("Price");;
                model.addRow(new Object[]{UserName, type, amount, date, price});

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public double calculateSumExpense() {

        try {
            sumAll = 0;
            connection = dbHelper1.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from expenses");

            while (resultSet.next()) {

                double price = resultSet.getInt("Price");
                sumAll = sumAll + price;

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sumAll;
    }

    @Override
    public  double calculateSpecialExpenseSum(String expName) {
        try {
            specialSum = 0;
            connection = dbHelper1.getConnection();
            String sql1 = "select * from expenses where ExpenseType = ?";
            state = connection.prepareStatement(sql1);
            state.setString(1, expName);
            resultSet = state.executeQuery();

            while (resultSet.next()) {
                System.out.println("1");
                int price = resultSet.getInt("Price");
                specialSum = specialSum + price;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return specialSum;

    }

}
