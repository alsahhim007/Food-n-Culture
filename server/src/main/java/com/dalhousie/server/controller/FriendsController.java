package com.dalhousie.server.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dalhousie.server.model.Friends;
import com.dalhousie.server.persistence.IFriendRepository;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {
    
    @Autowired
    private IFriendRepository friendRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createFriend(@RequestBody Friends friends) {
        if(friendRepository.save(friends) > 0) {
            return new ResponseEntity<>("Friend created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Friends> getAllFriends(){
        return friendRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friends> get(@PathVariable Integer id) {
        return friendRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFriend(@PathVariable Integer id, @RequestBody Friends friends) {
        return friendRepository.getById(id)
        .map(savedFriend -> {
            savedFriend.setId(friends.getId());
            savedFriend.setUserId(friends.getUserId());

            friendRepository.update(savedFriend);
            return new ResponseEntity<>("Friend updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if(friendRepository.deleteById(id) > 0) {
            return new ResponseEntity<>("Friend deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
