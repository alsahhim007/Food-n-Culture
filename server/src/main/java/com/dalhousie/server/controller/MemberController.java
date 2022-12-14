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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dalhousie.server.contract.IEventMemberRepository;
import com.dalhousie.server.model.EventMember;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    
    @Autowired
    private IEventMemberRepository eventMemberRepository;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createMember(@RequestBody EventMember member) {
        if(eventMemberRepository.save(member) > 0) {
            return new ResponseEntity<>("Member created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<EventMember> getAllMembers(){
        return eventMemberRepository.findAll();
    }

    @GetMapping("/events/{id}")
    public List<EventMember> getAllEventDonations(@PathVariable Integer id){
        return eventMemberRepository.getMembersByEventId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventMember> get(@PathVariable Integer id) {
        return eventMemberRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Integer id, @RequestBody EventMember member) {
        return eventMemberRepository.getById(id)
        .map(savedMember -> {
            savedMember.setEventId(member.getEventId());
            savedMember.setUserId(member.getUserId());
            savedMember.setStatus(member.getStatus());
            savedMember.setUpdatedAt(member.getUpdatedAt());
            savedMember.setCreatedAt(member.getCreatedAt());

            eventMemberRepository.update(savedMember);
            return new ResponseEntity<>("Member updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if(eventMemberRepository.deleteById(id) > 0) {
            return new ResponseEntity<>("Member deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/users/{id}")
    public List<EventMember> getAllMembersByUserId(@PathVariable Integer id){
        return eventMemberRepository.getMembersByUserId(id);
    }

}
