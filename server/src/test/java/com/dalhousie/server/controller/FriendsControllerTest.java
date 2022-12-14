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
import com.dalhousie.server.model.Friends;
import com.dalhousie.server.model.User;
import com.dalhousie.server.repository.FriendRepository;

@SpringBootTest
@ActiveProfiles("test")
public class FriendsControllerTest extends AbstractTest {
    
    @MockBean
    FriendRepository friendRepository;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private Friends getFriend() {
        Friends friends = new Friends();
        friends.setId(1);
        friends.setUserId(1);
        friends.setTargetUserId(2);
        friends.setUpdatedAt("2022-11-17 00:00:00");
        friends.setCreatedAt("2022-11-17 00:00:00");
        return friends;
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
    void createFriendTest() throws Exception {
        Mockito.doReturn(1).when(friendRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/friends/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getFriend())))
            .andExpect(status().isCreated())
            .andExpect(content().string("Friend created successfully"));
    }

    @Test
    void createFriendFailedTest() throws Exception {
        Mockito.when(friendRepository.save(getFriend())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/friends/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getFriend())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllFriendTest() throws Exception {
        List<Friends> friends = new ArrayList<>();
        friends.add(getFriend());
        Mockito.when(friendRepository.findAll()).thenReturn(friends);

        mvc.perform(MockMvcRequestBuilders.get("/api/friends/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].userId").value("1"));
    }

    @Test
    void getFriendTest() throws Exception {
        Mockito.when(friendRepository.getById(99)).thenReturn(Optional.of(getFriend()));
        mvc.perform(MockMvcRequestBuilders.get("/api/friends/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userId").value("1"));
    }

    @Test
    void getFriendNotFoundTest() throws Exception {
        Mockito.when(friendRepository.getById(99)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/friends/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateFriendTest() throws Exception {
        Mockito.when(friendRepository.getById(99)).thenReturn(Optional.of(getFriend()));
        Mockito.when(friendRepository.update(getFriend())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/friends/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getFriend())))
            .andExpect(status().isOk())
            .andExpect(content().string("Friend updated successfully"));
    }

    @Test
    void updateFriendWhenNotFoundTest() throws Exception {
        Mockito.when(friendRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(friendRepository.update(getFriend())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/friends/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getFriend())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteFriendTest() throws Exception {
        Mockito.when(friendRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/friends/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Friend deleted successfully"));
    }

    @Test
    void deleteFriendWhenNotFound() throws Exception {
        Mockito.when(friendRepository.deleteById(999)).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.delete("/api/friends/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllFriendsByUserIdTest() throws Exception {
        List<User> friends = new ArrayList<>();
        friends.add(getUser());
        Mockito.when(friendRepository.getAllFriendsByUserId(1)).thenReturn(friends);

        mvc.perform(MockMvcRequestBuilders.get("/api/friends/all/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].userName").value("testuser"));
    }
}
