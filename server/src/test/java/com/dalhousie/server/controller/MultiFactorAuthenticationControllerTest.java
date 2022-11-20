package com.dalhousie.server.controller;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.model.Authentication;
import com.dalhousie.server.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ActiveProfiles("test")
public class MultiFactorAuthenticationControllerTest extends AbstractTest {
    
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }
    
    private Authentication getAuthDetails() {
        Authentication authentication = new Authentication();
        authentication.setOtp("1234");
        authentication.setExpired(false);
        authentication.setCreatedAt("2022-11-17 00:00:00");
        return authentication;
    }

    @Test
    @Order(1)
    public void createAuthTest() throws Exception {
        String uri = "/api/users/";
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
        user.setId(1000);

        String inputJson = super.mapToJson(user);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("User created successfully", content);
        
        uri = "/api/authentication/";
        Authentication auth = getAuthDetails();
        auth.setId(1000);
        auth.setUserId(1000);
        inputJson = super.mapToJson(auth);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Authentication created successfully", content);
    }

    @Test
    @Order(2)
    public void getAllAuthTest() throws Exception {
        String uri = "/api/authentication/";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        Authentication[] authlist = super.mapFromJson(content, Authentication[].class);
        assertTrue(authlist.length >= 0);
    }

    @Test
    @Order(3)
    public void getAuthTest() throws Exception {
        String uri = "/api/authentication/1000";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(4)
    public void getAuthNotFoundTest() throws Exception {
        String uri = "/api/authentication/999";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(5)
    public void updateAuthTest() throws Exception {
        String uri = "/api/users/";
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
        user.setId(1001);

        String inputJson = super.mapToJson(user);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("User created successfully", content);
        
        uri = "/api/authentication/";
        Authentication auth = getAuthDetails();
        auth.setId(1001);
        auth.setUserId(1001);
        inputJson = super.mapToJson(auth);
        result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(201, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Authentication created successfully", content);

        uri = "/api/authentication/1001";
        auth = getAuthDetails();
        auth.setId(1001);
        auth.setUserId(1001);
        inputJson = super.mapToJson(auth);
        result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Authentication updated successfully", content);
    }

    @Test
    @Order(6)
    public void updateAuthWhenNotFoundTest() throws Exception {
        String uri = "/api/authentication/999";
        Authentication auth = getAuthDetails();
        String inputJson = super.mapToJson(auth);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(7)
    @AfterTestClass
    public void deleteUserTest() throws Exception {
        String uri = "/api/authentication/1000";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Authentication deleted successfully", content);

        String deleteUri = "/api/authentication/1001";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Authentication deleted successfully", content);

        uri = "/api/users/1000";
        result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User deleted successfully", content);

        uri = "/api/users/1001";
        result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("User deleted successfully", content);
    }
}
