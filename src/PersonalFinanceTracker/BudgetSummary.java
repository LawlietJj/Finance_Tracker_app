/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersonalFinanceTracker;
import java.math.BigDecimal;
/**
 *
 * @author HP ElitePC
 */
public class BudgetSummary {
     private String category;
    private BigDecimal budget;
    private BigDecimal actual;
    private BigDecimal savings;

    public BudgetSummary(String category, BigDecimal budget, BigDecimal actual, BigDecimal savings) {
        this.category = category;
        this.budget = budget;
        this.actual = actual;
        this.savings = savings;
    }

    // Getters
    public String getCategory() { return category; }
    public BigDecimal getBudget() { return budget; }
    public BigDecimal getActual() { return actual; }
    public BigDecimal getSavings() { return savings; }
}
