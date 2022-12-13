package com.dalhousie.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.dalhousie.server.model.Venues;
import com.dalhousie.server.persistence.IVenueRepository;

@RestController
@RequestMapping("/api/venues")
public class VenueController {
    
    @Autowired
    private IVenueRepository venueRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createVenue(@RequestBody Venues venue) {
        if(venueRepository.save(venue) > 0) {
            return new ResponseEntity<>("Venue created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Venues> getAllVenues(){
        return venueRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public List<Venues> getAllVenuesByUserId(@PathVariable Integer id) {
        return venueRepository.getVenuesByUserId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venues> get(@PathVariable Integer id) {
        return venueRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVenue(@PathVariable Integer id, @RequestBody Venues venue) {
        return venueRepository.getById(id)
        .map(savedVenue -> {
            savedVenue.setUserId(venue.getUserId());
            savedVenue.setName(venue.getName());
            savedVenue.setStatus(venue.getStatus());
            savedVenue.setAddressLine1(venue.getAddressLine1());
            savedVenue.setAddressLine2(venue.getAddressLine2());
            savedVenue.setUpdatedAt(venue.getUpdatedAt());
            savedVenue.setCreatedAt(venue.getCreatedAt());

            venueRepository.update(savedVenue);
            return new ResponseEntity<>("Venue updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if(venueRepository.deleteById(id) > 0) {
            return new ResponseEntity<>("Venue deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
