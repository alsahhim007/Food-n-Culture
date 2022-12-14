package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class FriendsTest {
    @Test
    void getValidIdTest() {
        Friends friends = new Friends();
        friends.setId(1);
        assertEquals(1, friends.getId());
    }

    @Test
    void getInvalidIdTest() {
        Friends friends = new Friends();
        assertEquals(0, friends.getId());
    }

    @Test
    void getValidUserIdTest() {
        Friends friends = new Friends();
        friends.setUserId(1);
        assertEquals(1, friends.getUserId());
    }

    @Test
    void getInvalidUserIdTest() {
        Friends friends = new Friends();
        assertEquals(0, friends.getUserId());
    }

    @Test
    void getValidTargetUserIdTest() {
        Friends friends = new Friends();
        friends.setTargetUserId(1);
        assertEquals(1, friends.getTargetUserId());
    }

    @Test
    void getInvalidTargetUserIdTest() {
        Friends friends = new Friends();
        assertEquals(0, friends.getTargetUserId());
    }

    @Test
    void getValidUpdatedAtTest() {
        Friends friends = new Friends();
        friends.setUpdatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", friends.getUpdatedAt());
    }

    @Test
    void getInvalidUpdatedAtTest() {
        Friends friends = new Friends();
        assertNull(friends.getUpdatedAt());
    }

    @Test
    void getValidCreatedAtTest() {
        Friends friends = new Friends();
        friends.setCreatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", friends.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        Friends friends = new Friends();
        assertNull(friends.getCreatedAt());
    }
}
