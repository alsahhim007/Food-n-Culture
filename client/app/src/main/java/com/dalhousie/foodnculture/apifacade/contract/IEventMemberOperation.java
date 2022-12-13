package com.dalhousie.foodnculture.apifacade.contract;

import com.dalhousie.foodnculture.models.EventMember;

import java.util.List;

public interface IEventMemberOperation extends ICrudOperation <EventMember, Integer> {
    public List<EventMember> getMembersByEventId(Integer eventId);
    public List<EventMember> getMembersByUserId(Integer userId);
}
