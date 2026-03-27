import java.util.Arrays;
import java.util.Scanner;

public class ArraySortSumAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("enter the number of elements: ");
        int n = scanner.nextInt();
        double[] my_array1 = new double[n];

        System.out.println("enter the elements:");
        for (int i = 0; i < n; i++) {
            my_array1[i] = scanner.nextDouble();
        }

        // sap xep mang
        Arrays.sort(my_array1);

        // sum va tb
        double sum = 0;
        for (double num : my_array1) {
            sum += num;
        }
        double average = sum / n;


        System.out.println("sorted array: " + Arrays.toString(my_array1));
        System.out.println("sum: " + sum);
        System.out.println("average: " + average);

        scanner.close();
    }
}