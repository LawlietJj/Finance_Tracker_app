/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersonalFinanceTracker;

/**
 *
 * @author HP ElitePC
 */
public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String imagePath;
    
     public User(int userId, String username, String email, String password, String imagePath) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.imagePath = imagePath;
    }
     
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getImagePath() { return imagePath; }
    
    // Setters for mutable fields
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
