package com.dalhousie.server.repository;

import com.dalhousie.server.contract.IAuthenticationRepository;
import com.dalhousie.server.model.Authentication;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.AuthenticationRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthenticationRepository implements IAuthenticationRepository {

    @Autowired
    private IConnection dbConnection;
    
    @Override
    public List<Authentication> findAll() {
        return dbConnection.executeProcedure("CALL getAllAuthentication()", new AuthenticationRowMapper());
    }

    @Override
    public Authentication getOTPByUserId(Integer userId) {
        return dbConnection.executeProcedureForObject("CALL getOTPByUserId(?)", new AuthenticationRowMapper(), userId);
    }

    @Override
    public int save(Authentication authentication) {
        return dbConnection.executeProcedure(
                "CALL createAuthentication(?, ?, ?, ?)",
                authentication.getId(), authentication.getUserId(), authentication.getOtp(), authentication.isExpired());
    }

    @Override
    public int update(Authentication authentication) {
        return dbConnection.executeProcedure(
                "CALL updateAuthentication(?, ?, ?, ?)",
                authentication.getId(), authentication.getUserId(), authentication.getOtp(), authentication.isExpired());
    }

    @Override
    public int delete(Authentication authentication) {
        return dbConnection.executeProcedure("CALL deleteAuthenticationById(?)", authentication.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteAuthenticationById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Authentication> getById(Integer id) {
        try {
            Authentication authentication = dbConnection.executeProcedureForObject("CALL getAuthenticationById(?)", new AuthenticationRowMapper(), id);
            return Optional.of(authentication);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int deleteAllOTPByuserId(Integer userId) {
        return dbConnection.executeProcedure("CALL deleteAllAuthenticationById(?)", userId);
    }
    
}
