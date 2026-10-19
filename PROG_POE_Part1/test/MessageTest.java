import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Message class
 * Tests message length validation, recipient cell checks, hash creation, and sending options
 */
public class MessageTest {

    // Reset total messages counter before each test
    @BeforeEach
    public void setUp() {
        Message.resetTotalMessages();
    }

    // Tests that a message within 250 characters is ready to send
    @Test
    public void testMessageLength_Success() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    // Tests that a message exceeding 250 characters returns the excess character warning
    @Test
    public void testMessageLength_Failure() {
        String longMessage = "A".repeat(260);
        Message msg = new Message(0, "+27718693002", longMessage);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.checkMessageLength());
    }

    // Tests that a valid international cell number is captured successfully
    @Test
    public void testRecipient_Success() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    // Tests that a number missing the international code returns an error message
    @Test
    public void testRecipient_Failure() {
        Message msg = new Message(0, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    // Tests that the message hash is correctly created for test case 1
    @Test
    public void testMessageHash_TestCase1() {
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    // Tests multiple message hashes in a loop using test data
    @Test
    public void testMessageHashes_Loop() {
        String[] testIDs = {"0012345678", "0087654321"};
        int[] numbers = {0, 1};
        String[] messages = {
            "Hi Mike, can you join us for dinner tonight?",
            "Hi Keegan, did you receive the payment?"
        };
        String[] expectedHashes = {
            "00:0:HITONIGHT",
            "00:1:HIPAYMENT"
        };

        for (int i = 0; i < testIDs.length; i++) {
            Message msg = new Message(testIDs[i], numbers[i], "+27718693002", messages[i]);
            assertEquals(expectedHashes[i], msg.createMessageHash(), "Hash mismatch at index " + i);
        }
    }

    // Tests that message ID is generated with 10 digits and formatted correctly
    @Test
    public void testMessageID_Created() {
        Message msg = new Message(0, "+27718693002", "Test Message");
        assertEquals("Message ID generated: " + msg.getMessageID(), msg.getMessageIDGenerated());
        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
    }

    // Tests selecting Option 1 to send message and increments total count
    @Test
    public void testMessageSent_Option1_Send() {
        Message msg = new Message(0, "+27718693002", "Test Message");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals(1, Message.returnTotalMessagess());
    }

    // Tests selecting Option 2 to disregard message without incrementing total count
    @Test
    public void testMessageSent_Option2_Disregard() {
        Message msg = new Message(0, "+27718693002", "Test Message");
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
        assertEquals(0, Message.returnTotalMessagess());
    }

    // Tests selecting Option 3 to store message to send later
    @Test
    public void testMessageSent_Option3_Store() {
        Message msg = new Message(0, "+27718693002", "Test Message");
        assertEquals("Message successfully stored.", msg.SentMessage(3));
        assertEquals(0, Message.returnTotalMessagess());
    }

    // Tests that printMessages outputs all details in the expected order
    @Test
    public void testPrintMessages_Order() {
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String output = msg.printMessages();
        assertTrue(output.contains("Message ID: 0012345678"));
        assertTrue(output.contains("Message Hash: 00:0:HITONIGHT"));
        assertTrue(output.contains("Recipient: +27718693002"));
        assertTrue(output.contains("Message: Hi Mike, can you join us for dinner tonight?"));
    }

    // Tests storing the message data to a JSON file
    @Test
    public void testStoreMessage_JSON() {
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.storeMessage());
    }
}