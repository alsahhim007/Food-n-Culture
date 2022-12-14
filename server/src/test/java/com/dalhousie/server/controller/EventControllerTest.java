package com.dalhousie.server.controller;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.enums.EventType;
import com.dalhousie.server.model.Event;
import com.dalhousie.server.persistence.EventRepository;

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
public class EventControllerTest extends AbstractTest {
    
    @MockBean
    EventRepository eventRepository;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private Event getEvent() {
        Event event = new Event();
        event.setTitle("Test Title");
        event.setEventType(EventType.INDIVIDUAL);
        event.setDescription("test description");
        event.setStatus("created");
        event.setStartDatetime("2022-11-18 00:00:00");
        event.setEndDatetime("2022-11-18 10:00:00");
        event.setVenue("Halifax");
        event.setMaxCapacity(10);
        event.setUpdatedAt("2022-10-11 00:00:00");
        event.setCreatedAt("2022-10-11 00:00:00");
        return event;
    }

    @Test
    void createEventTest() throws Exception {
        Mockito.doReturn(1).when(eventRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/events/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getEvent())))
            .andExpect(status().isCreated())
            .andExpect(content().string("Event created successfully"));
    }

    @Test
    void createEventFailedTest() throws Exception {
        Mockito.when(eventRepository.save(getEvent())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/events/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getEvent())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllEventsTest() throws Exception {
        List<Event> events = new ArrayList<>();
        events.add(getEvent());
        Mockito.when(eventRepository.findAll()).thenReturn(events);

        mvc.perform(MockMvcRequestBuilders.get("/api/events/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Test Title"));
    }

    @Test
    void getEventsTest() throws Exception {
        Mockito.when(eventRepository.getById(99)).thenReturn(Optional.of(getEvent()));
        mvc.perform(MockMvcRequestBuilders.get("/api/events/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Test Title"));
    }

    @Test
    void getEventsNotFoundTest() throws Exception {
        Mockito.when(eventRepository.getById(999)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/events/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateEventTest() throws Exception {
        Mockito.when(eventRepository.getById(99)).thenReturn(Optional.of(getEvent()));
        Mockito.when(eventRepository.update(getEvent())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/events/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getEvent())))
            .andExpect(status().isOk())
            .andExpect(content().string("Event updated successfully"));
    }

    @Test
    void updateEventsNotFoundTest() throws Exception {
        Mockito.when(eventRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(eventRepository.update(getEvent())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/events/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getEvent())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteEventsTest() throws Exception {
        Mockito.when(eventRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/events/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Event deleted successfully"));
    }
    
}
