import java.util.Scanner;

public class StarTriangle {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("enter the height (n): ");
        int n = keyboard.nextInt();

        for (int i = 1; i <= n; i++) {
            // in khoang trang
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // in dau sao
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        keyboard.close();
    }
}