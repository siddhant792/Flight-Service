import services.UserService;
import services.servicesImpl.UserServiceImpl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import models.User;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();
    }

    @Test
    public void testAddUser() {
        // Test adding a user with valid parameters
        User user = userService.addUser("u1", "Nitin", 5000);
        assertEquals("u1", user.getUserId());
        assertEquals("Nitin", user.getName());
    }
}
