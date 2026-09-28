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

            scanner.close();
        }
    }
