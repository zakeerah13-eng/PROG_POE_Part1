import java.util.Scanner;

/**
    * Main class - Handles user input/output for Part 1
*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== REGISTRATION ===");

        // Get first name and last name
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        // Get username and keep asking until correct
        String username;
        while (true) {
            System.out.print("Enter Username (must contain _ and <=5 chars, e.g. kyl_1): ");
            username = scanner.nextLine();
            if (login.checkUserName(username)) {
        System.out.println("Username successfully captured.");
            break;
        } else {
        System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
        }
    }

        // Get password and keep asking until correct
        String password;
        while (true) {
            System.out.print("Enter Password (8 chars, Capital, Number, Special e.g. Ch&&sec@ke99!): ");
            password = scanner.nextLine();
            if (login.checkPasswordComplexity(password)) {
        System.out.println("Password successfully captured.");
            break;
        } else {
        System.out.println("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.");
        }
    }

        // Get cell number and keep asking until correct
        String cellNumber;
        while (true) {
            System.out.print("Enter Cell Number (must start with +27, e.g. +27838968976): ");
            cellNumber = scanner.nextLine();
            if (login.checkCellPhoneNumber(cellNumber)) {
        System.out.println(login.registerUser(username, password, cellNumber, firstName, lastName));
            break;
        } else {
        System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
        }
    }

            System.out.println("\n=== LOGIN ===");
            System.out.print("Enter Username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();

        // Check login and show welcome or fail message
        System.out.println(login.returnLoginStatus(loginUsername, loginPassword));
        
// ===== PART 2 STARTS HERE =====
    System.out.println("Welcome to QuickChat.");

    boolean running = true;
    while (running) {
        System.out.println("\n--- QuickChat Menu ---");
        System.out.println("1) Send Messages");
        System.out.println("2) Show recently sent messages");
        System.out.println("3) Quit");
        System.out.print("Choose an option: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
    System.out.print("How many messages do you wish to enter? ");
            int numMessages;
            try {
            numMessages = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
    System.out.println("Please enter a valid number.");
            break;
        }

        for (int i = 0; i < numMessages; ) {
    System.out.println("\n--- Message " + (i + 1) + " ---");
    System.out.print("Enter Recipient (+27...): ");
            String recipient = scanner.nextLine();
    System.out.print("Enter Message (max 250 chars): ");
            String text = scanner.nextLine();

    // Create Message object
            Message msg = new Message(i, recipient, text);

    // Check recipient
            String recCheck = msg.checkRecipientCell();
    System.out.println(recCheck);
        if (recCheck.contains("incorrectly formatted")) {
    continue; // don't count this attempt
    }

    // Check length
        if (text.length() > 250) {
    System.out.println("Please enter a message of less than 250 characters. Your message was " + (text.length() - 250) + " characters over.");
        continue;
    }

    // Show ID and Hash
    System.out.println(msg.getMessageIDGenerated());
    System.out.println("Message Hash: " + msg.createMessageHash());

    System.out.println("1) Send Message");
    System.out.println("2) Disregard Message");
    System.out.println("3) Store Message to send later");
    System.out.print("Choose: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
    System.out.println("Invalid choice, storing message.");
            choice = 3;
        }

            String result = msg.SentMessage(choice);
    System.out.println(result);

            if (result.equals("Message successfully sent.")) {
    System.out.println(msg.printMessages());
    }
            i++; // only increase if recipient and length were valid
    }
    System.out.println("\nTotal messages sent: " + Message.returnTotalMessagess());
        break;

            case "2":
    System.out.println("Coming Soon.");
        break;

            case "3":
        running = false;
    System.out.println("Quitting QuickChat. Total messages sent: " + Message.returnTotalMessagess());
            break;

        default:
    System.out.println("Invalid option. Please choose 1, 2, or 3.");
        }
    }
        scanner.close();
        return;
    }
}
