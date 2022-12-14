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
import com.dalhousie.server.repository.UserRepository;


@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTest extends AbstractTest {

    @MockBean
    UserRepository userRepository;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private User getUser() {
        User user = new User();
        user.setId(99);
        user.setUserName("testuser");
        user.setEmail("test@dal.ca");
        user.setPassword("SWHSWQIWHIQWHIQ/==");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setVerified(false);
        user.setStatus("created");
        user.setUpdatedAt("2022-11-17 00:00:00");
        user.setCreatedAt("2022-11-17 00:00:00");
        return user;
    }

    @Test
    void createUserTest() throws Exception {
        Mockito.doReturn(1).when(userRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/users/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getUser())))
            .andExpect(status().isCreated())
            .andExpect(content().string("User created successfully"));
    }

    @Test
    void createUserFailedTest() throws Exception {
        Mockito.when(userRepository.save(getUser())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/users/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getUser())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllUsersTest() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(getUser());
        Mockito.when(userRepository.findAll()).thenReturn(users);

        mvc.perform(MockMvcRequestBuilders.get("/api/users/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].userName").value("testuser"));
    }

    @Test
    void getUserTest() throws Exception {
        Mockito.when(userRepository.getById(99)).thenReturn(Optional.of(getUser()));
        mvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userName").value("testuser"));
    }

    @Test
    void getUserNotFoundTest() throws Exception {
        Mockito.when(userRepository.getById(99)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateUserTest() throws Exception {
        Mockito.when(userRepository.getById(99)).thenReturn(Optional.of(getUser()));
        Mockito.when(userRepository.update(getUser())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/users/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getUser())))
            .andExpect(status().isOk())
            .andExpect(content().string("User updated successfully"));
    }

    @Test
    void updateUserWhenNotFoundTest() throws Exception {
        Mockito.when(userRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(userRepository.update(getUser())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/users/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getUser())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserTest() throws Exception {
        Mockito.when(userRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("User deleted successfully"));
    }

    @Test
    void deleteUserWhenNotFound() throws Exception {
        Mockito.when(userRepository.deleteById(999)).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getByUsernameTest() throws Exception {
        Mockito.when(userRepository.getByUserName("testuser")).thenReturn(Optional.of(getUser()));

        mvc.perform(MockMvcRequestBuilders.get("/api/users/username/{username}", "testuser")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userName").value("testuser"));
    }

    @Test
    void getByUsernameWhenNotFoundTest() throws Exception {
        Mockito.when(userRepository.getByUserName("test1user")).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get("/api/users/username/{username}", "test1user")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void getByEmailTest() throws Exception {
        Mockito.when(userRepository.getByEmail("test@dal.ca")).thenReturn(Optional.of(getUser()));

        mvc.perform(MockMvcRequestBuilders.get("/api/users/email/{email}", "test@dal.ca")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email").value("test@dal.ca"));
    }

    @Test
    void getByEmailWhenNotFoundTest() throws Exception {
        Mockito.when(userRepository.getByEmail("test1@dal.ca")).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get("/api/users/email/{email}", "test1@dal.ca")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }
}
