package com.dalhousie.server.controller;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.enums.EventType;
import com.dalhousie.server.model.Event;
import com.dalhousie.server.model.EventMember;
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
public class MemberControllerTest extends AbstractTest {
    
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }
    
    private EventMember getMember() {
        EventMember member = new EventMember();
        member.setStatus("active");
        member.setUpdatedAt("2022-10-11 00:00:00");
        member.setCreatedAt("2022-10-11 00:00:00");
        return member;
    }

    @Test
    @Order(1)
    public void addMemberTest() throws Exception {
        String uri = "/api/events/";
        Event event = new Event();
        event.setId(201);
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
        user.setId(201);

        inputJson = super.mapToJson(user);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User created successfully", content);

        uri = "/api/members/";
        EventMember member = getMember();
        member.setId(201);
        member.setEventId(201);
        member.setUserId(201);
        inputJson = super.mapToJson(member);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member created successfully", content);
    }

    @Test
    @Order(2)
    public void getAllMembersTest() throws Exception {
        String uri = "/api/members/";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        EventMember[] memberlist = super.mapFromJson(content, EventMember[].class);
        assertTrue(memberlist.length >= 0);
    }

    @Test
    @Order(3)
    public void getMemberTest() throws Exception {
        String uri = "/api/events/";
        Event event = new Event();
        event.setId(202);
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
        user.setId(202);

        inputJson = super.mapToJson(user);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User created successfully", content);

        uri = "/api/members/";
        EventMember member = getMember();
        member.setId(202);
        member.setEventId(202);
        member.setUserId(202);
        inputJson = super.mapToJson(member);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member created successfully", content);

        uri = "/api/members/202";
        result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(4)
    public void getMemberNotFoundTest() throws Exception {
        String uri = "/api/members/999";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(5)
    public void updateMemberTest() throws Exception {
        String uri = "/api/events/";
        Event event = new Event();
        event.setId(203);
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
        user.setId(203);

        inputJson = super.mapToJson(user);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User created successfully", content);

        uri = "/api/members/";
        EventMember member = getMember();
        member.setId(203);
        member.setEventId(203);
        member.setUserId(203);
        inputJson = super.mapToJson(member);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member created successfully", content);

        uri = "/api/members/203";
        EventMember member1 = getMember();
        member1.setId(203);
        member1.setEventId(203);
        member1.setUserId(203);
        member1.setStatus("updatedStatus");
        inputJson = super.mapToJson(member1);
        result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member updated successfully", content);
    }

    @Test
    @Order(6)
    public void updateMemberNotFoundTest() throws Exception {
        String uri = "/api/members/999";
        EventMember member = getMember();
        String inputJson = super.mapToJson(member);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(7)
    public void deleteMemberTest() throws Exception {
        String uri = "/api/members/201";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Member deleted successfully", content);

        String deleteUri = "/api/members/202";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member deleted successfully", content);

        deleteUri = "/api/members/203";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Member deleted successfully", content);

        deleteUri = "/api/users/201";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User deleted successfully", content);

        deleteUri = "/api/users/202";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User deleted successfully", content);

        deleteUri = "/api/users/203";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User deleted successfully", content);

        deleteUri = "/api/events/201";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);

        deleteUri = "/api/events/202";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);

        deleteUri = "/api/events/203";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Event deleted successfully", content);
    }
}
