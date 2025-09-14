/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersonalFinanceTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangePasswordDialog extends JDialog {
    private int userId;
    private JPasswordField currentPassField;
    private JPasswordField newPassField;
    private JPasswordField confirmPassField;
    private JButton btnChange;
    private JButton btnCancel;
    private Database db;

    public ChangePasswordDialog(Window parent, int userId) {
        super(parent, "Change Password", ModalityType.APPLICATION_MODAL);
        this.userId = userId;
        this.db = new Database();

        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Current Password
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Current Password:"), gbc);
        currentPassField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(currentPassField, gbc);

        // New Password
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("New Password:"), gbc);
        newPassField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(newPassField, gbc);

        // Confirm New Password
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Confirm New Password:"), gbc);
        confirmPassField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(confirmPassField, gbc);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnChange = new JButton("Change");
        btnCancel = new JButton("Cancel");

        btnChange.addActionListener(e -> handleChangePassword());
        btnCancel.addActionListener(e -> dispose());

        buttonsPanel.add(btnChange);
        buttonsPanel.add(btnCancel);

        // Add panels to dialog
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void handleChangePassword() {
        String currentPass = new String(currentPassField.getPassword());
        String newPass = new String(newPassField.getPassword());
        String confirmPass = new String(confirmPassField.getPassword());

        if (currentPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "New passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verify current password matches what's stored in DB
        if (!db.verifyPassword(userId, currentPass)) {
            JOptionPane.showMessageDialog(this, "Current password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update password (hashing should happen inside your Database method)
        boolean success = db.updatePassword(userId, newPass);
        if (success) {
            JOptionPane.showMessageDialog(this, "Password changed successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to change password. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

