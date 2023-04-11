import java.util.HashMap;

public class YearlyReport {
    int year;

    HashMap <Integer, MonthlyRecord> monthlyRecords;

    YearlyReport (int year) {
        this.year = year;
        monthlyRecords = new HashMap<>();
    }

    public void addExpense(int monthNumber, int expense) {
        if (monthlyRecords.containsKey(monthNumber)) {
            MonthlyRecord record = monthlyRecords.get(monthNumber);
            record.expense = expense;
        } else {
            MonthlyRecord record = new MonthlyRecord(monthNumber, expense, 0);
            monthlyRecords.put(monthNumber, record);
        }
    }

    public void addProfit(int monthNumber, int profit) {
        if (monthlyRecords.containsKey(monthNumber)) {
            MonthlyRecord record = monthlyRecords.get(monthNumber);
            record.profit = profit;
        } else {
            MonthlyRecord record = new MonthlyRecord(monthNumber, 0, profit);
            monthlyRecords.put(monthNumber, record);
        }
    }

    public boolean isEmpty () {
        return monthlyRecords.isEmpty();
    }

    public double getAverageProfit () {
        double sum = 0;

        for (MonthlyRecord record : monthlyRecords.values()) {
            sum += record.profit;
        }
        return sum/monthlyRecords.size();
    }

    public double getAverageExpense () {
        double sum = 0;

        for (MonthlyRecord record : monthlyRecords.values()) {
            sum += record.expense;
        }
        return sum/monthlyRecords.size();
    }
}
