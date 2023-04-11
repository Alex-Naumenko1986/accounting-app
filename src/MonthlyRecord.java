public class MonthlyRecord {
    int monthNumber;
    int expense;
    int profit;

    MonthlyRecord (int monthNumber, int expense, int profit) {
        this.monthNumber = monthNumber;
        this.expense = expense;
        this.profit = profit;
    }

    public int getIncome() {
        return profit - expense;
    }
}
