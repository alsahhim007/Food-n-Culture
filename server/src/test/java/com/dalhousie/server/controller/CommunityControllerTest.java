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
import com.dalhousie.server.model.Community;
import com.dalhousie.server.persistence.CommunityRepository;


@SpringBootTest
@ActiveProfiles("test")
public class CommunityControllerTest extends AbstractTest {

    @MockBean
    CommunityRepository communityRepository;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private Community getCommunity() {
        Community community = new Community();
        community.setId(1);
        community.setTitle("Test Title");
        community.setDescription("test description");
        community.setUpdatedAt("2022-11-17 00:00:00");
        community.setCreatedAt("2022-11-17 00:00:00");
        return community;
    }

    @Test
    void createCommunityTest() throws Exception {
        Mockito.doReturn(1).when(communityRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/community/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getCommunity())))
            .andExpect(status().isCreated())
            .andExpect(content().string("Community created successfully"));
    }

    @Test
    void createCommunityFailedTest() throws Exception {
        Mockito.when(communityRepository.save(getCommunity())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/community/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getCommunity())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllCommunityTest() throws Exception {
        List<Community> community = new ArrayList<>();
        community.add(getCommunity());
        Mockito.when(communityRepository.findAll()).thenReturn(community);

        mvc.perform(MockMvcRequestBuilders.get("/api/community/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].description").value("test description"));
    }

    @Test
    void getCommunityTest() throws Exception {
        Mockito.when(communityRepository.getById(99)).thenReturn(Optional.of(getCommunity()));
        mvc.perform(MockMvcRequestBuilders.get("/api/community/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.description").value("test description"));
    }

    @Test
    void getCommunityNotFoundTest() throws Exception {
        Mockito.when(communityRepository.getById(99)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/community/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateCommunityTest() throws Exception {
        Mockito.when(communityRepository.getById(99)).thenReturn(Optional.of(getCommunity()));
        Mockito.when(communityRepository.update(getCommunity())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/community/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getCommunity())))
            .andExpect(status().isOk())
            .andExpect(content().string("Community updated successfully"));
    }

    @Test
    void updateCommunityWhenNotFoundTest() throws Exception {
        Mockito.when(communityRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(communityRepository.update(getCommunity())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/community/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getCommunity())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteCommunityTest() throws Exception {
        Mockito.when(communityRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/community/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Community deleted successfully"));
    }

    @Test
    void deleteCommunityWhenNotFound() throws Exception {
        Mockito.when(communityRepository.deleteById(999)).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.delete("/api/community/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
