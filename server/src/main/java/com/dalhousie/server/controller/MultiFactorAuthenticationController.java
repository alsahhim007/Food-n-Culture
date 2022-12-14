package com.dalhousie.server.controller;

import com.dalhousie.server.contract.IAuthenticationRepository;
import com.dalhousie.server.email.EmailServiceImplementation;
import com.dalhousie.server.email.IEmail;
import com.dalhousie.server.model.Authentication;
import com.dalhousie.server.model.EmailDetails;
import com.dalhousie.server.model.User;
import com.dalhousie.server.repository.UserRepository;
import com.dalhousie.server.utilities.RNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authentication")
public class MultiFactorAuthenticationController {
    
    @Autowired
    private IAuthenticationRepository authenticationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    IEmail emailService = new EmailServiceImplementation();
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createAuthentication(@RequestBody Authentication authentication) {

        authentication.setOtp(Integer.toString(RNG.getRandomNumber(4)));
        authenticationRepository.deleteAllOTPByuserId(authentication.getUserId());
        
        if(authenticationRepository.save(authentication) > 0) {
            
            Optional<User> user = userRepository.getById(authentication.getUserId());
            if(user.isPresent() && emailService.sendMail(new EmailDetails(user.get().getEmail(), "OTP - " + authentication.getOtp(), "FoodNCulture - Account Verification"))) {
                return new ResponseEntity<>("Authentication created successfully", HttpStatus.CREATED);
            }
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
    public Authentication getOTPByUserId(@PathVariable Integer id) {
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
        if(authenticationRepository.deleteById(id) > 0) {
            return new ResponseEntity<>("Authentication deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
