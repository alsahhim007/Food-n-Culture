package com.dalhousie.server.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.model.User;
import com.dalhousie.server.model.Venues;
import com.dalhousie.server.persistence.VenueRepository;

@SpringBootTest
@ActiveProfiles("test")
public class VenueControllerTest extends AbstractTest {

    @MockBean
    VenueRepository venueRepository;
    
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private Venues getVenue() {
        Venues venues = new Venues();
        venues.setId(1);
        venues.setName("test");
        venues.setStatus("created");
        venues.setUserId(1);
        venues.setAddressLine1("address1");
        venues.setAddressLine2("address2");
        venues.setUpdatedAt("2022-11-17 00:00:00");
        venues.setCreatedAt("2022-11-17 00:00:00");
        return venues;
    }

    @Test
    void createVenueTest() throws Exception {
        Mockito.doReturn(1).when(venueRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/venues/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getVenue())))
            .andExpect(status().isCreated())
            .andExpect(content().string("Venue created successfully"));
    }

    @Test
    void createVenueFailedTest() throws Exception {
        Mockito.when(venueRepository.save(getVenue())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/venues/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getVenue())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllVenuesTest() throws Exception {
        List<Venues> venues = new ArrayList<>();
        venues.add(getVenue());
        Mockito.when(venueRepository.findAll()).thenReturn(venues);

        mvc.perform(MockMvcRequestBuilders.get("/api/venues/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("test"));
    }

    @Test
    void getVenueTest() throws Exception {
        Mockito.when(venueRepository.getById(99)).thenReturn(Optional.of(getVenue()));
        mvc.perform(MockMvcRequestBuilders.get("/api/venues/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    void getVenueNotFoundTest() throws Exception {
        Mockito.when(venueRepository.getById(99)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/venues/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateVenueTest() throws Exception {
        Mockito.when(venueRepository.getById(99)).thenReturn(Optional.of(getVenue()));
        Mockito.when(venueRepository.update(getVenue())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/venues/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getVenue())))
            .andExpect(status().isOk())
            .andExpect(content().string("Venue updated successfully"));
    }

    @Test
    void updateVenueWhenNotFoundTest() throws Exception {
        Mockito.when(venueRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(venueRepository.update(getVenue())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/venues/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getVenue())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteVenueTest() throws Exception {
        Mockito.when(venueRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/venues/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Venue deleted successfully"));
    }

    @Test
    void deleteVenueWhenNotFound() throws Exception {
        Mockito.when(venueRepository.deleteById(999)).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.delete("/api/venues/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getVenuesByUserIdTest() throws Exception {
        List<Venues> venues = new ArrayList<>();
        venues.add(getVenue());
        Mockito.when(venueRepository.getVenuesByUserId(1)).thenReturn(venues);

        mvc.perform(MockMvcRequestBuilders.get("/api/venues/users/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("test"));
    }
    
}
