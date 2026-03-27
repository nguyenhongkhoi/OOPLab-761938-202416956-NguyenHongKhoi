package src.week1_lab01;

import java.util.Scanner;

public class solve_function {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Equation Solver ---");
            System.out.println("1. First-degree equation (ax + b = 0)");
            System.out.println("2. System of first-degree equations (2 variables)");
            System.out.println("3. Second-degree equation (ax^2 + bx + c = 0)");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: solveLinearEquation(scanner); break;
                case 2: solveLinearSystem(scanner); break;
                case 3: solveQuadraticEquation(scanner); break;
            }
        } while (choice != 0);

        scanner.close();
    }

    // 1. Giai phuong trinh bac nhat: ax + b = 0
    public static void solveLinearEquation(Scanner sc) {
        System.out.print("enter a: ");
        double a = sc.nextDouble();
        System.out.print("enter b: ");
        double b = sc.nextDouble();

        if (a == 0) {
            if (b == 0) System.out.println("infinite solutions.");
            else System.out.println("no solution.");
        } else {
            double x = -b / a;
            System.out.println("solution: x = " + x);
        }
    }

    // 2. Giai he phuong trinh bac nhat 2 an bang dinh thuc (Cramer)
    public static void solveLinearSystem(Scanner sc) {
        System.out.println("system: a11*x1 + a12*x2 = b1 | a21*x1 + a22*x2 = b2");
        System.out.print("a11, a12, b1: ");
        double a11 = sc.nextDouble(), a12 = sc.nextDouble(), b1 = sc.nextDouble();
        System.out.print("a21, a22, b2: ");
        double a21 = sc.nextDouble(), a22 = sc.nextDouble(), b2 = sc.nextDouble();

        double d = a11 * a22 - a21 * a12;
        double d1 = b1 * a22 - b2 * a12;
        double d2 = a11 * b2 - a21 * b1;

        if (d != 0) {
            System.out.println("solution: x1 = " + (d1 / d) + ", x2 = " + (d2 / d));
        } else {
            if (d1 == 0 && d2 == 0) System.out.println("infinitely many solutions.");
            else System.out.println("no solution.");
        }
    }

    // 3. Giai phuong trinh bac hai: ax^2 + bx + c = 0
    public static void solveQuadraticEquation(Scanner sc) {
        System.out.print("enter a, b, c: ");
        double a = sc.nextDouble(), b = sc.nextDouble(), c = sc.nextDouble();

        if (a == 0) {
            // tro thanh phuong trinh bac nhat bx + c = 0
            if (b == 0) {
                if (c == 0) System.out.println("infinite solutions.");
                else System.out.println("no solution.");
            } else {
                System.out.println("solution: x = " + (-c / b));
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                System.out.println("no real root.");
            } else if (delta == 0) {
                System.out.println("double root: x = " + (-b / (2 * a)));
            } else {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                System.out.println("two distinct roots: x1 = " + x1 + ", x2 = " + x2);
            }
        }
    }
}