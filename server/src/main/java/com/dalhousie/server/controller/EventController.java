package com.dalhousie.server.controller;

import com.dalhousie.server.contract.IEventRepository;
import com.dalhousie.server.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    
    @Autowired
    private IEventRepository eventRepository;

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
    public ResponseEntity<String> updateEvent(@PathVariable Integer id, @RequestBody Event event) {
        return eventRepository.getById(id)
        .map(savedEvent -> {
            savedEvent.setTitle(event.getTitle());
            savedEvent.setDescription(event.getDescription());
            savedEvent.setEventType(event.getEventType());
            savedEvent.setStatus(event.getStatus());
            savedEvent.setStartDatetime(event.getStartDatetime());
            savedEvent.setEndDatetime(event.getEndDatetime());
            savedEvent.setVenue(event.getVenue());
            savedEvent.setMaxCapacity(event.getMaxCapacity());
            savedEvent.setUpdatedAt(event.getUpdatedAt());
            savedEvent.setCreatedAt(event.getCreatedAt());

            eventRepository.update(savedEvent);
            return new ResponseEntity<>("Event updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if(eventRepository.deleteById(id) > 0) {
            return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
