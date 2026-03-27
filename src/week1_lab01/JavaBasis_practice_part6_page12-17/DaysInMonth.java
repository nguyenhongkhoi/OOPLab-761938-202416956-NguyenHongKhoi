import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int month = 0, year = 0;

        while (true) {
            System.out.print("enter month (1-12): ");
            month = sc.nextInt();
            System.out.print("enter year: ");
            year = sc.nextInt();

            if (month >= 1 && month <= 12 && year >= 0) {
                break;
            }
            System.out.println("invalid month/year. please try again.");
        }

        int days = 0;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                days = 31;
                break;
            case 4: case 6: case 9: case 11:
                days = 30;
                break;
            case 2:
                // kiem tra nam nhuan
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    days = 29;
                } else {
                    days = 28;
                }
                break;
        }

        System.out.println("number of days: " + days);
        sc.close();
    }
}