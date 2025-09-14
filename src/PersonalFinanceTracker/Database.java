/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersonalFinanceTracker;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.mindrot.jbcrypt.BCrypt;




/**
 *
 * @author HP ElitePC
 */
public class Database {
    
     JTextField firstNameField;   
    JTextField lastNameField;
    JTextField emailField;
    JTextField phoneField;   
    JTextField phone2Field; 
    JTextField addrField;
    JTextField empIdField; 
    JTextField genderField;    
    JTextField first;

     DefaultTableModel tableModel;
    JPasswordField pwdField;
    JTextField userNameField;
    JLabel errorLabel;   
    

       
        
        public String getHashedPassword(String userName) {
        
               String url = "jdbc:mysql://localhost:3306/finance_tracker";
               
                 
                String sql = "SELECT password FROM users WHERE userName = ?";

                try (Connection conn = DriverManager.getConnection(url, "root", "");
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    // Set parameters\
                    
                     pstmt.setString(1, userName);
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            return rs.getString("password");
                        }

                    // Execute insert statement
                 
             
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Show error message
                    
                }
        
                return null;
        }

    
    public boolean CreateUser(String Email,String Username,String Password) {
    
        boolean state = false;
            
          String url = "jdbc:mysql://localhost:3306/finance_tracker";
               
                 
                String sql = "INSERT INTO `users`( `Email`, `userName`, `Password`) VALUES (?, ?, ?)";

                try (Connection conn = DriverManager.getConnection(url, "root", "");
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    // Set parameters\
                    
                    pstmt.setString(1, Email);
                    pstmt.setString(2, Username);
                    pstmt.setString(3, Password);   
                    

                    // Execute insert statement
                 
              int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    state = true;
                }
               
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Error: Unable to save data to the database.");
                }
                
                return state;
    }
         public boolean insertTransaction(int userId, String type, String category, BigDecimal amount, java.sql.Date date) {
             
            boolean state = false;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "INSERT INTO transactions (user_id, Type, Category, Amount, Date) VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, userId);   
                pstmt.setString(2, type);
                pstmt.setString(3, category);
                pstmt.setBigDecimal(4, amount);
                pstmt.setDate(5, date);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    state = true;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to save transaction to the database.");
            }
            return state;
        }   

    
        public DefaultTableModel Transactions() {
        
            Vector<String> header = new Vector<>();
            Vector<Vector<Object>> data = new Vector<>();

            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT Id, Type, Category, Amount, Date FROM transactions WHERE user_id = ? ORDER BY Date ASC";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, Session.userId);  // filter by current user

                ResultSet res = pstmt.executeQuery();

                // Set table headers
                header.add("Id");
                header.add("Type");
                header.add("Category");
                header.add("Amount");
                header.add("Date");

                
                
                int count = 0;

                    while (res.next()) {
                        Vector<Object> rowRecords = new Vector<>();
                        count++;
                                
                    rowRecords.add(count);           // Use getInt for Id
                    rowRecords.add(res.getString("Type"));
                    rowRecords.add(res.getString("Category"));
                    rowRecords.add(res.getBigDecimal("Amount")); // Use getBigDecimal for numeric
                    rowRecords.add(res.getTimestamp("Date"));
                    data.add(rowRecords);
                }

                res.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to load transactions data.");
            }

            return new DefaultTableModel(data, header);
        }

    
            public boolean deleteTransactionRecord(int id) {
                
                    String url = "jdbc:mysql://localhost:3306/finance_tracker";

                    // Only delete if transaction belongs to current user
                    String sql = "DELETE FROM transactions WHERE Id = ? AND user_id = ?";

                    try (Connection conn = DriverManager.getConnection(url, "root", "");
                         PreparedStatement pstmt = conn.prepareStatement(sql)) {

                        pstmt.setInt(1, id);
                        pstmt.setInt(2, Session.userId);  // ensure current user's ownership

                        int affected = pstmt.executeUpdate();

                        return affected > 0;

                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
        }

    
                public boolean updateTransaction(int id, String category, BigDecimal amount, java.sql.Date date) {
                        boolean updated = false;
                        String url = "jdbc:mysql://localhost:3306/finance_tracker";

                        // Only update if transaction belongs to current user
                        String sql = "UPDATE transactions SET Category = ?, Amount = ?, Date = ? WHERE Id = ? AND user_id = ?";

                        try (Connection conn = DriverManager.getConnection(url, "root", "");
                             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                            pstmt.setString(1, category);
                            pstmt.setBigDecimal(2, amount);
                            pstmt.setDate(3, date);
                            pstmt.setInt(4, id);
                            pstmt.setInt(5, Session.userId);  // check ownership

                            int rowsUpdated = pstmt.executeUpdate();
                            updated = rowsUpdated > 0;

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error updating Transaction record.");
                        }

                        return updated;
            }

    
    
      public boolean insertIncome(String Category, BigDecimal Amount, java.sql.Date Date) {
            boolean state = false;

            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "INSERT INTO `income` (`user_id`, `Category`, `Amount`, `Date`) VALUES (?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Use Session.userId directly
                pstmt.setInt(1, Session.userId);
                pstmt.setString(2, Category);
                pstmt.setBigDecimal(3, Amount);
                pstmt.setDate(4, Date);

                int rowsAffected = pstmt.executeUpdate();
                state = rowsAffected > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to save data to the database.");
            }

            return state;
        }

    
        public DefaultTableModel incomeTable() {
            
            Vector<String> header = new Vector<>();
            Vector<Vector<Object>> data = new Vector<>();

            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT * FROM income WHERE user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Set the current user's ID
                pstmt.setInt(1, Session.userId);

                try (ResultSet res = pstmt.executeQuery()) {
                    header.add("Id");
                    header.add("Category");
                    header.add("Amount");
                    header.add("Date");

                    int count = 0;

                    while (res.next()) {
                        Vector<Object> rowRecords = new Vector<>();
                        count++;

                        rowRecords.add(count); // This is just a row count, not DB id
                        rowRecords.add(res.getString("Category"));
                        rowRecords.add(res.getBigDecimal("Amount")); 
                        rowRecords.add(res.getDate("Date"));

                        data.add(rowRecords);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to load income data from the database.");
            }

            return new DefaultTableModel(data, header);
    }

    
        public boolean deleteIncomeRecord(int id) {
            
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = "DELETE FROM income WHERE id = ? AND user_id = 0";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                
                pstmt.setInt(1, id);
                pstmt.setInt(2, Session.userId);

                int affected = pstmt.executeUpdate();

                return affected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        
         public boolean updateIncome(int id, String category, BigDecimal amount, java.sql.Date date) {
             
            boolean updated = false;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = "UPDATE income SET Category = ?, Amount = ?, Date = ? WHERE Id = ? AND user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, category);
                pstmt.setBigDecimal(2, amount);
                pstmt.setDate(3, date);
                pstmt.setInt(4, id);
                pstmt.setInt(5, Session.userId);  // user ID for ownership check

                int rowsUpdated = pstmt.executeUpdate();
                updated = rowsUpdated > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating income record.");
            }

            return updated;
        }

         
         public boolean insertExpenses(String Category, BigDecimal Amount, java.sql.Date Date) {
            boolean state = false;

            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "INSERT INTO `expenses` (`user_id`, `Category`, `Amount`, `Date`) VALUES (?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Use Session.userId directly
                pstmt.setInt(1, Session.userId);
                pstmt.setString(2, Category);
                pstmt.setBigDecimal(3, Amount);
                pstmt.setDate(4, Date);

                int rowsAffected = pstmt.executeUpdate();
                state = rowsAffected > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to save data to the database.");
            }

            return state;
        }
    
        public DefaultTableModel expensesTable() {
            
            Vector<String> header = new Vector<>();
            Vector<Vector<Object>> data = new Vector<>();

            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT * FROM expenses WHERE user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Set the current user's ID
                pstmt.setInt(1, Session.userId);

                try (ResultSet res = pstmt.executeQuery()) {
                    header.add("Id");
                    header.add("Category");
                    header.add("Amount");
                    header.add("Date");

                    int count = 0;

                    while (res.next()) {
                        Vector<Object> rowRecords = new Vector<>();
                        count++;

                        rowRecords.add(count); // This is just a row count, not DB id
                        rowRecords.add(res.getString("Category"));
                        rowRecords.add(res.getBigDecimal("Amount")); 
                        rowRecords.add(res.getDate("Date"));

                        data.add(rowRecords);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to load income data from the database.");
            }

            return new DefaultTableModel(data, header);
    }
    
        public boolean deleteExpenseRecord(int id) {
            
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = "DELETE FROM income WHERE Id = ? AND user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, id);
                pstmt.setInt(2, Session.userId);

                int affected = pstmt.executeUpdate();

                return affected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        
         public boolean updateExpenses(int id, String category, BigDecimal amount, java.sql.Date date) {
             
            boolean updated = false;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = "UPDATE income SET Category = ?, Amount = ?, Date = ? WHERE Id = ? AND user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, category);
                pstmt.setBigDecimal(2, amount);
                pstmt.setDate(3, date);
                pstmt.setInt(4, id);
                pstmt.setInt(5, Session.userId);  // user ID for ownership check

                int rowsUpdated = pstmt.executeUpdate();
                updated = rowsUpdated > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating income record.");
            }

            return updated;
        }
    
         public int getUserId(String username) {
            int userId = 0;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/finance_tracker";
                try (Connection con = DriverManager.getConnection(url, "root", "");
                     PreparedStatement stmt = con.prepareStatement("SELECT userId FROM users WHERE userName = ?")) {

                    stmt.setString(1, username);
                    ResultSet res = stmt.executeQuery();

                    if (res.next()) {
                        userId = res.getInt("userId");
                    }

                    res.close();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();  // Better for debugging
            }

            return userId;
}

         
        public boolean insertTotalIncome(int userId, BigDecimal income) {
            
            boolean state = false;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = """
                INSERT INTO assets (userId, Income, Expenses, Savings, Budget)
                VALUES (?, ?, 0, 0, 0)
                ON DUPLICATE KEY UPDATE Income = VALUES(Income)
            """;

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, userId);
                stmt.setBigDecimal(2, income);

                int rowsAffected = stmt.executeUpdate();
                state = rowsAffected > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving income to assets.");
            }

            return state;
        }
        
        
        public boolean insertTotalExpense(int userId, BigDecimal expense) {
            
            boolean state = false;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = """
                INSERT INTO assets (userId, Income, Expenses, Savings, Budget)
                VALUES (?, 0, ?, 0, 0)
                ON DUPLICATE KEY UPDATE Expenses = VALUES(Expenses)
            """;

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, userId);
                stmt.setBigDecimal(2, expense);

                int rowsAffected = stmt.executeUpdate();
                state = rowsAffected > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving income to assets.");
            }

            return state;
        }
        
        public boolean insertTotalBudget(int userId, BigDecimal budget) {
            
            boolean state = false;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = """
                INSERT INTO assets (userId, Income, Expenses, Savings, Budget)
                VALUES (?, 0, 0, 0, ?)
                ON DUPLICATE KEY UPDATE Budget = VALUES(Budget)
            """;

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, userId);
                stmt.setBigDecimal(2, budget);

                int rowsAffected = stmt.executeUpdate();
                state = rowsAffected > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Error saving budget to assets.");
            }

            return state;
        }
        
        public boolean insertTotalSavings(int userId, BigDecimal savings) {
            
            boolean state = false;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = """
                INSERT INTO assets (userId, Income, Expenses, Savings, Budget)
                VALUES (?, 0, 0, ?, 0)
                ON DUPLICATE KEY UPDATE Savings = VALUES(Savings)
            """;

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, userId);
                stmt.setBigDecimal(2, savings);

                int rowsAffected = stmt.executeUpdate();
                state = rowsAffected > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving income to assets.");
            }

            return state;
        }

        public BigDecimal getIncomeByUserId(int userId) {
            
            BigDecimal income = BigDecimal.ZERO;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT Income FROM assets WHERE UserId = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    income = rs.getBigDecimal("Income");
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return income;
        }
            
        public BigDecimal getExpensesByUserId(int userId) {
            
            BigDecimal expense = BigDecimal.ZERO;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT expenses FROM assets WHERE UserId = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    expense = rs.getBigDecimal("expenses");
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return expense;
        }

        public BigDecimal getSavingsByUserId(int userId) {
            
            BigDecimal savings = BigDecimal.ZERO;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT Savings FROM assets WHERE UserId = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    savings = rs.getBigDecimal("Savings");
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return savings;
        }
        
        
        public BigDecimal getBudgetByUserId(int userId) {
            
            BigDecimal budget = BigDecimal.ZERO;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT Budget FROM assets WHERE UserId = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    budget = rs.getBigDecimal("Budget");
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return budget;
        }
        
         public BigDecimal getTotalMonthlyBudget(int userId) {
             
               String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT SUM(monthly_limit) FROM budget WHERE user_id = ?";
            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return BigDecimal.ZERO;
    }
         
         
          public BigDecimal getTotalMonthlyIncome(int userId) {
             
               String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT SUM(Amount) FROM income WHERE user_id = ?";
            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return BigDecimal.ZERO;
    } 
          
          
          public BigDecimal getTotalExpense(int userId) {
             
               String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT SUM(Amount) FROM expenses WHERE user_id = ?";
            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return BigDecimal.ZERO;
    }

        
        public boolean insertGoals(String Type, String Name, BigDecimal Amount, java.sql.Date Date) {
            boolean state = false;

            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "INSERT INTO `goals` (`user_id`, `goal_type`,`goal_name`, `Amount`, `Deadline`) VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Use Session.userId directly
                pstmt.setInt(1, Session.userId);
                pstmt.setString(2, Type);
                pstmt.setString(3, Name);
                pstmt.setBigDecimal(4, Amount);
                pstmt.setDate(5, Date);

                int rowsAffected = pstmt.executeUpdate();
                state = rowsAffected > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to save data to the database.");
            }

            return state;
        }
        
        public List<Goals> getGoalsByUserId(int userId) {
            
            List<Goals> goals = new ArrayList<>();
            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT * FROM goals WHERE user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();

                
                
                while (rs.next()) {
                    Goals goal = new Goals(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("goal_type"),
                        rs.getString("goal_name"),
                        rs.getBigDecimal("amount"),
                        rs.getDate("deadline")
                    );
                    goals.add(goal);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading goals from database.");
            }

            return goals;
        }
        
        public boolean updateGoals(int id, int userid, String Type, String Name, BigDecimal Amount, java.sql.Date Date) {
             
            boolean updated = false;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = "UPDATE goals SET goal_type = ?, goal_name = ?, Amount = ?, Deadline = ? WHERE id = ? AND user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, Type);       
                pstmt.setString(2, Name);      
                pstmt.setBigDecimal(3, Amount); 
                pstmt.setDate(4, Date);         
                pstmt.setInt(5, id);            
                pstmt.setInt(6, userid);        


                int rowsUpdated = pstmt.executeUpdate();
                updated = rowsUpdated > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating income record.");
            }

            return updated;
        }
        
        
         public boolean deleteGoals(int id) {
            
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = "DELETE FROM goals WHERE Id = ? AND user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, id);
                pstmt.setInt(2, Session.userId);

                int affected = pstmt.executeUpdate();

                return affected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
         
          public boolean insertBudget(int userId, String category, BigDecimal limit) {
              
               String url = "jdbc:mysql://localhost:3306/finance_tracker";
               
            String sql = "INSERT INTO budget (user_id, category, monthly_limit) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, userId);
                stmt.setString(2, category);
                stmt.setBigDecimal(3, limit);

                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to add budget.");
                return false;
            }
        }
          
          public DefaultTableModel budgetTable() {
            
            Vector<String> header = new Vector<>();
            Vector<Vector<Object>> data = new Vector<>();

            String url = "jdbc:mysql://localhost:3306/finance_tracker";
            String sql = "SELECT * FROM budget WHERE user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Set the current user's ID
                pstmt.setInt(1, Session.userId);

                try (ResultSet res = pstmt.executeQuery()) {
                    header.add("Id");
                    header.add("Category");
                    header.add("Monthly Limit");
                   

                    int count = 0;

                    while (res.next()) {
                        Vector<Object> rowRecords = new Vector<>();
                        count++;

                        rowRecords.add(count); // This is just a row count, not DB id
                        rowRecords.add(res.getString("Category"));
                        rowRecords.add(res.getBigDecimal("monthly_limit")); 
                        

                        data.add(rowRecords);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to load budget data from the database.");
            }

            return new DefaultTableModel(data, header);
    }
    
        public boolean deleteBudgetRecord(int id) {
            
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = "DELETE FROM budget WHERE Id = ? AND user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, id);
                pstmt.setInt(2, Session.userId);

                int affected = pstmt.executeUpdate();

                return affected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        
         public boolean updateBudget(int id, String category, BigDecimal amount) {
             
            boolean updated = false;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = "UPDATE budget SET Category = ?, Amount = ?, Date = ? WHERE Id = ? AND user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, category);
                pstmt.setBigDecimal(2, amount);
                pstmt.setInt(3, id);
                pstmt.setInt(4, Session.userId);  // user ID for ownership check

                int rowsUpdated = pstmt.executeUpdate();
                updated = rowsUpdated > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating Budget record.");
            }

            return updated;
        }
         
         
         public List<BudgetSummary> getBudgetVsActual(int userId) {
                List<BudgetSummary> list = new ArrayList<>();
                String url = "jdbc:mysql://localhost:3306/finance_tracker";
                String sql = """
                    SELECT 
                            b.category,
                            b.monthly_limit,
                            COALESCE(SUM(e.amount), 0) AS actual_spent,
                            b.monthly_limit - COALESCE(SUM(e.amount), 0) AS savings
                        FROM budget b
                        LEFT JOIN expenses e 
                            ON b.category = e.category 
                            AND MONTH(b.Date) = MONTH(e.Date)
                            AND YEAR(b.Date) = YEAR(e.Date)
                            AND b.user_id = e.user_id
                        WHERE b.user_id = ?
                          AND MONTH(b.Date) = MONTH(CURRENT_DATE)
                          AND YEAR(b.Date) = YEAR(CURRENT_DATE)
                        GROUP BY b.category, b.monthly_limit
                """;

                try (Connection conn = DriverManager.getConnection(url, "root", "");
                     PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, userId);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        String category = rs.getString("category");
                        BigDecimal budget = rs.getBigDecimal("monthly_limit");
                        BigDecimal actual = rs.getBigDecimal("actual_spent");
                        BigDecimal savings = rs.getBigDecimal("savings");

                        list.add(new BudgetSummary(category, budget, actual, savings));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return list;
            }

        public BigDecimal getBudgetLimitForCategoryThisMonth(int userId, String category) {
            
            BigDecimal limit = BigDecimal.ZERO;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = """
                SELECT monthly_limit
                FROM budget
                WHERE user_id = ?
                  AND category = ?
                  AND MONTH(Date) = MONTH(CURRENT_DATE)
                  AND YEAR(Date) = YEAR(CURRENT_DATE)
                LIMIT 1
            """;

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, userId);
                ps.setString(2, category);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    limit = rs.getBigDecimal("monthly_limit");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return limit;
        }


         public BigDecimal getTotalExpenseForCategoryThisMonth(int userId, String category) {
             
            BigDecimal total = BigDecimal.ZERO;
            String url = "jdbc:mysql://localhost:3306/finance_tracker";

            String sql = """
                SELECT COALESCE(SUM(amount), 0) as total
                FROM expenses
                WHERE user_id = ?
                  AND category = ?
                  AND MONTH(Date) = MONTH(CURRENT_DATE)
                  AND YEAR(Date) = YEAR(CURRENT_DATE)
            """;

            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setString(2, category);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getBigDecimal("total");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return total;
        }


          // Insert a notification
            public boolean insertNotification(int userId, String message) {
                String url = "jdbc:mysql://localhost:3306/finance_tracker";
                String sql = "INSERT INTO notifications (user_id, message) VALUES (?, ?)";
                try (Connection conn = DriverManager.getConnection(url, "root", "");
                     PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, userId);
                    ps.setString(2, message);
                    int rows = ps.executeUpdate();
                    return rows > 0;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            
              public List<String> getUnreadNotifications(int userId) {
                  
                List<String> notifications = new ArrayList<>();
                 String url = "jdbc:mysql://localhost:3306/finance_tracker";
                String sql = "SELECT id, message FROM notifications WHERE user_id = ? AND is_read = FALSE ORDER BY created_at DESC";
                try (Connection conn = DriverManager.getConnection(url, "root", "");
                        PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, userId);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        notifications.add(rs.getString("message"));
                        // You can also store the id if you want to mark it as read later
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return notifications;
            }
              
              public boolean markNotificationsAsRead(int userId) {
                  
                  String url = "jdbc:mysql://localhost:3306/finance_tracker";
                String sql = "UPDATE notifications SET is_read = TRUE WHERE user_id = ? AND is_read = FALSE";
                try (Connection conn = DriverManager.getConnection(url, "root", "");
                     PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, userId);
                    int rows = ps.executeUpdate();
                    return rows > 0;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
              
              public Map<String, BigDecimal[]> getTransactionsSummary(int userId, String range) {
                  
                    Map<String, BigDecimal[]> data = new LinkedHashMap<>();

                    String dateFormat = switch (range) {
                        case "daily" -> "%Y-%m-%d";
                        case "weekly" -> "%Y-%u";  // %u is week number (Monday is first day)
                        case "monthly" -> "%Y-%m";
                        default -> throw new IllegalArgumentException("Invalid range: " + range);
                    };

                    String sql = """
                        SELECT DATE_FORMAT(date, ?) as period,
                               SUM(CASE WHEN type = 'Income' THEN amount ELSE 0 END) as income_total,
                               SUM(CASE WHEN type = 'Expense' THEN amount ELSE 0 END) as expense_total
                        FROM transactions
                        WHERE user_id = ?
                        GROUP BY period
                        ORDER BY period
                    """;

                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance_tracker", "root", "");
                         PreparedStatement ps = conn.prepareStatement(sql)) {

                        ps.setString(1, dateFormat);
                        ps.setInt(2, userId);

                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String period = rs.getString("period");
                            BigDecimal income = rs.getBigDecimal("income_total");
                            BigDecimal expense = rs.getBigDecimal("expense_total");
                            data.put(period, new BigDecimal[]{income, expense});
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return data;
                }
              public void checkBudgetsAndUpdateSavings(int userId) {
                  Database db = new Database();
                    List<BudgetSummary> budgets = db.getBudgetVsActual(userId);

                    for (BudgetSummary bs : budgets) {
                        String category = bs.getCategory();
                        BigDecimal budget = bs.getBudget();
                        BigDecimal actual = bs.getActual();

                        int cmp = actual.compareTo(budget);

                        if (cmp > 0) {
                            // actual > budget → alert user (could be a GUI popup, log, etc.)
                            System.out.println("Alert: Expenses exceeded budget for category: " + category);
                            // You can also throw an exception or update a notification panel
                        } else {
                            // actual <= budget → save the remainder as savings
                            BigDecimal remainder = budget.subtract(actual);
                            if (remainder.compareTo(BigDecimal.ZERO) > 0) {
                                // Insert remainder to savings (you said you have this method)
                                db.insertTotalSavings(userId, actual);
                                System.out.println("Savings added: " + remainder + " for category: " + category);
                            }
                        }
                    }
                }

               public User getUserById(int userId) {
                   
                    String url = "jdbc:mysql://localhost:3306/finance_tracker";
                    String sql = "SELECT * FROM users WHERE userId = ?";
                    try (Connection conn = DriverManager.getConnection(url,  "root", "");
                         PreparedStatement stmt = conn.prepareStatement(sql)) {

                        stmt.setInt(1, userId);
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            return new User(
                                rs.getInt("userId"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("Image")
                            );
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }

                public boolean updateImagePath(int userId, String path) {
                    
                     String url = "jdbc:mysql://localhost:3306/finance_tracker";
                    String sql = "UPDATE users SET Image = ? WHERE userId = ?";
                    try (Connection conn = DriverManager.getConnection(url,  "root", "");
                         PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setString(1, path);
                        stmt.setInt(2, userId);
                        return stmt.executeUpdate() > 0;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        return false;
                    }
                }
            
              public boolean updateUsername(int userId, String newUsername) {
                  
                  String url = "jdbc:mysql://localhost:3306/finance_tracker";
                    String sql = "UPDATE users SET username = ? WHERE userId = ?";
                    try (Connection conn = DriverManager.getConnection(url,  "root", "");
                         PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setString(1, newUsername);
                        stmt.setInt(2, userId);
                        return stmt.executeUpdate() > 0;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        return false;
                    }
                }

            public boolean updateEmail(int userId, String newEmail) {
                
                String url = "jdbc:mysql://localhost:3306/finance_tracker";
                String sql = "UPDATE users SET email = ? WHERE userId = ?";
                try (Connection conn = DriverManager.getConnection(url,  "root", "");
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, newEmail);
                    stmt.setInt(2, userId);
                    return stmt.executeUpdate() > 0;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
              
                private final String DB_URL = "jdbc:mysql://localhost:3306/finance_tracker";
                private final String DB_USERNAME = "root";  // change if needed
                private final String DB_PASSWORD = "";      // change if needed

                // Get a connection to the database
                private Connection getConnection() throws SQLException {
                    return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                }

                // Hash a plaintext password
                public String hashPassword(String plainPassword) {
                    return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // work factor 12
                }

                // Verify plaintext password against hashed password
                public boolean checkPassword(String plainPassword, String hashedPassword) {
                    if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) {
                        throw new IllegalArgumentException("Invalid hashed password format");
                    }
                    return BCrypt.checkpw(plainPassword, hashedPassword);
                }

                // Verify current password for a userId
                public boolean verifyPassword(int userId, String plainPassword) {
                    String sql = "SELECT password FROM users WHERE userid = ?";
                    try (Connection conn = getConnection();
                         PreparedStatement ps = conn.prepareStatement(sql)) {

                        ps.setInt(1, userId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            String storedHash = rs.getString("password");
                            return checkPassword(plainPassword, storedHash);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }

                // Update password for a userId (store hashed password)
                public boolean updatePassword(int userId, String newPlainPassword) {
                    String newHashedPassword = hashPassword(newPlainPassword);
                    String sql = "UPDATE users SET password = ? WHERE userid = ?";
                    try (Connection conn = getConnection();
                         PreparedStatement ps = conn.prepareStatement(sql)) {

                        ps.setString(1, newHashedPassword);
                        ps.setInt(2, userId);
                        int rowsUpdated = ps.executeUpdate();
                        return rowsUpdated > 0;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }
}
