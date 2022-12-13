package com.dalhousie.server.persistence;

import java.util.List;

import com.dalhousie.server.model.User;
import com.dalhousie.server.model.Friends;

public interface IFriendRepository extends ICrudRepository <Friends, Integer> {
    public List<User> getAllFriendsByUserId(Integer userId);
}
