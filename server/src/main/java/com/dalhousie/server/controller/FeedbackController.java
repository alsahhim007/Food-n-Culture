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

import com.dalhousie.server.model.Feedback;
import com.dalhousie.server.persistence.FeedbackRepository;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createFeedback(@RequestBody Feedback feedback) {
        if(feedbackRepository.save(feedback) > 0) {
            return new ResponseEntity<>("Feedback created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Feedback> getAllFeedback(){
        return feedbackRepository.findAll();
    }

    @GetMapping("/events/{id}")
    public List<Feedback> getAllEventFeedback(@PathVariable Integer id){
        return feedbackRepository.getFeedbackByEventId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> get(@PathVariable Integer id) {
        return feedbackRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDonation(@PathVariable Integer id, @RequestBody Feedback feedback) {
        return feedbackRepository.getById(id)
        .map(savedFeedback -> {
            savedFeedback.setMemberId(feedback.getMemberId());
            savedFeedback.setComment(feedback.getComment());
            savedFeedback.setStars(feedback.getStars());
            savedFeedback.setCreatedAt(feedback.getCreatedAt());
            savedFeedback.setUpdatedAt(feedback.getUpdatedAt());

            feedbackRepository.update(savedFeedback);
            return new ResponseEntity<>("Feedback updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        feedbackRepository.deleteById(id);
        return new ResponseEntity<>("Feedback deleted successfully", HttpStatus.OK);
    }

}
