import java.util.Scanner;

/**
 * Handles all user input with validation
 */
public class InputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    // Complex: Handles field-specific integer input with validation
    public static int getIntInput(String fieldName) {
        System.out.print("ENTER " + fieldName + ": ");
        while (true) {
            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                // Important: Prevents crashes on non-numeric input
                System.out.print("INVALID " + fieldName + ". MUST BE A NUMBER: ");
            }
        }
    }

    // Complex: Validates number is within specified range
    public static int getIntInRange(int min, int max) {
        int input;
        do {
            input = getInt();
            if (input < min || input > max) {
                System.out.printf("PLEASE ENTER BETWEEN %d AND %d: ", min, max);
            }
        } while (input < min || input > max);
        return input;
    }

    // Basic integer input
    public static int getInt() {
        while (!SCANNER.hasNextInt()) {
            System.out.print("INVALID INPUT. PLEASE ENTER A NUMBER: ");
            SCANNER.next();
        }
        int num = SCANNER.nextInt();
        SCANNER.nextLine(); // Clear buffer
        return num;
    }

    // Complex: Ensures non-empty string input
    public static String getNonEmptyString(String fieldName) {
        String input;
        do {
            System.out.print("ENTER " + fieldName + ": ");
            input = SCANNER.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print(fieldName + " CANNOT BE EMPTY. ");
            }
        } while (input.isEmpty());
        return input;
    }

    // Basic string input
    public static String getString() {
        return SCANNER.nextLine();
    }
}