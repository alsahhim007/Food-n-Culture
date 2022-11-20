package com.dalhousie.server.controller;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.enums.EventType;
import com.dalhousie.server.model.Event;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class EventControllerTest extends AbstractTest {
    
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private Event getEvent() {
        Event event = new Event();
        event.setTitle("Test Title");
        event.setEventType(EventType.INDIVIDUAL);
        event.setDescription("test description");
        event.setStatus("created");
        event.setStartDateTime("2022-11-18 00:00:00");
        event.setEndDateTime("2022-11-18 10:00:00");
        event.setVenue("Halifax");
        event.setMaxCapacity(10);
        event.setUpdatedAt("2022-10-11 00:00:00");
        event.setCreatedAt("2022-10-11 00:00:00");
        return event;
    }

    @Test
    @Order(1)
    public void createEventTest() throws Exception {
        String uri = "/api/events/";
        Event event = getEvent();
        event.setId(101);
        String inputJson = super.mapToJson(event);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Event created successfully", content);
    }

    @Test
    @Order(2)
    public void getAllEventsTest() throws Exception {
        String uri = "/api/events/";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        Event[] eventslist = super.mapFromJson(content, Event[].class);
        assertTrue(eventslist.length >= 0);
    }

    @Test
    @Order(3)
    public void getEventsTest() throws Exception {
        String uri = "/api/events/";
        Event event = getEvent();
        event.setId(103);
        String inputJson = super.mapToJson(event);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Event created successfully", content);

        uri = "/api/events/103";
        result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(4)
    public void getEventsNotFoundTest() throws Exception {
        String uri = "/api/events/999";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(5)
    public void updateEventTest() throws Exception {
        String createUri = "/api/events/";
        Event createEvent = getEvent();
        createEvent.setId(102);
        String inputCreateJson = super.mapToJson(createEvent);
        MvcResult createResult = mvc.perform(MockMvcRequestBuilders.post(createUri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputCreateJson)).andReturn();
        int createStatus = createResult.getResponse().getStatus();
        assertEquals(201, createStatus);

        String uri = "/api/events/102";
        Event events = getEvent();
        String inputJson = super.mapToJson(events);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Event updated successfully", content);
    }

    @Test
    @Order(6)
    public void updateEventsNotFoundTest() throws Exception {
        String uri = "/api/events/999";
        Event events = getEvent();
        String inputJson = super.mapToJson(events);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(7)
    public void deleteEventsTest() throws Exception {
        String uri = "/api/events/101";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);

        String deleteUri = "/api/events/102";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);

        deleteUri = "/api/events/103";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);
    }
    
}
