package com.dalhousie.server.controller;

import com.dalhousie.server.contract.ICommunityRepository;
import com.dalhousie.server.model.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @Autowired
    private ICommunityRepository communityRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createCommunity(@RequestBody Community community) {
        if(communityRepository.save(community) > 0) {
            return new ResponseEntity<>("Community created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Community> getAllCommunity(){
        return communityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> get(@PathVariable Integer id) {
        return communityRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCommunity(@PathVariable Integer id, @RequestBody Community community) {
        return communityRepository.getById(id)
        .map(savedCommunity -> {
            savedCommunity.setId(community.getId());
            savedCommunity.setTitle(community.getTitle());
            savedCommunity.setDescription(community.getDescription());

            communityRepository.update(savedCommunity);
            return new ResponseEntity<>("Community updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if(communityRepository.deleteById(id) > 0) {
            return new ResponseEntity<>("Community deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
