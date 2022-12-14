package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class MessagesTest {
    @Test
    void getValidIdTest() {
        Messages messages = new Messages();
        messages.setId(1);
        assertEquals(1, messages.getId());
    }

    @Test
    void getInvalidIdTest() {
        Messages messages = new Messages();
        assertEquals(0, messages.getId());
    }

    @Test
    void getValiUserIdTest() {
        Messages messages = new Messages();
        messages.setUserId(1);
        assertEquals(1, messages.getUserId());
    }

    @Test
    void getInvalidUserIdTest() {
        Messages messages = new Messages();
        assertEquals(0, messages.getUserId());
    }

    @Test
    void getValidTargetUserIdTest() {
        Messages messages = new Messages();
        messages.setTargetUserId(1);
        assertEquals(1, messages.getTargetUserId());
    }

    @Test
    void getInvalidTargetUserIdTest() {
        Messages messages = new Messages();
        assertEquals(0, messages.getTargetUserId());
    }

    @Test
    void getValidContentTest() {
        Messages messages = new Messages();
        messages.setContent("Something to write");
        assertEquals("Something to write", messages.getContent());
    }

    @Test
    void getInvalidContentTest() {
        Messages messages = new Messages();
        assertNull(messages.getContent());
    }


    @Test
    void getValidIsReadTest() {
        Messages messages = new Messages();
        messages.setRead(true);
        assertTrue(messages.isRead());
    }

    @Test
    void getInvalidIsReadTest() {
        Messages messages = new Messages();
        assertFalse(messages.isRead());
    }

    @Test
    void getValidUpdatedAtTest() {
        Messages messages = new Messages();
        messages.setUpdatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", messages.getUpdatedAt());
    }

    @Test
    void getInvalidUpdatedAtTest() {
        Messages messages = new Messages();
        assertNull(messages.getUpdatedAt());
    }

    @Test
    void getValidCreatedAtTest() {
        Messages messages = new Messages();
        messages.setCreatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", messages.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        Messages messages = new Messages();
        assertNull(messages.getCreatedAt());
    }

}
