import java.util.Scanner;

public final class InputHelper {

    private InputHelper() {
    }

    public static int readPositiveInt(Scanner sc, String prompt) {
        return readInt(sc, prompt, 1, Integer.MAX_VALUE);
    }

    public static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        return readInt(sc, prompt, min, max);
    }

    public static String readNonEmptyLine(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty. Try again.");
        }
    }

    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String raw = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(raw);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Validation message is printed below.
            }
            if (min == max) {
                System.out.println("Invalid input. Enter " + min + ".");
            } else {
                System.out.println("Invalid input. Enter a number between " + min + " and " + max + ".");
            }
        }
    }
}
