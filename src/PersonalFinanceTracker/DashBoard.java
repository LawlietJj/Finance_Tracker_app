/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PersonalFinanceTracker;

import java.awt.*;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.List;
import javax.swing.*;
import javax.swing.text.NumberFormatter;



/**
 *
 * @author HP ElitePC
 */
public class DashBoard extends javax.swing.JFrame {

     JPanel rPanel, lPanel, totalincomePanel,totalExpensePanel,savingsPanel,budgetPanel,contentPanel, summaryPanel,  bvsaPanel;
    JMenuBar loginBar, LogoutBar;
    JLabel incomeLabel, expenseLabel, savingsLabel,budgetLabel,income,expense,savings, budget,
    incomeIcon,expenseIcon,savingsIcon,budgetIcon;
    BigDecimal rawExpense,rawIncome,rawSavings,rawBudget;
    JDesktopPane desktopPane;
    Color grey, white;
     private NotificationPanel notificationPanel;
      private SummariesPanel chartPanel;
    private String username;  
    /**
     * Creates new form NewJFrame
     */
    /**
 * Dashboard panel that greets the user by username.
 * @param username the logged-in user's name
 */
//        public DashBoard(String username) {
//            this.username = username;
////            initComponents();
//             userLabel.setText(this.username);
//           ;
//    }
     
     
    public DashBoard(String username) {
          this.username = username;
        initComponents();
        
        ImageIcon icon = new ImageIcon("C:\\Users\\HP ElitePC\\Documents\\NetBeansProjects\\PerdonalFinanceTracker\\src\\resources\\pftlogo.jpg");
         setIconImage(icon.getImage());
        
        desktopPane = new JDesktopPane();
       


        Database db = new Database();
               userLabel.setText(this.username);

        CardLayout cardLayout = new CardLayout();
            mainSidePanel.setLayout(cardLayout);
            
               	grey = new Color(30,30,30,255);
                white = new Color(221,221,221,255);
                
                Font fonts = new Font("Segeo UI", Font.BOLD, 18);
                Font font1 = new Font("Calibri", Font.BOLD, 28);
                
                income =new JLabel(); 
                expense =new JLabel();
                savings =new JLabel();
                budget =new JLabel();

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
                
                
//                 EXPENSE

                expenseIcon = new JLabel();
                                // Load the imagewoth path
                ImageIcon originalIcon2 = new ImageIcon("C:\\Users\\HP ElitePC\\Documents\\NetBeansProjects\\PerdonalFinanceTracker\\src\\resources\\expense_3707999.png");

                // Scaling the image 
                Image scaledImage2 = originalIcon2.getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
                
//                SAVINGS
                
                savingsIcon = new JLabel();
                                // Load the imagewoth path
                ImageIcon originalIcon3 = new ImageIcon("C:\\Users\\HP ElitePC\\Documents\\NetBeansProjects\\PerdonalFinanceTracker\\src\\resources\\savings_389461.png");

                // Scaling the image 
                Image scaledImage3 = originalIcon3.getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
                

//                incomeIcon.setIcon(icon);
                
           leftPanel.setBackground(grey);     
        dashaBtn.setForeground(white);  
        expenseBtn.setForeground(white);
        budgetBtn.setForeground(white);
        transactionBtn.setForeground(white);
        

             contentPanel = new JPanel(new BorderLayout(15,25));
       contentPanel.setBackground(Color.ORANGE);
       contentPanel.setPreferredSize(new Dimension(400, 400));
       
      
      
       lPanel = new JPanel();
       lPanel.setLayout(new BoxLayout(lPanel, BoxLayout.Y_AXIS));
       lPanel.setPreferredSize(new Dimension(300,300));
       lPanel.setBackground(grey);
       
       rPanel = new JPanel(new BorderLayout());
       rPanel.setBackground(Color.BLUE);
       rPanel.setPreferredSize(new Dimension(905,910));

              //income Panel

       totalincomePanel = new JPanel();
            totalincomePanel.setLayout(new BoxLayout(totalincomePanel, BoxLayout.Y_AXIS));
            totalincomePanel.setPreferredSize(new Dimension(280, 160));
            totalincomePanel.setBackground(Color.gray.darker());

              


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

           
            totalincomePanel.add(Box.createVerticalGlue());
            totalincomePanel.add(incomeLabel);
            totalincomePanel.add(Box.createVerticalStrut(15)); // spacing
            totalincomePanel.add(incomeIcon); 
            totalincomePanel.add(Box.createVerticalStrut(15)); // spacing
            totalincomePanel.add(income);
            totalincomePanel.add(Box.createVerticalGlue());

        
        
        
       //Expense Panel
            totalExpensePanel = new JPanel();
            totalExpensePanel.setLayout(new BoxLayout(totalExpensePanel, BoxLayout.Y_AXIS));
            totalExpensePanel.setPreferredSize(new Dimension(280, 160));
            totalExpensePanel.setBackground(Color.gray.darker());

          

            expense = new JLabel();
            expense.setForeground(Color.YELLOW);
            expense.setFont(font1);
            expense.setAlignmentX(Component.CENTER_ALIGNMENT);

          
            expenseLabel = new JLabel("Total Expense");
            expenseLabel.setForeground(Color.WHITE);
            expenseLabel.setFont(fonts);
            expenseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            expenseIcon.setIcon(scaledIcon2);
            expenseIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
           
            totalExpensePanel.add(Box.createVerticalGlue());
            totalExpensePanel.add(expenseLabel);
            totalExpensePanel.add(Box.createVerticalStrut(18)); // spacing between labels
            totalExpensePanel.add(expenseIcon);
            totalExpensePanel.add(Box.createVerticalStrut(18)); // spacing between labels
            totalExpensePanel.add(expense);
            totalExpensePanel.add(Box.createVerticalGlue());

       
              //Savings Panel

                    savingsPanel = new JPanel();
                    savingsPanel.setLayout(new BoxLayout(savingsPanel, BoxLayout.Y_AXIS));
                    savingsPanel.setPreferredSize(new Dimension(280, 160));
                    savingsPanel.setBackground(Color.GRAY.darker()); // optional darker tone

                    
                    savings = new JLabel();
                    savings.setForeground(Color.GREEN);
                    savings.setFont(font1);
                    savings.setAlignmentX(Component.CENTER_ALIGNMENT);

                    // Create and style the savings title label
                    savingsLabel = new JLabel("Total Savings");
                    savingsLabel.setForeground(Color.WHITE);
                    savingsLabel.setFont(fonts);
                    savingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    
                    savingsIcon.setIcon(scaledIcon3);
                    savingsIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

                    // Add components with spacing
                    savingsPanel.add(Box.createVerticalGlue());
                    savingsPanel.add(savingsLabel);
                    savingsPanel.add(Box.createVerticalStrut(15)); // spacing
                    savingsPanel.add(savingsIcon);
                    savingsPanel.add(Box.createVerticalStrut(15)); // spacing
                    savingsPanel.add(savings);
                    savingsPanel.add(Box.createVerticalGlue());

       
       //budgets panels
            budgetPanel = new JPanel();
            budgetPanel.setLayout(new BoxLayout(budgetPanel, BoxLayout.Y_AXIS));
            budgetPanel.setPreferredSize(new Dimension(280, 160));
            budgetPanel.setBackground(Color.GRAY.darker());  // or Color.gray if you prefer

             
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

       
       
       
      
       lPanel.add(totalincomePanel);  
       lPanel.add(Box.createVerticalStrut(30));
       lPanel.add(totalExpensePanel);
       lPanel.add(Box.createVerticalStrut(30));
       lPanel.add(savingsPanel); 
       lPanel.add(Box.createVerticalStrut(30));
       lPanel.add(budgetPanel);
       
       lPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); 
       
        summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setPreferredSize(new Dimension(500, 400));
        summaryPanel.setBackground(new Color(245, 245, 245));

        // Get data and add chart
       
       chartPanel = new SummariesPanel(
        Session.userId,
        "Income vs Expense",
        "monthly", // default view
        (userId, range) -> db.getTransactionsSummary(userId, range) // dynamic data fetch
    );
      
        summaryPanel.add(chartPanel, BorderLayout.CENTER);
        
         bvsaPanel = new JPanel(new BorderLayout(15,25));
       bvsaPanel.setBackground(new Color(245, 245, 245));
       bvsaPanel.setPreferredSize(new Dimension(910, 400));
      
      BudgetvsActualPanel budgetChartPanel = new BudgetvsActualPanel(Session.userId, "Budget vs Actuals", db);

        bvsaPanel.add(budgetChartPanel, BorderLayout.CENTER);

        notificationPanel = new NotificationPanel();
        rPanel.add(summaryPanel, BorderLayout.NORTH);  
        rPanel.add(bvsaPanel, BorderLayout.CENTER);
        rPanel.add(notificationPanel, BorderLayout.SOUTH);
        
        chartPanel.updateChart();
        
        List<String> unreadNotifications = db.getUnreadNotifications(Session.userId);
        for (String msg : unreadNotifications) {
           notificationPanel.addNotification(msg, new Color(0, 128, 0));
        }

        // Mark notifications as read (so they don't show up again)
        db.markNotificationsAsRead(Session.userId);
        
        
      this.add(desktopPane);
       
       contentPanel.add(rPanel, BorderLayout.WEST);   
       contentPanel.add(lPanel, BorderLayout.EAST);

       

       // ✅ Add it to mainSidePanel in CENTER
       mainSidePanel.add(contentPanel, "dashboard");
//        cardLayout.show(mainSidePanel, "hello");
       mainSidePanel.revalidate();
       mainSidePanel.repaint();

      
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

        leftPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dashaBtn = new javax.swing.JButton();
        expenseBtn = new javax.swing.JButton();
        budgetBtn = new javax.swing.JButton();
        transactionBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        incomeBtn = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        goalsBtn = new javax.swing.JButton();
        mainSidePanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        userMenu = new javax.swing.JMenu();
        logoutMenu = new javax.swing.JMenu();
        incomemenu = new javax.swing.JMenu();
        expenseMenu = new javax.swing.JMenu();
        budgetMenu = new javax.swing.JMenu();
        savingsMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));

        jLabel1.setFont(new java.awt.Font("BankGothic Md BT", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FINANCE TRACKER");

        dashaBtn.setBackground(new java.awt.Color(51, 51, 51));
        dashaBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dashaBtn.setText("DashBoard");
        dashaBtn.setBorder(null);
        dashaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashaBtnActionPerformed(evt);
            }
        });

        expenseBtn.setBackground(new java.awt.Color(51, 51, 51));
        expenseBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        expenseBtn.setText("Add Expenses");
        expenseBtn.setBorder(null);
        expenseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expenseBtnActionPerformed(evt);
            }
        });

        budgetBtn.setBackground(new java.awt.Color(51, 51, 51));
        budgetBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        budgetBtn.setText("Budget Manager");
        budgetBtn.setBorder(null);
        budgetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                budgetBtnActionPerformed(evt);
            }
        });

        transactionBtn.setBackground(new java.awt.Color(51, 51, 51));
        transactionBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        transactionBtn.setText("Transactions");
        transactionBtn.setBorder(null);
        transactionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Azonix", 1, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PFT");

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel3.setText("Welcome");

        incomeBtn.setBackground(new java.awt.Color(51, 51, 51));
        incomeBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        incomeBtn.setForeground(new java.awt.Color(255, 255, 255));
        incomeBtn.setText("Add Income");
        incomeBtn.setBorder(null);
        incomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incomeBtnActionPerformed(evt);
            }
        });

        userLabel.setFont(new java.awt.Font("BankGothic Md BT", 1, 24)); // NOI18N
        userLabel.setForeground(new java.awt.Color(0, 204, 204));
        userLabel.setText("jLabel4");

        goalsBtn.setBackground(new java.awt.Color(51, 51, 51));
        goalsBtn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        goalsBtn.setForeground(new java.awt.Color(255, 255, 255));
        goalsBtn.setText("Create Goals");
        goalsBtn.setBorder(null);
        goalsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goalsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(transactionBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(budgetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dashaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(expenseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(incomeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(goalsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addComponent(dashaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(incomeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(expenseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(budgetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(transactionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(goalsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        mainSidePanel.setLayout(new BorderLayout());
        mainSidePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        javax.swing.GroupLayout mainSidePanelLayout = new javax.swing.GroupLayout(mainSidePanel);
        mainSidePanel.setLayout(mainSidePanelLayout);
        mainSidePanelLayout.setHorizontalGroup(
            mainSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1224, Short.MAX_VALUE)
        );
        mainSidePanelLayout.setVerticalGroup(
            mainSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );

        jMenuBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar1MouseClicked(evt);
            }
        });

        userMenu.setText(" USER PROFILE");
        userMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(userMenu);

        logoutMenu.setText("LOGOUT");
        logoutMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(logoutMenu);

        incomemenu.setText("Set Total Income");
        incomemenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                incomemenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(incomemenu);

        expenseMenu.setText("Set Total Expense");
        expenseMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                expenseMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(expenseMenu);

        budgetMenu.setText("Set Total Budget");
        budgetMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                budgetMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(budgetMenu);

        savingsMenu.setText("Set Total Savings");
        savingsMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savingsMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(savingsMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transactionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionBtnActionPerformed
        // TODO add your handling code here:
        TransactionsPanel transactionPanel = new TransactionsPanel();
        
        mainSidePanel.removeAll();
        mainSidePanel.add(transactionPanel, "transaction");
        mainSidePanel.revalidate();
        mainSidePanel.repaint();
    }//GEN-LAST:event_transactionBtnActionPerformed

    private void dashaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashaBtnActionPerformed
        // TODO add your handling code here:
      
        
        mainSidePanel.removeAll();
        mainSidePanel.add(contentPanel, "dashboard");
        mainSidePanel.revalidate();
        mainSidePanel.repaint();
        
    }//GEN-LAST:event_dashaBtnActionPerformed

    private void expenseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expenseBtnActionPerformed
        // TODO add your handling code here:
        ExpensePanel expensePanel = new ExpensePanel();
        expensePanel.setNotificationPanel(notificationPanel);
        expensePanel.setSummariesPanel(chartPanel);
        
        mainSidePanel.removeAll();
        mainSidePanel.add(expensePanel, "expense");
        mainSidePanel.revalidate();
        mainSidePanel.repaint();
    }//GEN-LAST:event_expenseBtnActionPerformed

    private void incomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incomeBtnActionPerformed
        // TODO add your handling code here:
        IncomePanel incomePanel = new IncomePanel();
        incomePanel.setNotificationPanel(notificationPanel);
        incomePanel.setSummariesPanel(chartPanel);
        
        mainSidePanel.removeAll();
        mainSidePanel.add(incomePanel, "income");
        mainSidePanel.revalidate();
        mainSidePanel.repaint();
    }//GEN-LAST:event_incomeBtnActionPerformed

    private void budgetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_budgetBtnActionPerformed
        // TODO add your handling code here:
         BudgetPanel budgetPanel = new BudgetPanel();
          budgetPanel.setNotificationPanel(notificationPanel);
        
        mainSidePanel.removeAll();
        mainSidePanel.add(budgetPanel, "budget");
        mainSidePanel.revalidate();
        mainSidePanel.repaint();
    }//GEN-LAST:event_budgetBtnActionPerformed

    private void goalsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goalsBtnActionPerformed
        // TODO add your handling code here:
         GoalsPanel goalPanel = new GoalsPanel();
           goalPanel.setNotificationPanel(notificationPanel);

        
        mainSidePanel.removeAll();
        mainSidePanel.add(goalPanel, "goals");
        mainSidePanel.revalidate();
        mainSidePanel.repaint();
    }//GEN-LAST:event_goalsBtnActionPerformed

    private void incomemenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incomemenuMouseClicked
        // TODO add your handling code here:
       JPanel incPanel = new JPanel(new BorderLayout(15, 25));
        incPanel.setPreferredSize(new Dimension(250, 80));

        JLabel inc = new JLabel("Set Income Amount");
        
        NumberFormat format = NumberFormat.getNumberInstance();
        
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false); // Only allow valid numbers
        formatter.setMinimum(0.0); // Only allow positive values

        JFormattedTextField incField = new JFormattedTextField(formatter);
        incField.setColumns(20);

        // Add label to NORTH and text field to CENTER to avoid overlap
        incPanel.add(inc, BorderLayout.NORTH);
        incPanel.add(incField, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                null,
                incPanel,
                "Income Input",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            
                Database db = new Database();
                Number value = (Number) incField.getValue();
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                    return;
                }
                BigDecimal incfields = BigDecimal.valueOf(value.doubleValue());

                int userId = Session.userId;
                boolean success = db.insertTotalIncome(userId, incfields);

                if (success) {
                   rawIncome = incfields;
                   NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));
                    String formatted = currencyFormat.format(rawIncome);
                    income.setText(formatted); 
                    
                    JOptionPane.showMessageDialog(null, "Income amount set successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to set income.");
                }

            } else {
                System.out.println("Cancelled");
            }

       
       
    }//GEN-LAST:event_incomemenuMouseClicked

    private void logoutMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMenuMouseClicked
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(
                    DashBoard.this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                // Clear session
                Session.clear();

                // Close dashboard
                DashBoard.this.dispose();

                // Show login dialog
                JFrame parentFrame = new JFrame();
                LoginBox login = new LoginBox(parentFrame, true);
                login.setSize(850,750);
                login.setLocation(300, 70);
                login.setVisible(true);
            }
    }//GEN-LAST:event_logoutMenuMouseClicked

    private void expenseMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expenseMenuMouseClicked
        // TODO add your handling code here:
         JPanel expPanel = new JPanel(new BorderLayout(15, 25));
        expPanel.setPreferredSize(new Dimension(250,80));

        JLabel exp = new JLabel("Set Expenses Amount");
        
        NumberFormat format = NumberFormat.getNumberInstance();
        
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false); // Only allow valid numbers
        formatter.setMinimum(0.0); // Only allow positive values

        JFormattedTextField expField = new JFormattedTextField(formatter);
        expField.setColumns(20);

        // Add label to NORTH and text field to CENTER to avoid overlap
        expPanel.add(exp, BorderLayout.NORTH);
        expPanel.add(expField, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                null,
                expPanel,
                "Expenses Input",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
                Database db = new Database();
                Number value = (Number) expField.getValue();

                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                    return;
                }

                BigDecimal expfields = BigDecimal.valueOf(value.doubleValue());
                int userId = Session.userId;

                boolean success = db.insertTotalExpense(userId, expfields);

                if (success) {
                    rawExpense = expfields; // Keep as BigDecimal

                    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));
                    String formatted = currencyFormat.format(rawExpense);
                    expense.setText(formatted);

                    JOptionPane.showMessageDialog(null, "Expenses amount set successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to set expenses.");
                }

            } else {
                System.out.println("Cancelled");
            }

    }//GEN-LAST:event_expenseMenuMouseClicked

    private void budgetMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_budgetMenuMouseClicked
        // TODO add your handling code here:
         JPanel budgPanel = new JPanel(new BorderLayout(15, 25));
        budgPanel.setPreferredSize(new Dimension(200,90));

        JLabel budg = new JLabel("Set Budget Amount");
        
        NumberFormat format = NumberFormat.getNumberInstance();
        
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false); // Only allow valid numbers
        formatter.setMinimum(0.0); // Only allow positive values

        JFormattedTextField budgField = new JFormattedTextField(formatter);
        budgField.setColumns(20);

        // Add label to NORTH and text field to CENTER to avoid overlap
        budgPanel.add(budg, BorderLayout.NORTH);
        budgPanel.add(budgField, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                null,
                budgPanel,
                "Budget Input",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            
                Database db = new Database();
                Number value = (Number) budgField.getValue();
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                    return;
                }
                BigDecimal budgfields = BigDecimal.valueOf(value.doubleValue());

                int userId = Session.userId;
                boolean success = db.insertTotalBudget(userId, budgfields);

                if (success) {
                   rawBudget = budgfields;
                   NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));
                    String formatted = currencyFormat.format(rawBudget);
                    budget.setText(formatted); 
                    
                    JOptionPane.showMessageDialog(null, "Expenses amount set successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to set expenses.");
                }

            } else {
                System.out.println("Cancelled");
            }
    }//GEN-LAST:event_budgetMenuMouseClicked

    private void savingsMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savingsMenuMouseClicked
        // TODO add your handling code here:
         JPanel savePanel = new JPanel(new BorderLayout(15, 25));
        savePanel.setPreferredSize(new Dimension(250,80));

        JLabel save = new JLabel("Set Savings Amount");
        
        NumberFormat format = NumberFormat.getNumberInstance();
        
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false); // Only allow valid numbers
        formatter.setMinimum(0.0); // Only allow positive values

        JFormattedTextField saveField = new JFormattedTextField(formatter);
        saveField.setColumns(20);

        // Add label to NORTH and text field to CENTER to avoid overlap
        savePanel.add(save, BorderLayout.NORTH);
        savePanel.add(saveField, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                null,
                savePanel,
                "Savings Input",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            
                Database db = new Database();
                Number value = (Number) saveField.getValue();
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                    return;
                }
                BigDecimal savefields = BigDecimal.valueOf(value.doubleValue());

                int userId = Session.userId;
                boolean success = db.insertTotalSavings(userId, savefields);

                if (success) {
                   rawSavings = savefields;
                   NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));
                    String formatted = currencyFormat.format(rawSavings);
                    savings.setText(formatted); 
                    
                    JOptionPane.showMessageDialog(null, "Savings amount set successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to set expenses.");
                }

            } else {
                System.out.println("Cancelled");
            }
    }//GEN-LAST:event_savingsMenuMouseClicked

    private void userMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMenuMouseClicked
        // TODO add your handling code here:
         int userId = Session.userId; // Assuming Session.userId is set after login
        UserProfileBox dialog = new UserProfileBox(null, userId);
        dialog.setVisible(true);
        dialog.setSize(550,650);

           System.out.println("i am clicking");
    }//GEN-LAST:event_userMenuMouseClicked

    private void jMenuBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar1MouseClicked

            private void updateTotalLabel() {
                
                int userId = Session.userId;
                Database db = new Database();
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));

                // ===== Update Total Income =====
                BigDecimal totalIncome = db.getTotalMonthlyIncome(userId);
                boolean incomeSuccess = db.insertTotalIncome(userId, totalIncome);

                if (incomeSuccess) {
                    rawIncome = db.getIncomeByUserId(userId);
                } else {
                    rawIncome = BigDecimal.ZERO;
                }
                income.setText(currencyFormat.format(rawIncome));

                // ===== Update Total Expense =====
                BigDecimal totalExpense = db.getTotalExpense(userId);
                boolean expenseSuccess = db.insertTotalExpense(userId, totalExpense);

                if (expenseSuccess) {
                    rawExpense = db.getExpensesByUserId(userId);
                } else {
                    rawExpense = BigDecimal.ZERO;
                }
                expense.setText(currencyFormat.format(rawExpense));

                // ===== Update Total Budget =====
                BigDecimal totalBudget = db.getTotalMonthlyBudget(userId);
                boolean budgetSuccess = db.insertTotalBudget(userId, totalBudget);

                if (budgetSuccess) {
                    rawBudget = db.getBudgetByUserId(userId);
                } else {
                    rawBudget = BigDecimal.ZERO;
                }
                budget.setText(currencyFormat.format(rawBudget));

                // ===== Update Total Savings =====
                BigDecimal totalSavings = db.getSavingsByUserId(userId);
                boolean savingsSuccess = db.insertTotalSavings(userId, totalSavings);

                if (savingsSuccess) {
                    rawSavings = db.getSavingsByUserId(userId);
                } else {
                    rawSavings = BigDecimal.ZERO;
                }
                savings.setText(currencyFormat.format(rawSavings));
            }

            
            
    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton budgetBtn;
    private javax.swing.JMenu budgetMenu;
    private javax.swing.JButton dashaBtn;
    private javax.swing.JButton expenseBtn;
    private javax.swing.JMenu expenseMenu;
    private javax.swing.JButton goalsBtn;
    private javax.swing.JButton incomeBtn;
    private javax.swing.JMenu incomemenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JMenu logoutMenu;
    private javax.swing.JPanel mainSidePanel;
    private javax.swing.JMenu savingsMenu;
    private javax.swing.JButton transactionBtn;
    private javax.swing.JLabel userLabel;
    private javax.swing.JMenu userMenu;
    // End of variables declaration//GEN-END:variables
}
