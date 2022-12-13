package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Friends;
import com.dalhousie.foodnculture.models.User;

import java.util.List;

public interface IFriendOperation extends ICrudOperation<Friends, Integer> {
    public List<User> getAllFriendsByUserId(Integer userId);
}
