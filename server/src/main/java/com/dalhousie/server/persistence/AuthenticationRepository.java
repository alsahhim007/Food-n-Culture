package com.dalhousie.server.persistence;

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
        return jdbcTemplate.query("CALL getAllAuthentication()", BeanPropertyRowMapper.newInstance(Authentication.class));
    }

    @Override
    public List<Authentication> getOTPByUserId(Integer userId) {
        return jdbcTemplate.query("CALL getOTPByUserId(?)", BeanPropertyRowMapper.newInstance(Authentication.class), userId);
    }

    @Override
    public int save(Authentication authentication) {
        return jdbcTemplate.update(
                "CALL createAuthentication(?, ?, ?, ?)",
                authentication.getId(), authentication.getUserId(), authentication.getOtp(), authentication.isExpired());
    }

    @Override
    public int update(Authentication authentication) {
        return jdbcTemplate.update(
                "CALL updateAuthentication(?, ?, ?, ?)",
                authentication.getId(), authentication.getUserId(), authentication.getOtp(), authentication.isExpired());
    }

    @Override
    public int delete(Authentication authentication) {
        return jdbcTemplate.update("CALL deleteAuthenticationById(?)", authentication.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("CALL deleteAuthenticationById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Authentication> getById(Integer id) {
        try {
            Authentication authentication = jdbcTemplate.queryForObject("CALL getAuthenticationById(?)", BeanPropertyRowMapper.newInstance(Authentication.class), id);
            return Optional.of(authentication);
        }catch(Exception e) {
            return Optional.empty();
        }
    }
    
}
