package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class VenuesTest {
    @Test
    void getValidIdTest() {
        Venues venues = new Venues();
        venues.setId(1);
        assertEquals(1, venues.getId());
    }

    @Test
    void getInvalidIdTest() {
        Venues venues = new Venues();
        assertEquals(0, venues.getId());
    }

    @Test
    void getValidUserIdTest() {
        Venues venues = new Venues();
        venues.setUserId(1);
        assertEquals(1, venues.getUserId());
    }

    @Test
    void getInvalidUserIdTest() {
        Venues venues = new Venues();
        assertEquals(0,venues.getUserId());
    }

    @Test
    void getValidNameTest() {
        Venues venues = new Venues();
        venues.setName("Halifax");
        assertEquals("Halifax", venues.getName());
    }

    @Test
    void getInvalidNameTest() {
        Venues venues = new Venues();
        assertNull(venues.getName());
    }

    @Test
    void getValidAddressLine1Test() {
        Venues venues = new Venues();
        venues.setAddressLine1("Halifax");
        assertEquals("Halifax", venues.getAddressLine1());
    }

    @Test
    void getInvalidAddressLine1Test() {
        Venues venues = new Venues();
        assertNull(venues.getAddressLine1());
    }


    @Test
    void getValidAddressLine2Test() {
        Venues venues = new Venues();
        venues.setAddressLine2("Nova Scotia");
        assertEquals("Nova Scotia", venues.getAddressLine2());
    }

    @Test
    void getInvalidAddressLine2Test() {
        Venues venues = new Venues();
        assertNull(venues.getAddressLine2());
    }

    @Test
    void getValidStatusTest() {
        Venues venues = new Venues();
        venues.setStatus("Created");
        assertEquals("Created", venues.getStatus());
    }

    @Test
    void getInvalidStatusTest() {
        Venues venues = new Venues();
        assertNull(venues.getStatus());
    }

    @Test
    void getValidUpdatedAtTest() {
        Venues venues = new Venues();
        venues.setUpdatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", venues.getUpdatedAt());
    }

    @Test
    void getInvalidUpdatedAtTest() {
        Venues venues = new Venues();
        assertNull(venues.getUpdatedAt());
    }

    @Test
    void getValidCreatedAtTest() {
        Venues venues = new Venues();
        venues.setCreatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", venues.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        Venues venues = new Venues();
        assertNull(venues.getCreatedAt());
    }
}
