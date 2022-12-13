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
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.model.Donation;
import com.dalhousie.server.persistence.DonationRepository;

@SpringBootTest
@ActiveProfiles("test")
public class DonationControllerTest extends AbstractTest {

    @MockBean
    DonationRepository donationRepository;
    
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private Donation getDonation() {
        Donation donation = new Donation();
        donation.setId(99);
        donation.setEventId(99);
        donation.setName("Bob");
        donation.setAmount(100);
        donation.setEmail("bob@gmail.com");
        donation.setNote("Donate $100.00");
        donation.setUpdatedAt("2022-10-11 00:00:00");
        donation.setCreatedAt("2022-10-11 00:00:00");
        return donation;
    }

    @Test
    void createDonationTest() throws Exception {
        Mockito.doReturn(1).when(donationRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/donations/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getDonation())))
            .andExpect(status().isCreated())
            .andExpect(content().string("Donation created successfully"));
    }

    @Test
    void createDonationFailedTest() throws Exception {
        Mockito.when(donationRepository.save(getDonation())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/donations/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getDonation())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllDonationsTest() throws Exception {
        List<Donation> donations = new ArrayList<>();
        donations.add(getDonation());
        Mockito.when(donationRepository.findAll()).thenReturn(donations);

        mvc.perform(MockMvcRequestBuilders.get("/api/donations/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].note").value("Donate $100.00"));
    }

    @Test
    void getDonationTest() throws Exception {
        Mockito.when(donationRepository.getById(99)).thenReturn(Optional.of(getDonation()));
        mvc.perform(MockMvcRequestBuilders.get("/api/donations/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.note").value("Donate $100.00"));
    }

    @Test
    void getDonationsNotFoundTest() throws Exception {
        Mockito.when(donationRepository.getById(99)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/donations/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateDonationsTest() throws Exception {
        Mockito.when(donationRepository.getById(99)).thenReturn(Optional.of(getDonation()));
        Mockito.when(donationRepository.update(getDonation())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/donations/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getDonation())))
            .andExpect(status().isOk())
            .andExpect(content().string("Donation updated successfully"));
    }

    @Test
    void updateDonationsNotFoundTest() throws Exception {
        Mockito.when(donationRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(donationRepository.update(getDonation())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/donations/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getDonation())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteDonationsTest() throws Exception {
        Mockito.when(donationRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/donations/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Donation deleted successfully"));
    }

    @Test
    void getDonationsByEventIdTest() throws Exception {
        List<Donation> donations = new ArrayList<>();
        donations.add(getDonation());
        Mockito.when(donationRepository.getDonationsByEventId(99)).thenReturn(donations);

        mvc.perform(MockMvcRequestBuilders.get("/api/donations/events/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].note").value("Donate $100.00"));
    }
}
