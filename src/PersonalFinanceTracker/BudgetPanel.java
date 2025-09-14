/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PersonalFinanceTracker;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
/**
 *
 * @author HP ElitePC
 */
public class BudgetPanel extends javax.swing.JPanel {

    JLabel incomeLabel, budgetLabel, budgetIcon, incomeIcon, income, budget;
    BigDecimal rawIncome, rawBudget;
    Color grey;
      private NotificationPanel notificationPanel;
    /**
     * Creates new form BudgetPanel
     */
    public BudgetPanel() {
        initComponents();
        
        Database db = new Database();
        
                   grey = new Color(30,30,30,255);
                Font fonts = new Font("Segeo UI", Font.BOLD, 18);
                Font font1 = new Font("Calibri", Font.BOLD, 28);
        
                incomeLabel =new JLabel(); 
                
                budgetLabel =new JLabel();

                incomeIcon = new JLabel();
                                // Load the image path
                ImageIcon originalIcon = new ImageIcon("C:\\Users\\HP ElitePC\\Documents\\NetBeansProjects\\PerdonalFinanceTracker\\src\\resources\\wallet_888203.png");

                // Scaling the image 
                Image scaledImage = originalIcon.getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                
//                BUDGET
                
                budgetIcon = new JLabel();
                                // Load the imagewoth path
                ImageIcon originalIcon1 = new ImageIcon("C:\\Users\\HP ElitePC\\Documents\\NetBeansProjects\\PerdonalFinanceTracker\\src\\resources\\budget_12340872.png");

                // Scaling the image 
                Image scaledImage1 = originalIcon1.getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
                
                
                
                
            totalIncomePanel.setLayout(new BoxLayout(totalIncomePanel, BoxLayout.Y_AXIS));
            totalIncomePanel.setPreferredSize(new Dimension(250, 125));
            totalIncomePanel.setBackground(grey);
                
                
              

            income = new JLabel();
            income.setForeground(Color.GREEN);
            income.setFont(font1);
            income.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            incomeLabel = new JLabel("Total Income");
            incomeLabel.setForeground(Color.WHITE);
            incomeLabel.setFont(fonts);
            incomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            incomeIcon.setIcon(scaledIcon);
            incomeIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            totalIncomePanel.add(Box.createVerticalGlue());
            totalIncomePanel.add(incomeLabel);
            totalIncomePanel.add(Box.createVerticalStrut(15)); // spacing
            totalIncomePanel.add(incomeIcon); 
            totalIncomePanel.add(Box.createVerticalStrut(15)); // spacing
            totalIncomePanel.add(income);
            totalIncomePanel.add(Box.createVerticalGlue());
            
            
             
            budgetPanel.setLayout(new BoxLayout(budgetPanel, BoxLayout.Y_AXIS));
            budgetPanel.setPreferredSize(new Dimension(250, 125));
            budgetPanel.setBackground(grey);  // or Color.gray if you prefer

           

            
            budget = new JLabel();
            budget.setFont(font1);
            budget.setForeground(Color.ORANGE);
            budget.setAlignmentX(Component.CENTER_ALIGNMENT);

            budgetLabel = new JLabel("Total Budget");
            budgetLabel.setFont(fonts);
            budgetLabel.setForeground(Color.WHITE);
            budgetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            budgetIcon.setIcon(scaledIcon1);
            budgetIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

            budgetPanel.add(Box.createVerticalGlue());
            budgetPanel.add(budgetLabel);
            budgetPanel.add(Box.createVerticalStrut(15));
            budgetPanel.add(budgetIcon);
            budgetPanel.add(Box.createVerticalStrut(15));
            budgetPanel.add(budget);
            budgetPanel.add(Box.createVerticalGlue());
            
            
           
        
        DefaultTableModel expenseTableModel = db.budgetTable();
        jTable1.setModel(expenseTableModel);
        jTable1.setRowHeight(25);
        jTable1.setBackground(Color.GRAY.brighter());
        
        
        this.setBackground(Color.GRAY.brighter());
            
            NumberFormat nf = NumberFormat.getNumberInstance(new Locale("en", "NG"));
            nf.setMaximumFractionDigits(2); 

            // Create NumberFormatter with that format
            NumberFormatter formatter = new NumberFormatter(nf);
            formatter.setAllowsInvalid(false); 
            formatter.setOverwriteMode(false);


            amountfield.setFormatterFactory(new DefaultFormatterFactory(formatter));
             updateTotalLabel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        totalIncomePanel = new javax.swing.JPanel();
        budgetPanel = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        categoryBox = new javax.swing.JComboBox<>();
        Category = new javax.swing.JLabel();
        amountfield = new javax.swing.JFormattedTextField();
        Amount = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        totalIncomePanel.setBackground(new java.awt.Color(255, 204, 51));

        javax.swing.GroupLayout totalIncomePanelLayout = new javax.swing.GroupLayout(totalIncomePanel);
        totalIncomePanel.setLayout(totalIncomePanelLayout);
        totalIncomePanelLayout.setHorizontalGroup(
            totalIncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );
        totalIncomePanelLayout.setVerticalGroup(
            totalIncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        budgetPanel.setBackground(new java.awt.Color(255, 204, 51));

        javax.swing.GroupLayout budgetPanelLayout = new javax.swing.GroupLayout(budgetPanel);
        budgetPanel.setLayout(budgetPanelLayout);
        budgetPanelLayout.setHorizontalGroup(
            budgetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );
        budgetPanelLayout.setVerticalGroup(
            budgetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );

        addBtn.setBackground(new java.awt.Color(0, 204, 204));
        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addBtn.setText("Create Budgets");
        addBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 2, true));
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        categoryBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        categoryBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rent", "Groceries", "Utilities (Electricity, Water, Gas)", "Transportation", "Car maintenance", "Health", "Mobile/Internet", "Subscriptions (Netflix, Spotify)", "Shopping", "Education", "Savings", "Loan payments" }));

        Category.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        Category.setText("CATEGORY");

        amountfield.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Amount.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        Amount.setText("Monthly Limit ");

        deleteBtn.setBackground(new java.awt.Color(0, 204, 204));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(51, 51, 51));
        deleteBtn.setText("DELETE RECORDS");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(0, 204, 204));
        updateBtn.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(51, 51, 51));
        updateBtn.setText("UPDATE TABLE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(totalIncomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(budgetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(amountfield, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(budgetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalIncomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Category)
                    .addComponent(Amount)
                    .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountfield, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(520, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 282, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
         Database db = new Database();

        try {
                int userid = Session.userId;
                String Category = categoryBox.getSelectedItem().toString();

                Number value = (Number) amountfield.getValue();
                if (value == null) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                    return;
                }
                BigDecimal Amount = BigDecimal.valueOf(value.doubleValue());

                
                if (Category.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                    return;
                }

                boolean success = db.insertBudget(userid, Category, Amount);

                if (success) {               
                    JOptionPane.showMessageDialog(this, "Budget Added");
                    String notifMsg = "✅ Budget Created successfully for category: " + Category + 
                              ", Amount: " + Amount ;

                            // Insert notification into DB
                            db.insertNotification(userid, notifMsg);

                            // Show message in UI NotificationPanel if it exists
                            if (notificationPanel != null) {
                                 notificationPanel.addNotification(notifMsg, new Color(0, 128, 0));
                            } else {
                                JOptionPane.showMessageDialog(this, notifMsg);
                            }
                     updateTotalLabel();
                     table_update();

                    amountfield.setValue(null);  // Clear formatted text field
                    categoryBox.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add the record.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        Database db = new Database();

        DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a record to delete.");
            return;
        }

        int id = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());

        int dialogResult = JOptionPane.showConfirmDialog(null,
            "Do you want to delete this record?", "Warning",
            JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            boolean success = db.deleteExpenseRecord(id);
            if (success) {
                JOptionPane.showMessageDialog(this, "Budget deleted");
                String notifMsg = "✅ Budget Record was Deleted successfully";

                            // Insert notification into DB
                            db.insertNotification(Session.userId, notifMsg);

                            // Show message in UI NotificationPanel if it exists
                            if (notificationPanel != null) {
                                 notificationPanel.addNotification(notifMsg, new Color(0, 128, 0));
                            } else {
                                JOptionPane.showMessageDialog(this, notifMsg);
                            }
                table_update();
                 updateTotalLabel();


                amountfield.setText("");

                categoryBox.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete the record.");
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a record to update");
            return;
        }

        try {
            int id = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());
            String category = categoryBox.getSelectedItem().toString();
            Number value = (Number) amountfield.getValue();
            if (value == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                return;
            }
            BigDecimal amount = BigDecimal.valueOf(value.doubleValue());

          

            if (category.isEmpty() || amount == null) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            

            Database db = new Database();
            boolean success = db.updateBudget(id, category, amount);

            if (success) {
                JOptionPane.showMessageDialog(this, "Record updated successfully!");
                table_update();  // refresh table
                updateTotalLabel();

                amountfield.setValue(null);
                categoryBox.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update record.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();

        if (selectedIndex != -1) {
            String category = Df.getValueAt(selectedIndex, 1).toString();
            String amount = Df.getValueAt(selectedIndex, 2).toString();

            categoryBox.setSelectedItem(category);   // For JComboBox

            try {
                BigDecimal amountValue = new BigDecimal(amount.replaceAll(",", ""));
                amountfield.setValue(amountValue);
            } catch (NumberFormatException e) {
                amountfield.setText(amount);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

               private void table_update() {
                    Database db = new Database();
                DefaultTableModel model = db.budgetTable();
                jTable1.setModel(model);
                jTable1.setRowHeight(25);
            }
    
         private void updateTotalLabel() {
             int userId = Session.userId;
                Database db = new Database();

                // Get income from DB
                rawIncome = db.getIncomeByUserId(userId);
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));
                income.setText(currencyFormat.format(rawIncome));

                // Get sum of monthly budgets and update assets budget field
                BigDecimal totalBudget = db.getTotalMonthlyBudget(userId);
                boolean success = db.insertTotalBudget(userId, totalBudget);

                // Now get budget from assets table (to be sure)
                if (success) {
                    rawBudget = db.getBudgetByUserId(userId);
                } else {
                    rawBudget = BigDecimal.ZERO;
                }

                budget.setText(currencyFormat.format(rawBudget));
    }

           public void setNotificationPanel(NotificationPanel notificationPanel) {
                this.notificationPanel = notificationPanel;
            }
        
                    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Amount;
    private javax.swing.JLabel Category;
    private javax.swing.JButton addBtn;
    private javax.swing.JFormattedTextField amountfield;
    private javax.swing.JPanel budgetPanel;
    private javax.swing.JComboBox<String> categoryBox;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel totalIncomePanel;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
