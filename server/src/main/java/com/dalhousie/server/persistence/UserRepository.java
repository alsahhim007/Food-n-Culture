package com.dalhousie.server.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.User;

@Component
public class UserRepository implements IUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("CALL getAllUsers", BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public int save(User user) {
        return jdbcTemplate.update(
                "CALL createUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                user.getId(), user.getUserName(), user.getEmail(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.isVerified(), user.getStatus(), user.getCreatedAt(),
                user.getUpdatedAt());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(
                "CALL updateUser(?, ?, ?, ?, ?, ?, ?, ?)",
                user.getId(), user.getUserName(), user.getEmail(), user.getPassword(),
                user.getFirstName(), user.getLastName(), user.isVerified(), user.getStatus());
    }

    @Override
    public int delete(User user) {
        return jdbcTemplate.update("CALL deleteUserById(?)", user.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("CALL deleteUserById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<User> getById(Integer id) {
        try {
            User user = jdbcTemplate.queryForObject("CALL getUserById(?)", BeanPropertyRowMapper.newInstance(User.class), id);
            return Optional.of(user);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        try {
            User user = jdbcTemplate.queryForObject("CALL getUserByUserName(?)", BeanPropertyRowMapper.newInstance(User.class), userName);
            return Optional.of(user);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject("CALL getUserByEmail(?)", BeanPropertyRowMapper.newInstance(User.class), email);
            return Optional.of(user);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

}
