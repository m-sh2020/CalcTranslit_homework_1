import java.util.Scanner;

public class Zadanie1Calculator {

	public static boolean fl = true; // flag that determines whether the entered operation is correct
	// calculation function
	public static double calculate(double n1, double n2, char operation) {
		double result = 0.0;
		switch (operation) {
		case '+':
			result = n1 + n2;
			break;
		case '-':
			result = n1 - n2;
			break;
		case '*':
			result = n1 * n2;
			break;
		case '/':
			result = n1 / n2;
			break;
		default:
			System.out.println("Incorrect operation");
			fl = false;
			break;
		}
		return result;
	}

	public static void main(String[] args) {

		double number1, number2, result;
		char operation;
		Scanner scan = new Scanner(System.in);

		while (true) {
			try {
				System.out.println("Enter the first number and press Enter:");
				number1 = scan.nextDouble();
				System.out.println("Enter the second number and press Enter:");
				number2 = scan.nextDouble();
				System.out.println("Enter the operation and press Enter:");
				operation = scan.next().charAt(0);

				result = calculate(number1, number2, operation);

				if (Double.isInfinite(result)) {
					System.out.println("Division by 0 is not defined");
				} else if (fl == true) {
					System.out.println("Result: " + result);
					scan.close();
					break;
				} else {
					fl = true;
				}

			} catch (Exception e) {
				System.out.println("Incorrect input");
				scan.next();
			}
		}
	}
}
