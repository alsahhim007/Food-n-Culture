package com.dalhousie.server.model;

import com.dalhousie.server.enums.EventType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class EventTest {
    @Test
    void getValidIdTest() {
        Event event = new Event();
        event.setId(1);
        assertEquals(1, event.getId());
    }

    @Test
    void getInvalidIdTest() {
        Event event = new Event();
        assertEquals(0, event.getId());
    }
    @Test
    void getValidTitleTest() {
        Event event = new Event();
        event.setTitle("Test");
        assertEquals("Test", event.getTitle());
    }

    @Test
    void getInvalidTitleTest() {
        Event event = new Event();
        assertNull( event.getTitle());
    }

    @Test
    void getValidDescriptionTest() {
        Event event = new Event();
        event.setDescription("Test description");
        assertEquals("Test description", event.getDescription());
    }

    @Test
    void getInvalidDescriptionTest() {
        Event event = new Event();
        assertNull(event.getDescription());
    }

    @Test
    void getValidEventTypeTest() {
        Event event = new Event();
        event.setEventType(EventType.INDIVIDUAL);
        assertEquals(EventType.INDIVIDUAL, event.getEventType());
    }

    @Test
    void getInvalidEventTypeTest() {
        Event event = new Event();
        assertNull(event.getEventType());
    }

    @Test
    void getValidStatusTest() {
        Event event = new Event();
        event.setStatus("Ongoing");
        assertEquals("Ongoing", event.getStatus());
    }

    @Test
    void getInvalidStatusTest() {
        Event event = new Event();
        assertNull(event.getStatus());
    }

    @Test
    void getValidStartDatetimeTest() {
        Event event = new Event();
        event.setStartDatetime("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", event.getStartDatetime());
    }

    @Test
    void getInvalidStartDatetimeTest() {
        Event event = new Event();
        assertNull(event.getStartDatetime());
    }

    @Test
    void getValidEndDatetimeTest() {
        Event event = new Event();
        event.setEndDatetime("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", event.getEndDatetime());
    }

    @Test
    void getInvalidEndDatetimeTest() {
        Event event = new Event();
        assertNull(event.getEndDatetime());
    }

    @Test
    void getValidVenueTest() {
        Event event = new Event();
        event.setVenue("Halifax");
        assertEquals("Halifax", event.getVenue());
    }

    @Test
    void getInvalidVenueTest() {
        Event event = new Event();
        assertNull(event.getVenue());
    }

    @Test
    void getValidMaxCapacityTest() {
        Event event = new Event();
        event.setMaxCapacity(20);
        assertEquals(20, event.getMaxCapacity());
    }

    @Test
    void getInvalidMaxCapacityTest() {
        Event event = new Event();
        assertEquals(0, event.getMaxCapacity());
    }

    @Test
    void getValidUpdatedAtTest() {
        Event event = new Event();
        event.setUpdatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", event.getUpdatedAt());
    }

    @Test
    void getInvalidUpdatedAtTest() {
        Event event = new Event();
        assertNull(event.getUpdatedAt());
    }

    @Test
    void getValidCreatedAtTest() {
        Event event = new Event();
        event.setCreatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", event.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        Event event = new Event();
        assertNull(event.getCreatedAt());
    }
}
