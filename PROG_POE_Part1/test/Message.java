import java.io.FileWriter;
import java.io.IOException;

/**
 * Message class handling message details, validation, and storage
 */
public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private int messageNumber;
    private static int totalMessages = 0;

    // Constructor with auto-generated ID
    public Message(int number, String recipient, String message) {
        this.messageNumber = number;
        this.recipient = recipient;
        this.message = message;
        long randNum = (long) (Math.random() * 9000000000L) + 1000000000L;
        String idStr = String.valueOf(randNum);
        this.messageID = idStr.substring(0, 10);
        this.messageHash = createMessageHash();
    }

    // Constructor for custom ID (used in testing)
    public Message(String messageID, int number, String recipient, String message) {
        this.messageID = messageID;
        this.messageNumber = number;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }

    // Checks if message ID is no more than 10 characters
    public Boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Validates recipient number formatting
    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Validates message length
    public String checkMessageLength() {
        if (message == null || message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Generates message hash in format: IDPrefix:Number:FirstWordLastWord
    public String createMessageHash() {
        String prefix = "00";
        if (messageID != null && messageID.length() >= 2) {
            prefix = messageID.substring(0, 2);
        }

        if (message == null || message.trim().isEmpty()) {
            return (prefix + ":" + messageNumber + ":").toUpperCase();
        }

        String[] words = message.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");

        return (prefix + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Returns generated ID confirmation string
    public String getMessageIDGenerated() {
        return "Message ID generated: " + messageID;
    }

    // Handles user choice to send, disregard, or store message
    public String SentMessage(int choice) {
        if (choice == 1) {
            totalMessages++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else {
            storeMessage();
            return "Message successfully stored.";
        }
    }

    public String sentMessage(int choice) {
        return SentMessage(choice);
    }

    // Returns full message details
    public String printMessages() {
        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + message;
    }

    // Returns total messages sent
    public static int returnTotalMessagess() {
        return totalMessages;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    public static void resetTotalMessages() {
        totalMessages = 0;
    }

    /**
     * Stores message in JSON format into messages.json
     * Reference: Oracle (2023). Basic I/O: Character Streams.
     */
    public boolean storeMessage() {
        String jsonRecord = "{\n"
                + "  \"messageID\": \"" + messageID + "\",\n"
                + "  \"messageNumber\": " + messageNumber + ",\n"
                + "  \"recipient\": \"" + recipient + "\",\n"
                + "  \"message\": \"" + escapeJson(message) + "\",\n"
                + "  \"messageHash\": \"" + messageHash + "\"\n"
                + "}\n";

        try (FileWriter writer = new FileWriter("messages.json", true)) {
            writer.write(jsonRecord);
            return true;
        } catch (IOException e) {
            System.err.println("Error storing message to JSON: " + e.getMessage());
            return false;
        }
    }

    private String escapeJson(String raw) {
        if (raw == null) return "";
        return raw.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\b", "\\b")
                  .replace("\f", "\\f")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t");
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getMessageHash() { return messageHash; }
    public int getMessageNumber() { return messageNumber; }
}