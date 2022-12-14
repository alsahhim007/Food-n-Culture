package com.dalhousie.server.controller;

import com.dalhousie.server.contract.IAmenitiesRepository;
import com.dalhousie.server.model.Amenities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amenities")
public class AmenitiesController {
    
    @Autowired
    private IAmenitiesRepository amenitiesRepository;
    
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
        if(amenitiesRepository.deleteById(id) > 0) {
            return new ResponseEntity<>("Amenities deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
