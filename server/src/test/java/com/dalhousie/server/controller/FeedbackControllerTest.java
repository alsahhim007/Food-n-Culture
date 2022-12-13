package com.dalhousie.server.controller;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.model.Feedback;
import com.dalhousie.server.persistence.FeedbackRepository;

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
public class FeedbackControllerTest extends AbstractTest {
    
    @MockBean
    FeedbackRepository feedbackRepository;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }
    
    private Feedback getFeedback() {
        Feedback feedback = new Feedback();
        feedback.setComment("test comment");
        feedback.setStars("5");
        feedback.setUpdatedAt("2022-10-11 00:00:00");
        feedback.setCreatedAt("2022-10-11 00:00:00");
        return feedback;
    }

    @Test
    void createFeedbackTest() throws Exception {
        Mockito.doReturn(1).when(feedbackRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/feedbacks/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getFeedback())))
            .andExpect(status().isCreated())
            .andExpect(content().string("Feedback created successfully"));
    }

    @Test
    void createFeedbackFailedTest() throws Exception {
        Mockito.when(feedbackRepository.save(getFeedback())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/feedbacks/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getFeedback())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllFeedbackTest() throws Exception {
        List<Feedback> feedback = new ArrayList<>();
        feedback.add(getFeedback());
        Mockito.when(feedbackRepository.findAll()).thenReturn(feedback);

        mvc.perform(MockMvcRequestBuilders.get("/api/feedbacks/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].comment").value("test comment"));
    }

    @Test
    void getFeedbackTest() throws Exception {
        Mockito.when(feedbackRepository.getById(99)).thenReturn(Optional.of(getFeedback()));
        mvc.perform(MockMvcRequestBuilders.get("/api/feedbacks/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.comment").value("test comment"));
    }

    @Test
    void geFeedbackNotFoundTest() throws Exception {
        Mockito.when(feedbackRepository.getById(999)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/feedbacks/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateFeedbackTest() throws Exception {
        Mockito.when(feedbackRepository.getById(99)).thenReturn(Optional.of(getFeedback()));
        Mockito.when(feedbackRepository.update(getFeedback())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/feedbacks/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getFeedback())))
            .andExpect(status().isOk())
            .andExpect(content().string("Feedback updated successfully"));
    }

    @Test
    void updateFeedbackNotFoundTest() throws Exception {
        Mockito.when(feedbackRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(feedbackRepository.update(getFeedback())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/feedbacks/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getFeedback())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteFeedbackTest() throws Exception {
        Mockito.when(feedbackRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/feedbacks/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Feedback deleted successfully"));
    }

    @Test
    void getFeedbackByEventIdTest() throws Exception {
        List<Feedback> feedback = new ArrayList<>();
        feedback.add(getFeedback());
        Mockito.when(feedbackRepository.getFeedbackByEventId(99)).thenReturn(feedback);

        mvc.perform(MockMvcRequestBuilders.get("/api/feedbacks/events/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].comment").value("test comment"));
    }
}
