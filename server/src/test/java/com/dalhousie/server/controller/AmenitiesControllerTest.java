package com.dalhousie.server.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.model.Amenities;
import com.dalhousie.server.persistence.AmenitiesRepository;

import org.springframework.http.MediaType;

@SpringBootTest
@ActiveProfiles("test")
public class AmenitiesControllerTest extends AbstractTest {
    
    @MockBean
    AmenitiesRepository amenitiesRepository;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private Amenities getAmenities() {
        Amenities amenities = new Amenities();
        amenities.setName("Stove");
        amenities.setCategory("Utensil");
        amenities.setUpdatedAt("2022-10-11 00:00:00");
        amenities.setCreatedAt("2022-10-11 00:00:00");
        return amenities;
    }

    @Test
    void createAmenitiesTest() throws Exception {
        Mockito.doReturn(1).when(amenitiesRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/amenities/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getAmenities())))
            .andExpect(status().isCreated())
            .andExpect(content().string("Amenities created successfully"));
    }

    @Test
    void createAmenitiesFailedTest() throws Exception {
        Mockito.when(amenitiesRepository.save(getAmenities())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/amenities/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getAmenities())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllAmenitiesTest() throws Exception {
        List<Amenities> amenities = new ArrayList<>();
        amenities.add(getAmenities());
        Mockito.when(amenitiesRepository.findAll()).thenReturn(amenities);

        mvc.perform(MockMvcRequestBuilders.get("/api/amenities/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Stove"));
    }

    @Test
    void getAmenitiesTest() throws Exception {
        Mockito.when(amenitiesRepository.getById(99)).thenReturn(Optional.of(getAmenities()));
        mvc.perform(MockMvcRequestBuilders.get("/api/amenities/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Stove"));
    }

    @Test
    void getAmenitiesNotFoundTest() throws Exception {
        Mockito.when(amenitiesRepository.getById(99)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/amenities/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateAmenitiesTest() throws Exception {
        Mockito.when(amenitiesRepository.getById(99)).thenReturn(Optional.of(getAmenities()));
        Mockito.when(amenitiesRepository.update(getAmenities())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/amenities/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getAmenities())))
            .andExpect(status().isOk())
            .andExpect(content().string("Amenities updated successfully"));
    }

    @Test
    void updateAmenitiesNotFoundTest() throws Exception {
        Mockito.when(amenitiesRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(amenitiesRepository.update(getAmenities())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/amenities/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getAmenities())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteAmenitiesTest() throws Exception {
        Mockito.when(amenitiesRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/amenities/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Amenities deleted successfully"));
    }

    @Test
    void deleteAmenitiesNotFoundTest() throws Exception {
        Mockito.when(amenitiesRepository.deleteById(999)).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.delete("/api/amenities/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllAmenitiesByVenueIdTest() throws Exception {
        List<Amenities> amenities = new ArrayList<>();
        amenities.add(getAmenities());
        
        Mockito.when(amenitiesRepository.getAllAmenitiesByVenueId(99)).thenReturn(amenities);
        mvc.perform(MockMvcRequestBuilders.get("/api/amenities/venues/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Stove"));
    }
    
}
