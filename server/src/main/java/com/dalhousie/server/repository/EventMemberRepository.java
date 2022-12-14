package com.dalhousie.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalhousie.server.contract.IEventMemberRepository;
import com.dalhousie.server.model.EventMember;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.EventMemberRowMapper;

@Component
public class EventMemberRepository implements IEventMemberRepository {

    @Autowired
    private IConnection dbConnection;

    @Override
    public List<EventMember> findAll() {
        return dbConnection.executeProcedure("CALL getAllMembers", new EventMemberRowMapper());
    }

    @Override
    public List<EventMember> getMembersByEventId(Integer eventId) {
        return dbConnection.executeProcedure("CALL getMembersByEventId(?)", new EventMemberRowMapper(), eventId);
    }

    @Override
    public int save(EventMember member) {
        return dbConnection.executeProcedure(
                "CALL createMember(?, ?, ?, ?)",
                member.getId(), member.getEventId(), member.getUserId(), member.getStatus());
    }

    @Override
    public int update(EventMember member) {
        return dbConnection.executeProcedure(
                "CALL updateMember(?, ?, ?, ?)",
                member.getEventId(), member.getUserId(), member.getStatus(), member.getId());
    }

    @Override
    public int delete(EventMember member) {
        return dbConnection.executeProcedure("CALL deleteMember(?)", member.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteMember(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<EventMember> getById(Integer id) {
        try {
            EventMember member = dbConnection.executeProcedureForObject("CALL getMemberById(?)", new EventMemberRowMapper(), id);
            return Optional.of(member);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<EventMember> getMembersByUserId(Integer userId) {
        return dbConnection.executeProcedure("CALL getAllMembersByuserId(?)", new EventMemberRowMapper(), userId);
    }

}
