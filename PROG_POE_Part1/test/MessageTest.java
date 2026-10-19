import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

// Test if message length is OK (less than 250)
@Test
public void testMessageLength_Success() {
Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight");
assertEquals("Message ready to send.", msg.checkMessageLength());
}

// Test if message is too long (more than 250)
@Test
public void testMessageLength_Failure() {
String longMsg = "a".repeat(260);
Message msg = new Message(0, "+27718693002", longMsg);
assertTrue(msg.checkMessageLength().contains("Message exceeds 250 characters by 10"));
}

// Test correct cell number with +
@Test
public void testRecipient_Success() {
Message msg = new Message(0, "+27718693002", "Hi Mike");
assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
}

// Test wrong cell number without +
@Test
public void testRecipient_Failure() {
Message msg = new Message(0, "12345", "Test");
assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
}

// Test hash - must be 00:0:HITONIGHT
@Test
public void testMessageHash() {
Message msg = new Message("0012345678", 0, "+27718693002", "Hi tonight");
assertEquals("00:0:HITONIGHT", msg.createMessageHash());
}

// Test ID is 10 chars
@Test
public void testMessageID() {
Message msg = new Message(0, "+27718693002", "Test");
assertEquals(10, msg.getMessageID().length());
}

// Test send/delete/store messages
@Test
public void testMessageSent() {
Message msg = new Message(0, "+27718693002", "Test");
assertEquals("Message successfully sent.", msg.SentMessage(1));
assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
assertEquals("Message successfully stored.", msg.SentMessage(3));
}
}