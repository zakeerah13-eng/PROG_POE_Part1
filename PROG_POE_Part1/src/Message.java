public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private int messageNumber;
    private static int totalMessages = 0;

// Constructor for app - generates random 10-digit ID
public Message(int number, String recipient, String message) {
    this.messageNumber = number;
    this.recipient = recipient;
    this.message = message;
    this.messageID = String.format("%010d", (int)(Math.random() * 1000000000));
    this.messageHash = createMessageHash();
}

// Constructor for UNIT TEST - lets test use ID starting with 00
public Message(String messageID, int number, String recipient, String message) {
    this.messageID = messageID;
    this.messageNumber = number;
    this.recipient = recipient;
    this.message = message;
    this.messageHash = createMessageHash();
}

// Check ID is max 10 chars
public Boolean checkMessageID() {
    return messageID.length() <= 10;
}

// Must return these exact strings
public String checkRecipientCell() {
        if (recipient != null && recipient.contains("+") && recipient.length() >= 10 && recipient.length() <= 13) {
        return "Cell phone number successfully captured.";
}
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
}

// Creates 00:0:HITONIGHT hash
public String createMessageHash() {
    String[] words = message.trim().split("\\s+");
    String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
    String lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
        return (messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
}

public String getMessageIDGenerated() {
        return "Message ID generated: " + messageID;
}

// Must return these exact strings
public String SentMessage(int choice) {
        if (choice == 1) {
            totalMessages++;
        return "Message successfully sent.";
}
        if (choice == 2) {
        return "Press 0 to delete the message.";
}
        return "Message successfully stored.";
}

public String printMessages() {
        return "MessageID: " + messageID + "\nMessageHash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + message;
}

public static int returnTotalMessagess() {
        return totalMessages;
}

// Getters
public String getMessageID() { return messageID; }
public String getMessageHash() { return messageHash; }
public String getRecipient() { return recipient; }
public String getMessage() { return message; }
}