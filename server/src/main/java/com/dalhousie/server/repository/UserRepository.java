package com.dalhousie.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalhousie.server.contract.IUserRepository;
import com.dalhousie.server.model.User;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.UserRowMapper;

@Component
public class UserRepository implements IUserRepository {

    @Autowired
    private IConnection dbConnection;

    @Override
    public List<User> findAll() {
        return dbConnection.executeProcedure("CALL getAllUsers", new UserRowMapper());
    }

    @Override
    public int save(User user) {
        return dbConnection.executeProcedure("CALL createUser(?, ?, ?, ?, ?, ?, ?, ?)", user.getId(), user.getUserName(), user.getEmail(), user.getPassword(), user.getFirstName(),
        user.getLastName(), user.isVerified(), user.getStatus());
    }

    @Override
    public int update(User user) {
        return dbConnection.executeProcedure("CALL updateUser(?, ?, ?, ?, ?, ?, ?, ?)", user.getId(), user.getUserName(), user.getEmail(), user.getPassword(),
        user.getFirstName(), user.getLastName(), user.isVerified(), user.getStatus());
    }

    @Override
    public int delete(User user) {
        return dbConnection.executeProcedure("CALL deleteUserById(?)", user.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteUserById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<User> getById(Integer id) {
        try {
            User user = dbConnection.executeProcedureForObject("CALL getUserById(?)", new UserRowMapper(), id);
            return Optional.of(user);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        try {
            User user = dbConnection.executeProcedureForObject("CALL getUserByUserName(?)", new UserRowMapper(), userName);
            return Optional.of(user);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try {
            User user = dbConnection.executeProcedureForObject("CALL getUserByEmail(?)", new UserRowMapper(), email);
            return Optional.of(user);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

}
