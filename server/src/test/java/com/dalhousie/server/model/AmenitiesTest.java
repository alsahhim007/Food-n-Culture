package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class AmenitiesTest {
    @Test
    void getValidIdTest() {
        Amenities amenities = new Amenities();
        amenities.setId(1);
        assertEquals(1, amenities.getId());
    }

    @Test
    void getInvalidIdTest() {
        Amenities amenities = new Amenities();
        assertEquals(0, amenities.getId());
    }

    @Test
    void getValidNameTest() {
        Amenities amenities = new Amenities();
        amenities.setName("Test");
        assertEquals("Test", amenities.getName());
    }

    @Test
    void getInvalidNameTest() {
        Amenities amenities = new Amenities();
        assertNull(amenities.getName());
    }

    @Test
    void getValidCategoryTest() {
        Amenities amenities = new Amenities();
        amenities.setCategory("Utensils");
        assertEquals("Utensils", amenities.getCategory());
    }

    @Test
    void getInvalidCategoryTest() {
        Amenities amenities = new Amenities();
        assertNull(amenities.getCategory());
    }

    @Test
    void getValidUpdatedAtTest() {
        Amenities amenities = new Amenities();
        amenities.setUpdatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", amenities.getUpdatedAt());
    }

    @Test
    void getInvalidUpdatedAtTest() {
        Amenities amenities = new Amenities();
        assertNull(amenities.getUpdatedAt());
    }

    @Test
    void getValidCreatedAtTest() {
        Amenities amenities = new Amenities();
        amenities.setCreatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", amenities.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        Amenities amenities = new Amenities();
        assertNull(amenities.getCreatedAt());
    }
}
