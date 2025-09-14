package PersonalFinanceTracker;

import java.awt.Color;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author HP ElitePC
 */
public class GoalsPanel extends javax.swing.JPanel {

    JPanel goalPanel;
    JButton updateButton,deleteButton;
    private NotificationPanel notificationPanel;
    /**
     * Creates new form GoalsPanel
     */
        public GoalsPanel() {
          initComponents();
          
          this.setBackground(Color.GRAY.brighter());
          
          // Create a NumberFormat for currency or numbers with commas
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("en", "NG"));
        nf.setMaximumFractionDigits(2); 

        // Create NumberFormatter with that format
        NumberFormatter formatter = new NumberFormatter(nf);
        formatter.setAllowsInvalid(false);  // don't allow invalid chars
        formatter.setOverwriteMode(false);

        
        amountField.setFormatterFactory(new DefaultFormatterFactory(formatter));
          
          
          Database db = new Database();

          // Main panel to hold all goal cards
           goalPanel = new JPanel();
          goalPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Horizontal with spacing
          goalPanel.setBackground(Color.WHITE); 

          final int[] selectedGoalId = {-1};

          // Get user's goals
          List<Goals> userGoals = db.getGoalsByUserId(Session.userId);

          // Build cards
            for (Goals goal : userGoals) {
                JPanel card = new JPanel();
                card.setLayout(new BorderLayout());
                card.setPreferredSize(new Dimension(400, 220));
                card.setBackground(new Color(102, 255, 255));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));

                // ========== CENTER PANEL: Labels (Vertical)
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.setOpaque(false);

                JLabel nameLabel = new JLabel("<html><span style='font-weight:bold; font-size:12px; color:#000000;'>Name:  </span> " + goal.getGoalName() + "</html>");
                JLabel typeLabel = new JLabel("<html><span style='font-weight:bold; font-size:12px; color:#000000;'>Type:  </span> " + goal.getGoalType() + "</html>");
                JLabel amountLabel = new JLabel("<html><span style='font-weight:bold; font-size:12px; color:#000000;'>Amount:  </span> ₦" + goal.getAmount() + "</html>");
                JLabel deadlineLabel = new JLabel("<html><span style='font-weight:bold; font-size:12px; color:#00000;'>Deadline:  </span>" + goal.getDeadline().toString() + "</html>");



                // Style labels
                nameLabel.setFont(new Font("Segoe UI SemiboldI", Font.ITALIC, 15));
                typeLabel.setFont(new Font("Segoe UI Semibold ", Font.ITALIC, 15));
                amountLabel.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 15));
                deadlineLabel.setFont(new Font("Segoe UI Semibold ", Font.ITALIC, 15));

                
                infoPanel.add(nameLabel);
                infoPanel.add(Box.createVerticalStrut(12));
                infoPanel.add(typeLabel);
                infoPanel.add(Box.createVerticalStrut(12));
                infoPanel.add(amountLabel);
                infoPanel.add(Box.createVerticalStrut(12));
                infoPanel.add(deadlineLabel);

                // ========== BOTTOM PANEL: Buttons (Horizontal)
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                buttonPanel.setOpaque(false);

                JButton updateButton = new JButton("Update");
                JButton deleteButton = new JButton("Delete");

                updateButton.setFocusPainted(false);
                deleteButton.setFocusPainted(false);
                updateButton.setBackground(Color.WHITE);
                deleteButton.setBackground(Color.WHITE);

                buttonPanel.add(updateButton);
                buttonPanel.add(deleteButton);

                // Action listeners
                updateButton.addActionListener(e -> {
            int id = selectedGoalId[0]; // selectedGoalId should be set when card is clicked
            if (id == -1) {
                JOptionPane.showMessageDialog(null, "Please select a goal card first.");
                return;
            }

            try {
                String type = (String) categoryBox.getSelectedItem();
                String title = goalNameField.getText().trim();
                Number value = (Number) amountField.getValue();
            if (value == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                return;
            }
            BigDecimal amount = BigDecimal.valueOf(value.doubleValue());

                java.util.Date utilDate = jDateChooser1.getDate();
                if (utilDate == null) {
                    JOptionPane.showMessageDialog(null, "Please select a deadline date.");
                    return;
                }
                // Convert utilDate to sqlDate here
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                Goals updatedGoal = new Goals(id, Session.userId, type, title, amount, utilDate);

                if (db.updateGoals(
                    updatedGoal.getId(),
                    updatedGoal.getUserId(),
                    updatedGoal.getGoalType(),
                    updatedGoal.getGoalName(),
                    updatedGoal.getAmount(),
                    sqlDate // pass the sqlDate here
                )) {
                    JOptionPane.showMessageDialog(null, "Goal updated successfully.");
                    refreshGoals(); // Reload cards or clear fields
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update goal.");
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error updating goal: " + ex.getMessage());
                ex.printStackTrace();
            }
});



            card.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        selectedGoalId[0] = goal.getId();  // store selected goal id
                        goalNameField.setText(goal.getGoalName());
                        amountField.setText(goal.getAmount().toPlainString());
                        categoryBox.setSelectedItem(goal.getGoalType());
                        jDateChooser1.setDate(goal.getDeadline());  
                    }
                });
            

            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(card,
                        "Delete goal: " + goal.getGoalName() + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (db.deleteGoals(goal.getId())) {
                        goalPanel.remove(card);
                        goalPanel.revalidate();
                        goalPanel.repaint();
                        String notifMsg = "✅ Goals Record was Deleted successfully";

                            // Insert notification into DB
                            db.insertNotification(Session.userId, notifMsg);

                            // Show message in UI NotificationPanel if it exists
                            if (notificationPanel != null) {
                                 notificationPanel.addNotification(notifMsg, new Color(0, 128, 0));
                            } else {
                                JOptionPane.showMessageDialog(this, notifMsg);
                            }
                    }
                }
            });

                // Add panels to card
                card.add(infoPanel, BorderLayout.CENTER);
                card.add(buttonPanel, BorderLayout.SOUTH);

                // Add card to your container (like goalPanel)
                goalPanel.add(card);
}



          // Add scrollPane AFTER cards are built
          JScrollPane scrollPane = new JScrollPane(goalPanel);
          scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
          scrollPane.setPreferredSize(new Dimension(400, 500));
          scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scrolling

          // Add to the container panel (which must have been declared in initComponents)
          jPanel3.setLayout(new BorderLayout());
          jPanel3.add(scrollPane, BorderLayout.CENTER);
          
          
          
      }

        private void refreshGoals() {
            goalPanel.removeAll();  // Clear existing goal cards

            
            Database db = new Database();
            List<Goals> userGoals = db.getGoalsByUserId(Session.userId);
            final int[] selectedGoalId = {-1};
            
            for (Goals goal : userGoals) {
                JPanel card = new JPanel();
                card.setLayout(new BorderLayout());
                card.setPreferredSize(new Dimension(400, 220));
                card.setBackground(new Color(102, 255, 255));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));

                // Info labels
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.setOpaque(false);

                JLabel nameLabel = new JLabel("<html><span style='font-weight:bold; font-size:12px; color:#000;'>Name:</span> " + goal.getGoalName() + "</html>");
                JLabel typeLabel = new JLabel("<html><span style='font-weight:bold; font-size:12px; color:#000;'>Type:</span> " + goal.getGoalType() + "</html>");
                JLabel amountLabel = new JLabel("<html><span style='font-weight:bold; font-size:12px; color:#000;'>Amount:</span> ₦" + goal.getAmount() + "</html>");
                JLabel deadlineLabel = new JLabel("<html><span style='font-weight:bold; font-size:12px; color:#000;'>Deadline:</span> " + goal.getDeadline().toString() + "</html>");

                nameLabel.setFont(new Font("Segoe UI SemiboldI", Font.ITALIC, 15));
                typeLabel.setFont(new Font("Segoe UI SemiboldI", Font.ITALIC, 15));
                amountLabel.setFont(new Font("Segoe UI SemiboldI", Font.ITALIC, 15));
                deadlineLabel.setFont(new Font("Segoe UI SemiboldI", Font.ITALIC, 15));

                infoPanel.add(nameLabel);
                infoPanel.add(Box.createVerticalStrut(12));
                infoPanel.add(typeLabel);
                infoPanel.add(Box.createVerticalStrut(12));
                infoPanel.add(amountLabel);
                infoPanel.add(Box.createVerticalStrut(12));
                infoPanel.add(deadlineLabel);

                // Buttons
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                buttonPanel.setOpaque(false);

                 updateButton = new JButton("Update");
                 deleteButton = new JButton("Delete");

                updateButton.setFocusPainted(false);
                deleteButton.setFocusPainted(false);
                updateButton.setBackground(Color.WHITE);
                deleteButton.setBackground(Color.WHITE);

                buttonPanel.add(updateButton);
                buttonPanel.add(deleteButton);
                 updateButton.addActionListener(e -> {
            int id = selectedGoalId[0]; // selectedGoalId should be set when card is clicked
            if (id == -1) {
                JOptionPane.showMessageDialog(null, "Please select a goal card first.");
                return;
            }

            try {
                String type = (String) categoryBox.getSelectedItem();
                String title = goalNameField.getText().trim();
                Number value = (Number) amountField.getValue();
            if (value == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                return;
            }
            BigDecimal amount = BigDecimal.valueOf(value.doubleValue());

                java.util.Date utilDate = jDateChooser1.getDate();
                if (utilDate == null) {
                    JOptionPane.showMessageDialog(null, "Please select a deadline date.");
                    return;
                }
                // Convert utilDate to sqlDate here
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                Goals updatedGoal = new Goals(id, Session.userId, type, title, amount, utilDate);

                if (db.updateGoals(
                    updatedGoal.getId(),
                    updatedGoal.getUserId(),
                    updatedGoal.getGoalType(),
                    updatedGoal.getGoalName(),
                    updatedGoal.getAmount(),
                    sqlDate // pass the sqlDate here
                )) {
                    JOptionPane.showMessageDialog(null, "Goal updated successfully.");
                    refreshGoals(); // Reload cards or clear fields
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update goal.");
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error updating goal: " + ex.getMessage());
                ex.printStackTrace();
            }
});



            card.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        selectedGoalId[0] = goal.getId();  // store selected goal id
                        goalNameField.setText(goal.getGoalName());
                        amountField.setText(goal.getAmount().toPlainString());
                        categoryBox.setSelectedItem(goal.getGoalType());
                        jDateChooser1.setDate(goal.getDeadline());  
                    }
                });
            

            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(card,
                        "Delete goal: " + goal.getGoalName() + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (db.deleteGoals(goal.getId())) {
                        goalPanel.remove(card);
                        goalPanel.revalidate();
                        goalPanel.repaint();
                    }
                }
            });

                // Add to card
                card.add(infoPanel, BorderLayout.CENTER);
                card.add(buttonPanel, BorderLayout.SOUTH);

                // Add card to goalPanel
                goalPanel.add(card);

                // Optionally add action listeners (use goal.getId() etc.)
            }

            goalPanel.revalidate();
            goalPanel.repaint();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        goalNameField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        categoryBox = new javax.swing.JComboBox<>();
        amountField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        createGoalBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 15)); // NOI18N
        jLabel1.setText("Goal Name/ Description :");

        jLabel5.setFont(new java.awt.Font("Azonix", 0, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 204));
        jLabel5.setText("SET FINANCIAL GOALS");

        goalNameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        goalNameField.setAlignmentX(1.0F);
        goalNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goalNameFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(goalNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goalNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        categoryBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INCOME", "SAVINGS", "BUDGETS", "EXPENSES" }));

        amountField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 15)); // NOI18N
        jLabel2.setText("Goals Type");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 15)); // NOI18N
        jLabel3.setText("Amount");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 15)); // NOI18N
        jLabel4.setText("Set Deadline");

        jDateChooser1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        createGoalBtn.setBackground(new java.awt.Color(0, 204, 204));
        createGoalBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        createGoalBtn.setText("Create Goals");
        createGoalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGoalBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(amountField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(createGoalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(createGoalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void goalNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goalNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_goalNameFieldActionPerformed

    private void createGoalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGoalBtnActionPerformed
        // TODO add your handling code here:

        Database db = new Database();

        try {

            String Category = categoryBox.getSelectedItem().toString();

            String Name = goalNameField.getText();

            Number value = (Number) amountField.getValue();
            if (value == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                return;
            }
            BigDecimal Amount = BigDecimal.valueOf(value.doubleValue());

            Date selectedDate = jDateChooser1.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Please select a date.");
                return;
            }
            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());

            if (Category.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            boolean success = db.insertGoals(Category, Name, Amount, sqlDate);

            if (success) {

                JOptionPane.showMessageDialog(this, "Goals Created");
                String notifMsg = "✅ Financial Goal was Created successfully ";

                            // Insert notification into DB
                            db.insertNotification(Session.userId, notifMsg);

                            // Show message in UI NotificationPanel if it exists
                            if (notificationPanel != null) {
                                notificationPanel.addNotification(notifMsg, new Color(0, 128, 0));
                            } else {
                                JOptionPane.showMessageDialog(this, notifMsg);
                            }

                goalNameField.setText("");
                amountField.setValue(null);  // Clear formatted text field
                jDateChooser1.setDate(null);
                categoryBox.requestFocus();
                refreshGoals();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add the record.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_createGoalBtnActionPerformed

    
      public void setNotificationPanel(NotificationPanel notificationPanel) {
                this.notificationPanel = notificationPanel;
            }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField amountField;
    private javax.swing.JComboBox<String> categoryBox;
    private javax.swing.JButton createGoalBtn;
    private javax.swing.JTextField goalNameField;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
