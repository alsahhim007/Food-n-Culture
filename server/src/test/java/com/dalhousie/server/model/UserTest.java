package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
public class UserTest {

    @Test
    void getValidIdTest() {
        User user = new User();
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    void getInvalidIdTest() {
        User user = new User();
        assertEquals(0, user.getId());
    }

    @Test
    void getValidUserNameTest() {
        User user = new User();
        user.setUserName("test123");
        assertEquals("test123", user.getUserName());
    }

    @Test
    void getInvalidUserNameTest() {
        User user = new User();
        assertNull(user.getUserName());
    }

    @Test
    void getValidEmailTest() {
        User user = new User();
        user.setEmail("test@test.ca");
        assertEquals("test@test.ca", user.getEmail());
    }

    @Test
    void getInvalidEmailTest() {
        User user = new User();
        assertNull(user.getEmail());
    }

    @Test
    void getValidPasswordTest() {
        User user = new User();
        user.setPassword("Test@123");
        assertEquals("Test@123", user.getPassword());
    }

    @Test
    void getInvalidPasswordTest() {
        User user = new User();
        assertNull(user.getPassword());
    }

    @Test
    void getValidFirstNameTest() {
        User user = new User();
        user.setFirstName("Test");
        assertEquals("Test", user.getFirstName());
    }

    @Test
    void getInvalidFirstNameTest() {
        User user = new User();
        assertNull(user.getFirstName());
    }

    @Test
    void getValidLastNameTest() {
        User user = new User();
        user.setLastName("Test");
        assertEquals("Test", user.getLastName());
    }

    @Test
    void getInvalidLastNameTest() {
        User user = new User();
        assertNull(user.getLastName());
    }

    @Test
    void getValidIsVerifiedTest() {
        User user = new User();
        user.setVerified(true);
        assertTrue(user.isVerified());
    }

    @Test
    void getInvalidIsVerifiedTest() {
        User user = new User();
        assertFalse(user.isVerified());
    }

    @Test
    void getValidStatusTest() {
        User user = new User();
        user.setStatus("Registered");
        assertEquals("Registered", user.getStatus());
    }

    @Test
    void getInvalidStatusTest() {
        User user = new User();
        assertNull(user.getStatus());
    }

    @Test
    void getValidUpdatedAtTest() {
        User user = new User();
        user.setUpdatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", user.getUpdatedAt());
    }

    @Test
    void getInvalidUpdatedAtTest() {
        User user = new User();
        assertNull(user.getUpdatedAt());
    }

    @Test
    void getValidCreatedAtTest() {
        User user = new User();
        user.setCreatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", user.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        User user = new User();
        assertNull(user.getCreatedAt());
    }
}
