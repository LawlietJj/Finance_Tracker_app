/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PersonalFinanceTracker;

import javax.swing.JFrame;

public class PersonalFinanceTracker {

    public static void main(String[] args) {
        // Create a dummy parent frame (can be hidden)
       SplashScreen splash = new SplashScreen();

        splash.showSplash(() -> {
            // This will be called *after* the splash screen finishes
            JFrame parentFrame = new JFrame();
            LoginBox login = new LoginBox(parentFrame, true);
            login.setSize(850, 750);
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });
    }
    
}

