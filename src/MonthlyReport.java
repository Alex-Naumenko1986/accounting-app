import java.util.ArrayList;

public class MonthlyReport {
    int year;
    String monthName;
    int monthNumber;
    ArrayList <Item> expenses;
    ArrayList <Item> profits;

    public MonthlyReport (int year, String monthName, int monthNumber) {
        this.year = year;
        this.monthName = monthName;
        this.monthNumber = monthNumber;
        expenses = new ArrayList<>();
        profits = new ArrayList<>();
    }

    public boolean isEmpty() {
        return (expenses.isEmpty() && profits.isEmpty());
    }

    public Item getMostExpensiveItem () {
        Item mostExpensiveItem = null;
        for (Item expense : expenses) {
            if (mostExpensiveItem == null) {
                mostExpensiveItem = expense;
                continue;
            }
            if (mostExpensiveItem.getTotalSum() < expense.getTotalSum()) {
                mostExpensiveItem = expense;
            }
        }
        return mostExpensiveItem;
    }

    public Item getMostProfitableItem () {
        Item mostProfitableItem = null;
        for (Item profit : profits) {
            if (mostProfitableItem == null) {
                mostProfitableItem = profit;
                continue;
            }
            if (mostProfitableItem.getTotalSum() < profit.getTotalSum()) {
                mostProfitableItem = profit;
            }
        }
        return mostProfitableItem;
    }

    public int getTotalExpense() {
        int sum = 0;
        for (Item expense : expenses) {
            sum += expense.getTotalSum();
        }
        return sum;
    }

    public int getTotalProfit() {
        int sum = 0;
        for (Item profit : profits) {
            sum += profit.getTotalSum();
        }
        return sum;
    }

}
