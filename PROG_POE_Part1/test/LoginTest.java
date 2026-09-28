import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoginTest {
Login login = new Login();

@Test public void testCheckUserNameTrue() { assertTrue(login.checkUserName("kyl_1")); }
@Test public void testCheckUserNameFalse() { assertFalse(login.checkUserName("kyle!!!!!!!")); }
@Test public void testPasswordTrue() { assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!")); }
@Test public void testPasswordFalse() { assertFalse(login.checkPasswordComplexity("password")); }
@Test public void testCellTrue() { assertTrue(login.checkCellPhoneNumber("+27838968976")); }
@Test public void testCellFalse() { assertFalse(login.checkCellPhoneNumber("08966553")); }
@Test public void testLogin() { login.registerUser("kyl_1","Ch&&sec@ke99!","+27838968976","Kyle","Smith"); assertTrue(login.loginUser("kyl_1","Ch&&sec@ke99!")); }
@Test public void testStatus() { login.registerUser("kyl_1","Ch&&sec@ke99!","+27838968976","Kyle","Smith"); assertTrue(login.returnLoginStatus("kyl_1","Ch&&sec@ke99!").contains("Welcome")); }
}