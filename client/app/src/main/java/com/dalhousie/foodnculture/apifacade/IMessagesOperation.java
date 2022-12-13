package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Messages;

import java.util.List;

public interface IMessagesOperation extends ICrudOperation<Messages, Integer> {
    public List<Messages> getAllMessagesBetweenUsers(Integer user1, Integer user2);
}
