package com.dalhousie.server.businesslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.Authentication;

@Component
public class AuthenticationRepository implements IAuthenticationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Authentication> findAll() {
        return jdbcTemplate.query("SELECT * FROM authentication", BeanPropertyRowMapper.newInstance(Authentication.class));
    }

    @Override
    public List<Authentication> getOTPByUserId(Integer userId) {
        return jdbcTemplate.query("SELECT * FROM authentication WHERE user_id=?", BeanPropertyRowMapper.newInstance(Authentication.class), userId);
    }

    @Override
    public int save(Authentication authentication) {
        return jdbcTemplate.update(
                "INSERT INTO authentication(id, user_id, otp, expired, created_at) VALUES(?, ?, ?, ?, ?)",
                null, authentication.getUserId(), authentication.getOtp(), authentication.isExpired(), authentication.getCreatedAt());
    }

    @Override
    public int update(Authentication authentication) {
        return jdbcTemplate.update(
                "UPDATE authentication SET user_id=?, otp=?, expired=?, created_at=? WHERE id=?",
                authentication.getUserId(), authentication.getOtp(), authentication.isExpired(), authentication.getCreatedAt(), authentication.getId());
    }

    @Override
    public int delete(Authentication authentication) {
        return jdbcTemplate.update("DELETE FROM authentication WHERE id=?", authentication.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM authentication WHERE id=?", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Authentication> getById(Integer id) {
        try {
            Authentication authentication = jdbcTemplate.queryForObject("SELECT * from authentication WHERE id=?", BeanPropertyRowMapper.newInstance(Authentication.class), id);
            return Optional.of(authentication);
        }catch(Exception e) {
            return Optional.empty();
        }
    }
    
}
