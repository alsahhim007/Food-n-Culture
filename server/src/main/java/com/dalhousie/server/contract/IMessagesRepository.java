package com.dalhousie.server.contract;

import java.util.List;

import com.dalhousie.server.model.Messages;

public interface IMessagesRepository extends ICrudRepository <Messages, Integer> {
    public List<Messages> getAllMessagesBetweenUsers(Integer user1, Integer user2);
}
