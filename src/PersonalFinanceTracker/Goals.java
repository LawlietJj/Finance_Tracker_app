/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersonalFinanceTracker;

import java.math.BigDecimal;
import java.util.Date;



/**
 *
 * @author HP ElitePC
 */
public class Goals {
    
     private int id;
    private int userId;
    private String goalType;
    private String goalName;
    private BigDecimal amount;
    private Date deadline;

    public Goals(int id, int userId, String goalType, String goalName, BigDecimal amount, Date deadline) {
        this.id = id;
        this.userId = userId;
        this.goalType = goalType;
        this.goalName = goalName;
        this.amount = amount;
        this.deadline = deadline;
    }

    // Getters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getGoalType() { return goalType; }
    public String getGoalName() { return goalName; }
    public BigDecimal getAmount() { return amount; }
    public Date getDeadline() { return deadline; }
}
