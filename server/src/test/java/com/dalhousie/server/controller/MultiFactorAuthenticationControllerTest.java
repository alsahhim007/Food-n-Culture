package com.dalhousie.server.controller;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.model.Authentication;
import com.dalhousie.server.persistence.AuthenticationRepository;


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

@SpringBootTest
@ActiveProfiles("test")
public class MultiFactorAuthenticationControllerTest extends AbstractTest {
    
    @MockBean
    AuthenticationRepository authenticationRepository;

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
    void createAuthTest() throws Exception {
        Mockito.doReturn(1).when(authenticationRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/authentication/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getAuthDetails())))
            .andExpect(status().isCreated())
            .andExpect(content().string("Authentication created successfully"));
    }

    @Test
    void createAuthFailedTest() throws Exception {
        Mockito.when(authenticationRepository.save(getAuthDetails())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/authentication/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getAuthDetails())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllAuthTest() throws Exception {
        List<Authentication> auths = new ArrayList<>();
        auths.add(getAuthDetails());
        Mockito.when(authenticationRepository.findAll()).thenReturn(auths);

        mvc.perform(MockMvcRequestBuilders.get("/api/authentication/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].expired").value(false));
    }

    @Test
    void getAuthTest() throws Exception {
        Mockito.when(authenticationRepository.getById(99)).thenReturn(Optional.of(getAuthDetails()));
        mvc.perform(MockMvcRequestBuilders.get("/api/authentication/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.expired").value(false));
    }

    @Test
    void getAuthNotFoundTest() throws Exception {
        Mockito.when(authenticationRepository.getById(999)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/authentication/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateAuthTest() throws Exception {
        Mockito.when(authenticationRepository.getById(99)).thenReturn(Optional.of(getAuthDetails()));
        Mockito.when(authenticationRepository.update(getAuthDetails())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/authentication/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getAuthDetails())))
            .andExpect(status().isOk())
            .andExpect(content().string("Authentication updated successfully"));
    }

    @Test
    void updateAuthWhenNotFoundTest() throws Exception {
        Mockito.when(authenticationRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(authenticationRepository.update(getAuthDetails())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/authentication/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getAuthDetails())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserTest() throws Exception {
        Mockito.when(authenticationRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/authentication/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Authentication deleted successfully"));
    }
}
