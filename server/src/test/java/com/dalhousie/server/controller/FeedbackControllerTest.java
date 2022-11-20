package com.dalhousie.server.controller;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.enums.EventType;
import com.dalhousie.server.model.Event;
import com.dalhousie.server.model.EventMember;
import com.dalhousie.server.model.Feedback;
import com.dalhousie.server.model.User;

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
public class FeedbackControllerTest extends AbstractTest {
    
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }
    
    private Feedback getFeedback() {
        Feedback feedback = new Feedback();
        feedback.setComment("test comment");
        feedback.setStars("5");
        feedback.setUpdatedAt("2022-10-11 00:00:00");
        feedback.setCreatedAt("2022-10-11 00:00:00");
        return feedback;
    }

    @Test
    @Order(1)
    public void createFeedbackTest() throws Exception {
        String uri = "/api/events/";
        Event event = new Event();
        event.setId(301);
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

        String inputJson = super.mapToJson(event);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Event created successfully", content);

        uri = "/api/users/";
        User user = new User();
        user.setUserName(faker.name().username());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setVerified(false);
        user.setStatus("created");
        user.setUpdatedAt("2022-11-17 00:00:00");
        user.setCreatedAt("2022-11-17 00:00:00");
        user.setId(301);

        inputJson = super.mapToJson(user);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User created successfully", content);

        uri = "/api/members/";
        EventMember member = new EventMember();
        member.setStatus("active");
        member.setUpdatedAt("2022-10-11 00:00:00");
        member.setCreatedAt("2022-10-11 00:00:00");
        member.setId(301);
        member.setEventId(301);
        member.setUserId(301);
        inputJson = super.mapToJson(member);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member created successfully", content);
        
        uri = "/api/feedbacks/";
        Feedback feedback = getFeedback();
        feedback.setId(301);
        feedback.setMemberId(301);
        inputJson = super.mapToJson(feedback);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Feedback created successfully", content);
    }

    @Test
    @Order(2)
    public void getAllFeedbackTest() throws Exception {
        String uri = "/api/feedbacks/";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        Feedback[] feedbacklist = super.mapFromJson(content, Feedback[].class);
        assertTrue(feedbacklist.length >= 0);
    }

    @Test
    @Order(3)
    public void getFeedbackTest() throws Exception {
        String uri = "/api/events/";
        Event event = new Event();
        event.setId(302);
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

        String inputJson = super.mapToJson(event);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Event created successfully", content);

        uri = "/api/users/";
        User user = new User();
        user.setUserName(faker.name().username());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setVerified(false);
        user.setStatus("created");
        user.setUpdatedAt("2022-11-17 00:00:00");
        user.setCreatedAt("2022-11-17 00:00:00");
        user.setId(302);

        inputJson = super.mapToJson(user);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User created successfully", content);

        uri = "/api/members/";
        EventMember member = new EventMember();
        member.setStatus("active");
        member.setUpdatedAt("2022-10-11 00:00:00");
        member.setCreatedAt("2022-10-11 00:00:00");
        member.setId(302);
        member.setEventId(302);
        member.setUserId(302);
        inputJson = super.mapToJson(member);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member created successfully", content);
        
        uri = "/api/feedbacks/";
        Feedback feedback = getFeedback();
        feedback.setId(302);
        feedback.setMemberId(302);
        inputJson = super.mapToJson(feedback);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Feedback created successfully", content);

        uri = "/api/feedbacks/302";
        result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(4)
    public void geFeedbackNotFoundTest() throws Exception {
        String uri = "/api/feedbacks/999";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(5)
    public void updateFeedbackTest() throws Exception {
        String uri = "/api/events/";
        Event event = new Event();
        event.setId(303);
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

        String inputJson = super.mapToJson(event);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Event created successfully", content);

        uri = "/api/users/";
        User user = new User();
        user.setUserName(faker.name().username());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setVerified(false);
        user.setStatus("created");
        user.setUpdatedAt("2022-11-17 00:00:00");
        user.setCreatedAt("2022-11-17 00:00:00");
        user.setId(303);

        inputJson = super.mapToJson(user);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User created successfully", content);

        uri = "/api/members/";
        EventMember member = new EventMember();
        member.setStatus("active");
        member.setUpdatedAt("2022-10-11 00:00:00");
        member.setCreatedAt("2022-10-11 00:00:00");
        member.setId(303);
        member.setEventId(303);
        member.setUserId(303);
        inputJson = super.mapToJson(member);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member created successfully", content);
        
        uri = "/api/feedbacks/";
        Feedback feedback = getFeedback();
        feedback.setId(303);
        feedback.setMemberId(303);
        inputJson = super.mapToJson(feedback);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Feedback created successfully", content);

        uri = "/api/feedbacks/303";
        feedback = getFeedback();
        feedback.setId(303);
        feedback.setMemberId(303);
        inputJson = super.mapToJson(feedback);
        result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Feedback updated successfully", content);
    }

    @Test
    @Order(6)
    public void updateFeedbackNotFoundTest() throws Exception {
        String uri = "/api/feedbacks/999";
        Feedback feedback = getFeedback();
        feedback.setId(999);
        feedback.setMemberId(303);
        String inputJson = super.mapToJson(feedback);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(7)
    public void deleteFeedbackTest() throws Exception {
        String uri = "/api/feedbacks/301";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Feedback deleted successfully", content);

        String deleteUri = "/api/feedbacks/302";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Feedback deleted successfully", content);

        deleteUri = "/api/feedbacks/303";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Feedback deleted successfully", content);

        uri = "/api/members/301";
        result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member deleted successfully", content);

        deleteUri = "/api/members/302";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member deleted successfully", content);

        deleteUri = "/api/members/303";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member deleted successfully", content);

        deleteUri = "/api/events/301";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);

        deleteUri = "/api/events/302";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);

        deleteUri = "/api/events/303";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);

        deleteUri = "/api/users/301";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User deleted successfully", content);

        deleteUri = "/api/users/302";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User deleted successfully", content);

        deleteUri = "/api/users/303";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User deleted successfully", content);
    }
}
