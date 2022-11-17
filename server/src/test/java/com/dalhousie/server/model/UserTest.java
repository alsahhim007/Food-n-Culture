package com.dalhousie.server.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class UserTest {

    @Test
    void testUserObject() {
        
        User user = new User();
        user.setId(1);
        user.setUserName("testuser");
        user.setEmail("test@gmail.com");

        assertEquals(1, user.getId());
        assertEquals("testuser", user.getUserName());
        assertEquals("test@gmail.com", user.getEmail());
    }

    @Test
    void testUserDetails() {
        User user = new User();
        user.setId(1);
        user.setFirstName("test");
        user.setLastName("last");
        user.setVerified(false);
        user.setStatus("created");

        assertEquals(1, user.getId());
        assertEquals("test", user.getFirstName());
        assertEquals("last", user.getLastName());
        assertEquals("created", user.getStatus());
        assertEquals(false, user.isVerified());
    }

}
