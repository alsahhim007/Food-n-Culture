package com.dalhousie.server.contract;

import com.dalhousie.server.model.EventMember;

import java.util.List;

public interface IEventMemberRepository extends ICrudRepository <EventMember, Integer> {
    public List<EventMember> getMembersByEventId(Integer eventId);
    public List<EventMember> getMembersByUserId(Integer userId);
}
