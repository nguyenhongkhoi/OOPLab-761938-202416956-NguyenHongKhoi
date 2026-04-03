package src.week1_lab01.Bai2_2_5_page7_8;

import javax.swing.JOptionPane;

public class CalculateTwoNumber {
    public static void main(String[] args) {
        String strNum1 = JOptionPane.showInputDialog(null, "Please input the first number: ", "Input", JOptionPane.INFORMATION_MESSAGE);
        String strNum2 = JOptionPane.showInputDialog(null, "Please input the second number: ", "Input", JOptionPane.INFORMATION_MESSAGE);

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        double sum = num1 + num2;
        double diff = num1 - num2;
        double product = num1 * num2;

        String result = "Sum: " + sum + "\nDifference: " + diff + "\nProduct: " + product + "\n";

        if (num2 != 0) {
            result += "Quotient: " + (num1 / num2);
        } else {
            result += "Quotient: Cannot divide by zero!";
        }

        JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
