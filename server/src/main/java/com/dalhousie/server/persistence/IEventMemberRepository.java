package com.dalhousie.server.persistence;

import java.util.List;

import com.dalhousie.server.model.EventMember;

public interface IEventMemberRepository extends ICrudRepository <EventMember, Integer> {
    public List<EventMember> getMembersByEventId(Integer eventId);
    public List<EventMember> getMembersByUserId(Integer userId);
}
