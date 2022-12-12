package com.dalhousie.server.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.enums.EventType;
import com.dalhousie.server.model.Donation;
import com.dalhousie.server.model.Event;

@SpringBootTest
@ActiveProfiles("test")
public class DonationControllerTest extends AbstractTest {
    
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private Donation getDonation() {
        Donation donation = new Donation();
        donation.setName(faker.name().firstName());
        donation.setAmount(100);
        donation.setEmail(faker.internet().emailAddress());
        donation.setNote("Donate $100.00");
        donation.setUpdatedAt("2022-10-11 00:00:00");
        donation.setCreatedAt("2022-10-11 00:00:00");
        return donation;
    }

    @Test
    @Order(1)
    public void createEventTest() throws Exception{
        String uri = "/api/events/";
        Event event = new Event();
        event.setId(99);
        event.setTitle("Test Title");
        event.setEventType(EventType.INDIVIDUAL);
        event.setDescription("test description");
        event.setStatus("created");
        event.setStartDatetime("2022-11-18 00:00:00");
        event.setEndDatetime("2022-11-18 10:00:00");
        event.setVenue("Halifax");
        event.setMaxCapacity(10);
        event.setUpdatedAt("2022-10-11 00:00:00");
        event.setCreatedAt("2022-10-11 00:00:00");

        String inputJson = super.mapToJson(event);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Event created successfully", content);
    }

    @Test
    @Order(2)
    public void createDonationTest() throws Exception {
        String uri = "/api/donations/";
        Donation donation = getDonation();
        donation.setId(99);
        donation.setEventId(99);
        String inputJson = super.mapToJson(donation);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Donation created successfully", content);
    }

    @Test
    @Order(3)
    public void getAllDonationsTest() throws Exception {
        String uri = "/api/donations/";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        Donation[] donationslist = super.mapFromJson(content, Donation[].class);
        assertTrue(donationslist.length >= 0);
    }

    @Test
    @Order(4)
    public void getDonationTest() throws Exception {
        String uri = "/api/events/";
        Event event = new Event();
        event.setId(100);
        event.setTitle("Test Title");
        event.setEventType(EventType.INDIVIDUAL);
        event.setDescription("test description");
        event.setStatus("created");
        event.setStartDatetime("2022-11-18 00:00:00");
        event.setEndDatetime("2022-11-18 10:00:00");
        event.setVenue("Halifax");
        event.setMaxCapacity(10);
        event.setUpdatedAt("2022-10-11 00:00:00");
        event.setCreatedAt("2022-10-11 00:00:00");

        String inputJson = super.mapToJson(event);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Event created successfully", content);

        uri = "/api/donations/";
        Donation donation = getDonation();
        donation.setId(100);
        donation.setEventId(100);
        inputJson = super.mapToJson(donation);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Donation created successfully", content);

        uri = "/api/donations/100";
        result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(5)
    public void getDonationsNotFoundTest() throws Exception {
        String uri = "/api/donations/999";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(6)
    public void updateDonationsTest() throws Exception {
        String uri = "/api/events/";
        Event event = new Event();
        event.setId(88);
        event.setTitle("Test Title");
        event.setEventType(EventType.INDIVIDUAL);
        event.setDescription("test description");
        event.setStatus("created");
        event.setStartDatetime("2022-11-18 00:00:00");
        event.setEndDatetime("2022-11-18 10:00:00");
        event.setVenue("Halifax");
        event.setMaxCapacity(10);
        event.setUpdatedAt("2022-10-11 00:00:00");
        event.setCreatedAt("2022-10-11 00:00:00");

        String inputJson = super.mapToJson(event);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Event created successfully", content);

        String createUri = "/api/donations/";
        Donation createDonation = getDonation();
        createDonation.setId(88);
        createDonation.setEventId(88);
        String inputCreateJson = super.mapToJson(createDonation);
        MvcResult createResult = mvc.perform(MockMvcRequestBuilders.post(createUri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputCreateJson)).andReturn();
        int createStatus = createResult.getResponse().getStatus();
        assertEquals(201, createStatus);

        uri = "/api/donations/88";
        Donation donation = getDonation();
        donation.setId(88);
        donation.setEventId(88);
        inputJson = super.mapToJson(donation);
        result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Donation updated successfully", content);
    }

    @Test
    @Order(7)
    public void updateDonationsNotFoundTest() throws Exception {
        String uri = "/api/donations/999";
        Donation donation = getDonation();
        donation.setEventId(99);
        String inputJson = super.mapToJson(donation);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(8)
    public void deleteDonationsTest() throws Exception {
        String uri = "/api/donations/99";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Donation deleted successfully", content);

        String deleteUri = "/api/donations/88";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Donation deleted successfully", content);

        deleteUri = "/api/donations/100";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Donation deleted successfully", content);

        String deleteEventUri = "/api/events/88";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteEventUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);

        deleteEventUri = "/api/events/99";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteEventUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);

        deleteEventUri = "/api/events/100";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteEventUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);
    }
    
}
