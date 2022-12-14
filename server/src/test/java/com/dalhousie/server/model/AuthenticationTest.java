package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AuthenticationTest {
    @Test
    void getValidIdTest() {
        Authentication authentication = new Authentication();
        authentication.setId(1);
        assertEquals(1, authentication.getId());
    }

    @Test
    void getInvalidIdTest() {
        Authentication authentication = new Authentication();
        assertEquals(0, authentication.getId());
    }

    @Test
    void getValidUserIdTest() {
        Authentication authentication = new Authentication();
        authentication.setUserId(1);
        assertEquals(1, authentication.getUserId());
    }

    @Test
    void getInvalidUserIdTest() {
        Authentication authentication = new Authentication();
        assertEquals(0, authentication.getUserId());
    }

    @Test
    void getValidOtpTest() {
        Authentication authentication = new Authentication();
        authentication.setOtp("4321");
        assertEquals("4321", authentication.getOtp());
    }

    @Test
    void getInvalidOtpTest() {
        Authentication authentication = new Authentication();
        assertNull(authentication.getOtp());
    }

    @Test
    void getValidExpiredTest() {
        Authentication authentication = new Authentication();
        authentication.setExpired(true);
        assertTrue(authentication.isExpired());
    }

    @Test
    void getInvalidExpiredTest() {
        Authentication authentication = new Authentication();
        assertFalse(authentication.isExpired());
    }

    @Test
    void getValidCreatedAtTest() {
        Authentication authentication = new Authentication();
        authentication.setCreatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", authentication.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        Authentication authentication = new Authentication();
        assertNull(authentication.getCreatedAt());
    }
}
