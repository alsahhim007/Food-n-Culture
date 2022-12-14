package com.dalhousie.server.controller;

import com.dalhousie.server.contract.IMessagesRepository;
import com.dalhousie.server.model.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
    
    @Autowired
    private IMessagesRepository messagesRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createMessage(@RequestBody Messages messages) {
        if(messagesRepository.save(messages) > 0) {
            return new ResponseEntity<>("Message created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Messages> getAllMessages(){
        return messagesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Messages> get(@PathVariable Integer id) {
        return messagesRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMessage(@PathVariable Integer id, @RequestBody Messages messages) {
        return messagesRepository.getById(id)
        .map(savedMessage -> {
            savedMessage.setId(messages.getId());
            savedMessage.setUserId(messages.getUserId());
            savedMessage.setTargetUserId(messages.getTargetUserId());
            savedMessage.setContent(messages.getContent());
            savedMessage.setRead(messages.isRead());

            messagesRepository.update(savedMessage);
            return new ResponseEntity<>("Message updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if(messagesRepository.deleteById(id) > 0) {
            return new ResponseEntity<>("Message deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/chats/{user1}/{user2}")
    public List<Messages> getAllMessagesBetweenUsers(@PathVariable Integer user1, @PathVariable Integer user2){
        return messagesRepository.getAllMessagesBetweenUsers(user1, user2);
    }
}
