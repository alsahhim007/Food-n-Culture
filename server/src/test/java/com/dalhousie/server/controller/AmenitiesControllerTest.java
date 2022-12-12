package com.dalhousie.server.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.model.Amenities;

import org.springframework.http.MediaType;

@SpringBootTest
@ActiveProfiles("test")
public class AmenitiesControllerTest extends AbstractTest {
    
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private Amenities getAmenities() {
        Amenities amenities = new Amenities();
        amenities.setName(faker.name().firstName());
        amenities.setCategory("xyz");
        amenities.setUpdatedAt("2022-10-11 00:00:00");
        amenities.setCreatedAt("2022-10-11 00:00:00");
        return amenities;
    }

    @Test
    @Order(1)
    public void createAmenitiesTest() throws Exception {
        String uri = "/api/amenities/";
        Amenities amenities = getAmenities();
        amenities.setId(99);
        String inputJson = super.mapToJson(amenities);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Amenities created successfully", content);
    }

    @Test
    @Order(2)
    public void getAllAmenitiesTest() throws Exception {
        String uri = "/api/amenities/";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        Amenities[] amenitieslist = super.mapFromJson(content, Amenities[].class);
        assertTrue(amenitieslist.length >= 0);
    }

    @Test
    @Order(3)
    public void getAmenitiesTest() throws Exception {
        String uri = "/api/amenities/99";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(4)
    public void getAmenitiesNotFoundTest() throws Exception {
        String uri = "/api/amenities/999";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(5)
    public void updateAmenitiesTest() throws Exception {
        String createUri = "/api/amenities/";
        Amenities createAmenities = getAmenities();
        createAmenities.setId(88);
        String inputCreateJson = super.mapToJson(createAmenities);
        MvcResult createResult = mvc.perform(MockMvcRequestBuilders.post(createUri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputCreateJson)).andReturn();
        int createStatus = createResult.getResponse().getStatus();
        assertEquals(201, createStatus);

        String uri = "/api/amenities/88";
        Amenities amenities = getAmenities();
        amenities.setId(88);
        String inputJson = super.mapToJson(amenities);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Amenities updated successfully", content);
    }

    @Test
    @Order(6)
    public void updateAmenitiesNotFoundTest() throws Exception {
        String uri = "/api/amenities/999";
        Amenities amenities = getAmenities();
        String inputJson = super.mapToJson(amenities);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    @Order(7)
    public void deleteAmenitiesTest() throws Exception {
        String uri = "/api/amenities/99";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("Amenities deleted successfully", content);

        String deleteUri = "/api/amenities/88";
        result = mvc.perform(MockMvcRequestBuilders.delete(deleteUri)).andReturn();
        status = result.getResponse().getStatus();
        assertEquals(200, status);
        content = result.getResponse().getContentAsString();
        assertEquals("Amenities deleted successfully", content);
    }
    
}
