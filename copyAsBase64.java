import java.util.Scanner;

public class NumberConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input from the user
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        // Convert and display the number in different formats
        System.out.println("Decimal (Base 10): " + number);
        System.out.println("Binary (Base 2): " + Integer.toBinaryString(number));
        System.out.println("Hexadecimal (Base 16): " + Integer.toHexString(number).toUpperCase());
    }
}
