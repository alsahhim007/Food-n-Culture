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
import com.dalhousie.server.model.Messages;
import com.dalhousie.server.persistence.MessagesRepository;

@SpringBootTest
@ActiveProfiles("test")
public class MessagesControllerTest extends AbstractTest {
    
    @MockBean
    MessagesRepository messagesRepository;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private Messages getMessage() {
        Messages msg = new Messages();
        msg.setId(1);
        msg.setTargetUserId(10);
        msg.setContent("Hello");
        msg.setRead(false);
        msg.setUpdatedAt("2022-11-17 00:00:00");
        msg.setCreatedAt("2022-11-17 00:00:00");
        return msg;
    }

    @Test
    void createMessageTest() throws Exception {
        Mockito.doReturn(1).when(messagesRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/messages/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getMessage())))
            .andExpect(status().isCreated())
            .andExpect(content().string("Message created successfully"));
    }

    @Test
    void createMessageFailedTest() throws Exception {
        Mockito.when(messagesRepository.save(getMessage())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/messages/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getMessage())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllMessagesTest() throws Exception {
        List<Messages> messages = new ArrayList<>();
        messages.add(getMessage());
        Mockito.when(messagesRepository.findAll()).thenReturn(messages);

        mvc.perform(MockMvcRequestBuilders.get("/api/messages/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].content").value("Hello"));
    }

    @Test
    void getMessageTest() throws Exception {
        Mockito.when(messagesRepository.getById(99)).thenReturn(Optional.of(getMessage()));
        mvc.perform(MockMvcRequestBuilders.get("/api/messages/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").value("Hello"));
    }

    @Test
    void getMessageNotFoundTest() throws Exception {
        Mockito.when(messagesRepository.getById(99)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/messages/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateMessageTest() throws Exception {
        Mockito.when(messagesRepository.getById(99)).thenReturn(Optional.of(getMessage()));
        Mockito.when(messagesRepository.update(getMessage())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/messages/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getMessage())))
            .andExpect(status().isOk())
            .andExpect(content().string("Message updated successfully"));
    }

    @Test
    void updateMessageWhenNotFoundTest() throws Exception {
        Mockito.when(messagesRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(messagesRepository.update(getMessage())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/messages/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getMessage())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteMessageTest() throws Exception {
        Mockito.when(messagesRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/messages/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Message deleted successfully"));
    }

    @Test
    void deleteMessageWhenNotFound() throws Exception {
        Mockito.when(messagesRepository.deleteById(999)).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.delete("/api/messages/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllMessagesBetweenUsersTest() throws Exception {
        List<Messages> messages = new ArrayList<>();
        messages.add(getMessage());
        Mockito.when(messagesRepository.getAllMessagesBetweenUsers(1, 10)).thenReturn(messages);

        mvc.perform(MockMvcRequestBuilders.get("/api/messages/chats/{user1}/{user2}", 1, 10)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].content").value("Hello"));
    }
}
