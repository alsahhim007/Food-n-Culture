package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class EventMemberTest {
    @Test
    void getValidIdTest() {
        EventMember eventMember = new EventMember();
        eventMember.setId(1);
        assertEquals(1, eventMember.getId());
    }

    @Test
    void getInvalidIdTest() {
        EventMember eventMember = new EventMember();
        assertEquals(0, eventMember.getId());
    }

    @Test
    void getValidEventIdTest() {
        EventMember eventMember = new EventMember();
        eventMember.setEventId(1);
        assertEquals(1, eventMember.getEventId());
    }

    @Test
    void getInvalidEventIdTest() {
        EventMember eventMember = new EventMember();
        assertEquals(0, eventMember.getEventId());
    }

    @Test
    void getValidUserIdTest() {
        EventMember eventMember = new EventMember();
        eventMember.setUserId(1);
        assertEquals(1, eventMember.getUserId());
    }

    @Test
    void getInvalidUserIdTest() {
        EventMember eventMember = new EventMember();
        assertEquals(0, eventMember.getUserId());
    }

    @Test
    void getValidStatusTest() {
        EventMember eventMember = new EventMember();
        eventMember.setStatus("Registered");
        assertEquals("Registered", eventMember.getStatus());
    }

    @Test
    void getInvalidStatusTest() {
        EventMember eventMember = new EventMember();
        assertNull(eventMember.getStatus());
    }

    @Test
    void getValidUpdatedAtTest() {
        EventMember eventMember = new EventMember();
        eventMember.setUpdatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", eventMember.getUpdatedAt());
    }

    @Test
    void getInvalidUpdatedAtTest() {
        EventMember eventMember = new EventMember();
        assertNull(eventMember.getUpdatedAt());
    }

    @Test
    void getValidCreatedAtTest() {
        EventMember eventMember = new EventMember();
        eventMember.setCreatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", eventMember.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        EventMember eventMember = new EventMember();
        assertNull(eventMember.getCreatedAt());
    }
}
