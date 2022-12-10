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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dalhousie.server.model.Amenities;
import com.dalhousie.server.persistence.AmenitiesRepository;

@RestController
@RequestMapping("/api/amenities")
public class AmenitiesController {
    
    @Autowired
    private AmenitiesRepository amenitiesRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createAmenities(@RequestBody Amenities amenities) {
        if(amenitiesRepository.save(amenities) > 0) {
            return new ResponseEntity<>("Amenities created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Amenities> getAllAmenities(){
        return amenitiesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amenities> get(@PathVariable Integer id) {
        return amenitiesRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/venues/{id}")
    public List<Amenities> getAmenitiesByVenueId(@PathVariable Integer id) {
        return amenitiesRepository.getAllAmenitiesByVenueId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAmenities(@PathVariable Integer id, @RequestBody Amenities amenities) {
        return amenitiesRepository.getById(id)
        .map(savedAmenities -> {
            savedAmenities.setName(amenities.getName());
            savedAmenities.setCategory(amenities.getCategory());
            savedAmenities.setUpdatedAt(amenities.getUpdatedAt());
            savedAmenities.setCreatedAt(amenities.getCreatedAt());

            amenitiesRepository.update(savedAmenities);
            return new ResponseEntity<>("Amenities updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        amenitiesRepository.deleteById(id);
        return new ResponseEntity<>("Amenities deleted successfully", HttpStatus.OK);
    }
}
