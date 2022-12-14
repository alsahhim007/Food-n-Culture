package com.dalhousie.foodnculture.apifacade.contract;

import com.dalhousie.foodnculture.models.Messages;

import java.util.List;

public interface IMessagesOperation extends ICrudOperation<Messages, Integer> {
    List<Messages> getAllMessagesBetweenUsers(Integer user1, Integer user2);
}
