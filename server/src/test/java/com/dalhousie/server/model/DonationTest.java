package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class DonationTest {
    @Test
    void getValidIdTest() {
        Donation donation = new Donation();
        donation.setId(1);
        assertEquals(1, donation.getId());
    }

    @Test
    void getInvalidIdTest() {
        Donation donation = new Donation();
        assertEquals(0, donation.getId());
    }

    @Test
    void getValidEventIdTest() {
        Donation donation = new Donation();
        donation.setEventId(1);
        assertEquals(1, donation.getEventId());
    }

    @Test
    void getInvalidEventIdTest() {
        Donation donation = new Donation();
        assertEquals(0, donation.getEventId());
    }

    @Test
    void getValidNameTest() {
        Donation donation = new Donation();
        donation.setName("Test");
        assertEquals("Test", donation.getName());
    }

    @Test
    void getInvalidNameTest() {
        Donation donation = new Donation();
        assertNull(donation.getName());
    }

    @Test
    void getValidAmountTest() {
        Donation donation = new Donation();
        donation.setAmount(10);
        assertEquals(10, donation.getAmount());
    }

    @Test
    void getInvalidAmountTest() {
        Donation donation = new Donation();
        assertEquals(0, donation.getAmount());
    }

    @Test
    void getValidEmailTest() {
        Donation donation = new Donation();
        donation.setEmail("Test@test.ca");
        assertEquals("Test@test.ca", donation.getEmail());
    }

    @Test
    void getInvalidEmailTest() {
        Donation donation = new Donation();
        assertNull(donation.getEmail());
    }

    @Test
    void getValidNoteTest() {
        Donation donation = new Donation();
        donation.setNote("Something to write");
        assertEquals("Something to write", donation.getNote());
    }

    @Test
    void getInvalidNoteTest() {
        Donation donation = new Donation();
        assertNull(donation.getNote());
    }

    @Test
    void getValidUpdatedAtTest() {
        Donation donation = new Donation();
        donation.setUpdatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", donation.getUpdatedAt());
    }

    @Test
    void getInvalidUpdatedAtTest() {
        Donation donation = new Donation();
        assertNull(donation.getUpdatedAt());
    }

    @Test
    void getValidCreatedAtTest() {
        Donation donation = new Donation();
        donation.setCreatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", donation.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        Donation donation = new Donation();
        assertNull(donation.getCreatedAt());
    }

}
