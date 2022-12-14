package com.dalhousie.server.contract;

import com.dalhousie.server.model.Messages;

import java.util.List;

public interface IMessagesRepository extends ICrudRepository <Messages, Integer> {
    public List<Messages> getAllMessagesBetweenUsers(Integer user1, Integer user2);
}
