/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PersonalFinanceTracker;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;   

/**
 *
 * @author HP ElitePC
 */
public class UserProfilePanel extends javax.swing.JPanel {
    private JLabel usernameValueLabel;
    private JLabel emailValueLabel;
    private JTextField emailField,usernameField;
    private JLabel imageLabel,passwordLabel,usernameLabel,emailLabel,lblPassword; 
    private JButton btnChangePassword,btnEdit,btnSave,uploadButton;   
    private Color grey,white;
    private int userId;
    private User user;
   
    
    /**
     * Creates new form UserProfile
     */
    public UserProfilePanel(int userId) {
          initComponents();
               this.userId = userId;
        this.user = new Database().getUserById(userId);

        // Panel styling
        grey = new Color(30,30,30,255);  
        white = new Color(221,221,221,255);

         
        setPreferredSize(new Dimension(400, 550));
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setBackground(grey);

        // Profile Image Label
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(245, 245, 245));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));
        loadProfileImage(user.getImagePath());

        // Info Panel with GridLayout (4 rows, 2 columns, gaps)
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 15, 15));
        infoPanel.setOpaque(false);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 16);

        // Username
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(white);
        usernameValueLabel = new JLabel(user.getUsername());
        usernameValueLabel.setFont(valueFont);
        usernameValueLabel.setForeground(white);        
        usernameField = new JTextField(user.getUsername(), 20);
        usernameField.setFont(valueFont);
        usernameField.setVisible(false);

        // Email
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(white); 
        emailValueLabel = new JLabel(user.getEmail());
        emailValueLabel.setFont(valueFont);
        emailValueLabel.setForeground(white); 
        emailField = new JTextField(user.getEmail(), 20);
        emailField.setFont(valueFont);
        emailField.setVisible(false);
         emailField.setForeground(white); 

        // Password (masked)
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
         passwordLabel.setForeground(white); 
        lblPassword = new JLabel("********");
        lblPassword.setFont(valueFont);
        lblPassword.setForeground(white); 
        btnChangePassword = new JButton("Change Password");
        btnChangePassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnChangePassword.setFocusPainted(false);
        btnChangePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         btnChangePassword.addActionListener(e -> openChangePasswordDialog());

        // Add components to info panel (labels and values)
        infoPanel.add(usernameLabel);
        infoPanel.add(usernameValueLabel);
        infoPanel.add(usernameField);

        infoPanel.add(emailLabel);
        infoPanel.add(emailValueLabel);
        infoPanel.add(emailField);

        infoPanel.add(passwordLabel);
        infoPanel.add(lblPassword);
        infoPanel.add(btnChangePassword);

        // Buttons Panel (Edit / Save)
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        buttonsPanel.setOpaque(false);

        btnEdit = new JButton("Edit");
        btnSave = new JButton("Save");
        btnSave.setVisible(false);
        btnEdit.setPreferredSize(new Dimension(200, 40));

        styleButton(btnEdit, new Color(0, 123, 255));  // blue
        styleButton(btnSave, new Color(40, 167, 69));  // green

        btnEdit.addActionListener(e -> enableEditMode());
        btnSave.addActionListener(e -> saveChanges());

        buttonsPanel.add(btnEdit);
        buttonsPanel.add(btnSave);

        // Upload Image button styling
        uploadButton = new JButton("Change Profile Image");
        uploadButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        uploadButton.setBackground(new Color(230, 230, 230));
        uploadButton.setFocusPainted(false);
        uploadButton.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        uploadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        uploadButton.setPreferredSize(new Dimension(360, 35));
        uploadButton.addActionListener(e -> chooseNewImage());

        // South panel to hold buttons vertically
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setOpaque(false);
        southPanel.add(buttonsPanel);
        southPanel.add(Box.createVerticalStrut(15));
        southPanel.add(uploadButton);

        // Add all components to main panel
        add(imageLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

    }
    
        private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(10));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    // Rounded border for buttons
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }
    
    
         private void enableEditMode() {
        // Hide labels, show text fields
        usernameValueLabel.setVisible(false);
        usernameField.setVisible(true);

        emailValueLabel.setVisible(false);
        emailField.setVisible(true);

        btnEdit.setVisible(false);
        btnSave.setVisible(true);

        revalidate();
        repaint();
    }

        private void saveChanges() {
            String newUsername = usernameField.getText().trim();
            String newEmail = emailField.getText().trim();

            Database db = new Database();

            boolean usernameUpdated = db.updateUsername(userId, newUsername);
            boolean emailUpdated = db.updateEmail(userId, newEmail);

            if (usernameUpdated && emailUpdated) {
                user.setUsername(newUsername);
                user.setEmail(newEmail);

                usernameValueLabel.setText(newUsername);
                emailValueLabel.setText(newEmail);

                usernameValueLabel.setVisible(true);
                usernameField.setVisible(false);

                emailValueLabel.setVisible(true);
                emailField.setVisible(false);

                btnSave.setVisible(false);
                btnEdit.setVisible(true);

                revalidate();
                repaint();

                JOptionPane.showMessageDialog(this, "Profile updated successfully!");
            } else {
            JOptionPane.showMessageDialog(this, "Failed to update profile. Please try again.");
        }
    }

    private void openChangePasswordDialog() {
        ChangePasswordDialog dialog = new ChangePasswordDialog(SwingUtilities.getWindowAncestor(this), userId);
        dialog.setVisible(true);
    }
        
   
    
        private void loadProfileImage(String path) {
            try {
                if (path != null && !path.isEmpty()) {
                    BufferedImage img = ImageIO.read(new File(path));
                    Image scaled = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaled));
                } else {
                    imageLabel.setIcon(new ImageIcon(new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void chooseNewImage() {
            Database db = new Database();
            
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();

            // Save to DB
            if (db.updateImagePath(user.getUserId(), imagePath)) {
                loadProfileImage(imagePath);
                JOptionPane.showMessageDialog(this, "Image updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update image.");
            }
        }
    }
        
        
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
