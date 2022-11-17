package com.dalhousie.server.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.model.User;

@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private User getUser() {
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
        return user;
    }

    @Test
    public void getAllUsersTest() throws Exception {
        String uri = "/api/users/";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        User[] userlist = super.mapFromJson(content, User[].class);
        assertTrue(userlist.length >= 0);
    }

    @Test
    public void createUser() throws Exception {
        String uri = "/api/users/";
        User user = getUser();
        String inputJson = super.mapToJson(user);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("User created successfully", content);
    }

    @Test
    public void updateUser() throws Exception {
        String uri = "/api/users/5";
        User user = getUser();
        String inputJson = super.mapToJson(user);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("User updated successfully", content);
    }

    @Test
    public void deleteUser() throws Exception {
        String uri = "/api/users/1";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("User deleted successfully", content);
    }

}
