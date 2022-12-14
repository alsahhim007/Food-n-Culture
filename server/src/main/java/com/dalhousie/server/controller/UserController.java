package com.dalhousie.server.controller;

import com.dalhousie.server.contract.IUserRepository;
import com.dalhousie.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;
    
    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userRepository.save(user) > 0) {
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        return userRepository.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userRepository.getById(id)
                .map(savedUser -> {
                    savedUser.setFirstName(user.getFirstName());
                    savedUser.setLastName(user.getLastName());
                    savedUser.setEmail(user.getEmail());
                    savedUser.setStatus(user.getStatus());
                    savedUser.setUserName(user.getUserName());
                    savedUser.setVerified(user.isVerified());
                    savedUser.setPassword(user.getPassword());

                    userRepository.update(savedUser);
                    return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if(userRepository.deleteById(id) > 0){
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        return userRepository.getByUserName(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        return userRepository.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
