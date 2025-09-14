/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersonalFinanceTracker;
import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    private JProgressBar progressBar;
    private JLabel versionLabel;

    public SplashScreen() {
        // Load your image here:
        ImageIcon splashImage = new ImageIcon("C:\\Users\\HP ElitePC\\Documents\\NetBeansProjects\\PerdonalFinanceTracker\\src\\resources\\screen.png");

        // Set layout and components
        JPanel content = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel(splashImage);

        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);

        versionLabel = new JLabel("Version 1.0");
        versionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        versionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        versionLabel.setForeground(Color.DARK_GRAY);

//        content.add(versionLabel, BorderLayout.NORTH);
        content.add(imageLabel, BorderLayout.CENTER);
        content.add(progressBar, BorderLayout.SOUTH);

        setContentPane(content);
        pack();

        // Center on screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - getSize().width) / 2;
        int y = (screen.height - getSize().height) / 2;
        setLocation(x, y);
    }

    public void showSplash(Runnable afterSplash) {
        setVisible(true);

        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i += 5) {
                    Thread.sleep(100);  // Adjust speed here
                    final int progress = i;
                    SwingUtilities.invokeLater(() -> progressBar.setValue(progress));
                }
                SwingUtilities.invokeLater(() -> {
                    dispose();
                    if (afterSplash != null) {
                        afterSplash.run();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

  
}


