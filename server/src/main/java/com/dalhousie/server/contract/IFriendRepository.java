package com.dalhousie.server.contract;

import com.dalhousie.server.model.Friends;
import com.dalhousie.server.model.User;

import java.util.List;

public interface IFriendRepository extends ICrudRepository <Friends, Integer> {
    public List<User> getAllFriendsByUserId(Integer userId);
}
