package com.dalhousie.server.controller;

import com.dalhousie.server.contract.IVenueRepository;
import com.dalhousie.server.model.Venues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
