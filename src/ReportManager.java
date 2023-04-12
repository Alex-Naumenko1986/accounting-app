import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportManager {
    int year;
    ArrayList<MonthlyReport> monthlyReports;
    YearlyReport yearlyReport;

    public ReportManager (int year) {
        this.year = year;
        yearlyReport = new YearlyReport(2021);
        monthlyReports = new ArrayList<>();
    }

    public void readMonthlyReports () {
        monthlyReports.clear();

        for (int i = 1; i <=3; i++) {
            Month month = Month.values()[i-1];
            String monthName = month.translation;
            MonthlyReport monthlyReport = new MonthlyReport(year, monthName, i);

            String path = "resources/m.20210" + i + ".csv";
            List <String> content = readFileContents(path);
            for (int j = 1; j < content.size(); j++) {
                String[] parts = content.get(j).split(",");
                String name = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int sumOfOne = Integer.parseInt(parts[3]);
                Item item = new Item (name, quantity, sumOfOne);
                if (isExpense) {
                    monthlyReport.expenses.add(item);
                } else {
                    monthlyReport.profits.add(item);
                }
            }
            monthlyReports.add(monthlyReport);
        }
        if (!(monthlyReports.get(0).isEmpty())) {
            System.out.println("Месячные отчеты успешно считаны");
        } else {
            System.out.println("Что-то пошло не так. Месячные отчеты не считаны");
        }
    }

    public void readYearlyReport () {
        String path = "resources/y." + year + ".csv";
        List<String> content = readFileContents(path);

        for (int i = 1; i < content.size(); i++) {
            String[] parts = content.get(i).split(",");
            int monthNumber = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            if (isExpense) {
                yearlyReport.addExpense(monthNumber, amount);
            } else {
                yearlyReport.addProfit(monthNumber, amount);
            }
        }

        if (!yearlyReport.isEmpty()) {
            System.out.println("Годовой отчет успешно считан");
        } else {
            System.out.println("Что-то пошло не так. Годовой отчет не считан");
        }
    }

    public void printMonthlyStatistics() {
        if (monthlyReports.isEmpty() || monthlyReports.get(0).isEmpty()) {
            System.out.println("Перед выводом статиститки считайте месячные отчеты");
        } else {
            for (MonthlyReport monthlyReport : monthlyReports) {
                System.out.println(monthlyReport.monthName + ":");
                Item mostProfitableItem = monthlyReport.getMostProfitableItem();
                if (mostProfitableItem != null) {
                    System.out.println("Самый прибыльный товар: " + mostProfitableItem.name + " на сумму " +
                            mostProfitableItem.getTotalSum() + " руб.");
                } else {
                    System.out.println("Прибыльных товаров в этом месяце нет");
                }
                Item mostExpensiveItem = monthlyReport.getMostExpensiveItem();
                if (mostExpensiveItem != null) {
                    System.out.println("Самая большая трата: " + mostExpensiveItem.name + " на сумму " +
                            mostExpensiveItem.getTotalSum() + " руб.");
                } else {
                    System.out.println("Расходов в этом месяце нет");
                }
            }
        }
    }

        public void printYearlyStatistics () {
            if (yearlyReport.isEmpty()) {
                System.out.println("Сначала считайте годовой отчет");
            } else {
                System.out.println("Год: " + year);
                for (int i = 0; i < yearlyReport.monthlyRecords.size(); i++) {
                    Month month = Month.values()[i];
                    MonthlyRecord record = yearlyReport.monthlyRecords.get(i+1);
                    System.out.println("Прибыль за " + month.translation + ": " + record.getIncome() + " руб.") ;
                }
                System.out.printf("Средний расход за все месяцы в году: %.2f руб.\n", yearlyReport.getAverageExpense());
                System.out.printf("Средний доход за все месяцы в году: %.2f руб.\n", yearlyReport.getAverageProfit());
            }
        }

        public void compareReports() {
            if (yearlyReport.isEmpty() || monthlyReports.isEmpty() || monthlyReports.get(0).isEmpty()) {
                System.out.println("Перед сверкой необходимо считать все отчеты");
                return;
            }

            ArrayList <String> wrongMonths = getWrongMonths();
            if (wrongMonths.size() == 0) {
                System.out.println("Сверка отчетов произведена успешно. Расхождений не обнаружено");
            } else {
                System.out.println("Месяцы, в которых найдено несоответствие:");
                for (String wrongMonth : wrongMonths) {
                    System.out.println(wrongMonth);
                }
            }
        }

        private ArrayList<String> getWrongMonths() {
            ArrayList <String> wrongMonths = new ArrayList<>();
            for (MonthlyReport monthlyReport : monthlyReports) {
                int profitInMonthlyReport = monthlyReport.getTotalProfit();
                int expenseInMonthlyReport = monthlyReport.getTotalExpense();
                int monthNumber = monthlyReport.monthNumber;
                MonthlyRecord monthlyRecord = yearlyReport.monthlyRecords.get(monthNumber);
                if (profitInMonthlyReport != monthlyRecord.profit || expenseInMonthlyReport != monthlyRecord.expense) {
                    wrongMonths.add(monthlyReport.monthName);
                }
            }
            return wrongMonths;
        }


        private List<String> readFileContents (String path){
            try {
                return Files.readAllLines(Path.of(path));
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с отчётом. " +
                        "Возможно файл не находится в директории: " + path);
                return Collections.emptyList();
            }
        }
    }



