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

import com.dalhousie.server.businesslogic.AuthenticationRepository;
import com.dalhousie.server.model.Authentication;

@RestController
@RequestMapping("/api/authentication")
public class MultiFactorAuthenticationController {
    
    @Autowired
    private AuthenticationRepository authenticationRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createAuthentication(@RequestBody Authentication authentication) {
        if(authenticationRepository.save(authentication) > 0) {
            return new ResponseEntity<>("Authentication created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Authentication> getAllAuthentication(){
        return authenticationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Authentication> get(@PathVariable Integer id) {
        return authenticationRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{id}")
    public List<Authentication> getAllOTPByUserId(@PathVariable Integer id) {
        return authenticationRepository.getOTPByUserId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuthentication(@PathVariable Integer id, @RequestBody Authentication authentication) {
        return authenticationRepository.getById(id)
        .map(savedAuth -> {
            savedAuth.setUserId(authentication.getUserId());
            savedAuth.setOtp(authentication.getOtp());
            savedAuth.setExpired(authentication.isExpired());
            savedAuth.setCreatedAt(authentication.getCreatedAt());

            authenticationRepository.update(savedAuth);
            return new ResponseEntity<>("Authentication updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        authenticationRepository.deleteById(id);
        return new ResponseEntity<>("Authentication deleted successfully", HttpStatus.OK);
    }

}
