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

import com.dalhousie.server.businesslogic.DonationRepository;
import com.dalhousie.server.model.Donation;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private DonationRepository donationRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createDonation(@RequestBody Donation donation) {
        if(donationRepository.save(donation) > 0) {
            return new ResponseEntity<>("Donation created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Donation> getAllDonation(){
        return donationRepository.findAll();
    }

    @GetMapping("/events/{id}")
    public List<Donation> getAllEventDonations(@PathVariable Integer id){
        return donationRepository.getDonationsByEventId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> get(@PathVariable Integer id) {
        return donationRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDonation(@PathVariable Integer id, @RequestBody Donation donation) {
        return donationRepository.getById(id)
        .map(savedDonation -> {
            savedDonation.setEventId(donation.getEventId());
            savedDonation.setName(donation.getName());
            savedDonation.setAmount(donation.getAmount());
            savedDonation.setEmail(donation.getEmail());
            savedDonation.setDescription(donation.getDescription());
            savedDonation.setUpdatedAt(donation.getUpdatedAt());
            savedDonation.setCreatedAt(donation.getCreatedAt());

            donationRepository.update(savedDonation);
            return new ResponseEntity<>("Donation updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        donationRepository.deleteById(id);
        return new ResponseEntity<>("Donation deleted successfully", HttpStatus.OK);
    }
}
