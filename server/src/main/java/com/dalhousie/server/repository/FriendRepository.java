package com.dalhousie.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalhousie.server.contract.IFriendRepository;
import com.dalhousie.server.model.Friends;
import com.dalhousie.server.model.User;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.FriendRowMapper;
import com.dalhousie.server.persistence.mapper.UserRowMapper;

@Component
public class FriendRepository implements IFriendRepository {

    @Autowired
    private IConnection dbConnection;

    @Override
    public List<Friends> findAll() {
        return dbConnection.executeProcedure("CALL getAllFriends", new FriendRowMapper());
    }

    @Override
    public int save(Friends friends) {
        return dbConnection.executeProcedure(
                "CALL createFriend(?, ?, ?)",
                friends.getId(), friends.getUserId(), friends.getTargetUserId());
    }

    @Override
    public int update(Friends friends) {
        return dbConnection.executeProcedure(
                "CALL updateFriend(?, ?, ?)",
                friends.getId(), friends.getUserId(), friends.getTargetUserId());
    }

    @Override
    public int delete(Friends friends) {
        return dbConnection.executeProcedure("CALL deleteFriendById(?)", friends.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteFriendById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Friends> getById(Integer id) {
        try {
            Friends friend = dbConnection.executeProcedureForObject("CALL getFriendById(?)", new FriendRowMapper(), id);
            return Optional.of(friend);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAllFriendsByUserId(Integer userId) {
        return dbConnection.executeProcedure("CALL getAllFriendsByUserId(?)", new UserRowMapper(), userId);
    }
    
}
