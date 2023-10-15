import java.util.Scanner;

public class userInput {
    static Scanner scan = new Scanner(System.in);

    /**
     * Retrieves the user's choice within the specified range.
     *
     * @param  min  the minimum value of the range (inclusive)
     * @param  max  the maximum value of the range (inclusive)
     * @return      the user's valid choice within the range
     */
    public static int getUserChoice(int min, int max) {
        int choice;
        while (true) {
            System.out.print("\nEnter your choice: ");
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
                scan.nextLine();
                if (choice >= min && choice <= max) {
                    return choice;
                }
            }
            System.out.println("Invalid input. Please enter a valid choice.");
            scan.nextLine(); // Consume the invalid input
        }
    }
}