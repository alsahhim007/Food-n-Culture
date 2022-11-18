package com.dalhousie.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dalhousie.server.businesslogic.EventRepository;
import com.dalhousie.server.model.Event;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/events")
public class EventController {
    
    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        if(eventRepository.save(event) > 0) {
            return new ResponseEntity<>("Event created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable Integer id) {
        return eventRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody Event event) {
        return eventRepository.getById(id)
        .map(savedEvent -> {
            savedEvent.setTitle(event.getTitle());
            savedEvent.setDescription(event.getDescription());
            savedEvent.setEventType(event.getEventType());
            savedEvent.setStatus(event.getStatus());
            savedEvent.setStartDateTime(event.getStartDateTime());
            savedEvent.setEndDateTime(event.getEndDateTime());
            savedEvent.setVenue(event.getVenue());
            savedEvent.setMaxCapacity(event.getMaxCapacity());
            savedEvent.setUpdateAt(event.getUpdateAt());
            savedEvent.setCreatedAt(event.getCreatedAt());

            eventRepository.update(savedEvent);
            return new ResponseEntity<>("Event updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        eventRepository.deleteById(id);
        return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
    }

}
