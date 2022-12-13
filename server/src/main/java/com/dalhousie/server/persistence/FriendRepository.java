package com.dalhousie.server.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.Friends;
import com.dalhousie.server.model.User;

@Component
public class FriendRepository implements IFriendRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Friends> findAll() {
        return jdbcTemplate.query("CALL getAllFriends", BeanPropertyRowMapper.newInstance(Friends.class));
    }

    @Override
    public int save(Friends friends) {
        return jdbcTemplate.update(
                "CALL createFriend(?, ?, ?)",
                friends.getId(), friends.getUserId(), friends.getTargetUserId());
    }

    @Override
    public int update(Friends friends) {
        return jdbcTemplate.update(
                "CALL updateFriend(?, ?, ?)",
                friends.getId(), friends.getUserId(), friends.getTargetUserId());
    }

    @Override
    public int delete(Friends friends) {
        return jdbcTemplate.update("CALL deleteFriendById(?)", friends.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("CALL deleteFriendById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Friends> getById(Integer id) {
        try {
            Friends friend = jdbcTemplate.queryForObject("CALL getFriendById(?)", BeanPropertyRowMapper.newInstance(Friends.class), id);
            return Optional.of(friend);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAllFriendsByUserId(Integer userId) {
        return jdbcTemplate.query("CALL getAllFriendsByUserId(?)", BeanPropertyRowMapper.newInstance(User.class), userId);
    }
    
}
