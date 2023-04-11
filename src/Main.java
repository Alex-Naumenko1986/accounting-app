import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportManager reportManager = new ReportManager(2021);

        while (true) {
            printMenu();
            String userInput = scanner.nextLine();
            if (userInput.equals("Выход")) {
                System.out.println("Работа приложения завершена");
                break;
            }
            int command = 0;
            try {
                command = Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Введена неизвестная команда");
            }

            switch (command) {
                case 1:
                    reportManager.readMonthlyReports();
                    break;
                case 2:
                    reportManager.readYearlyReport();
                    break;
                case 3:
                    reportManager.compareReports();
                    break;
                case 4:
                    reportManager.printMonthlyStatistics();
                    break;
                case 5:
                    reportManager.printYearlyStatistics();
                    break;
                default:
                    System.out.println("Извините, такой команды пока нет. Повторите ввод");
            }


        }
    }

    public static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("\"Выход\" - выйти из приложения");
    }
}

