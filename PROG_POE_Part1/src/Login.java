/**
* PROG5121 - Part 1 - Registration and Login Feature
* This class handles all validation checks for the POE
*/
public class Login {
    // Variables to store user details after successful registration
    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private String storedFirstName;
    private String storedLastName;

    /**
    * a. Check username - must contain underscore and be no more than 5 characters
    * Test Data from POE: "kyl_1" = True, "kyle!!!!!!!" = False
    * @param username the username to check
    * @return true if correctly formatted, false if not
    */
    public boolean checkUserName(String username) {
        // Check if username contains "_" and length is 5 or less
        return username.contains("_") && username.length() <= 5;
    }

    /**
    * Check password complexity
    * Must have: at least 8 chars, 1 capital letter, 1 number, 1 special character
    * Test Data from POE: "Ch&&sec@ke99!" = True
    * @param password the password to check
    * @return true if meets complexity, false if not
    */
    public boolean checkPasswordComplexity(String password) {
        // Check length >= 8
        boolean isLongEnough = password.length() >= 8;
        // Check for capital letter using regex
        boolean hasCapital = password.matches(".*[A-Z].*");
        // Check for number using regex
        boolean hasNumber = password.matches(".*[0-9].*");
        // Check for special character using regex
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        // All conditions must be true
        return isLongEnough && hasCapital && hasNumber && hasSpecialChar;
    }

    /**
    * b. Regex-based cell phone checker
    * Ensures number contains international code +27
    * Test Data from POE: "+27838968976" = True, "08966553" = False
    *
    * Reference: Oracle (2023). Class Pattern - Regular Expressions.
    * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
    * Explanation: ^\\+27 means starts with +27, \\d{9} means 9 digits after that
    * Total length is 12 characters (+27 + 9 digits)
    *
    * @param cellNumber the cell number to check
    * @return true if correctly formatted
    */
    public boolean checkCellPhoneNumber(String cellNumber) {
        // Regex: Must start with +27 followed by exactly 9 digits
        String cellRegex = "^\\+27\\d{9}$";
        return cellNumber.matches(cellRegex);
    }

    /**
    * Register user - returns appropriate message for each failure/success
    * This method is tested with assertEquals in POE Page 9 & 10
    */
    public String registerUser(String username, String password, String cellNumber, String firstName, String lastName) {
        // Store names for welcome message later
        this.storedFirstName = firstName;
        this.storedLastName = lastName;

        // Check username first
        if (!checkUserName(username)) {
            // Message from POE Page 9 - must be exact
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        // Check password second
        if (!checkPasswordComplexity(password)) {
            // Message from POE Page 9 - must be exact
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        }

        // Check cell number third
        if (!checkCellPhoneNumber(cellNumber)) {
            // Message from POE Page 10 - must be exact
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        // If all checks pass, store the details
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellNumber = cellNumber;

        // Success message from POE Page 10
        return "Cell number successfully captured.";
}

    /**
    * Login verification - checks if entered details match stored details
    * @return True if login successful, False if failed (Page 10)
    */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        // Compare entered with stored
        return enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword);
    }

    /**
    * Return login status message
    * True message: "Welcome <first>, <last> it is great to see you again."
    * False message: "Username or password incorrect, please try again."
    */
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            // Successful login
            return "Welcome " + storedFirstName + ", " + storedLastName + " it is great to see you again.";
        } else {
            // Failed login
            return "Username or password incorrect, please try again.";
            }
        }
    }
