package com.dalhousie.server.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.EventMember;

@Component
public class EventMemberRepository implements IEventMemberRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EventMember> findAll() {
        return jdbcTemplate.query("CALL getAllMembers", BeanPropertyRowMapper.newInstance(EventMember.class));
    }

    @Override
    public List<EventMember> getMembersByEventId(Integer eventId) {
        return jdbcTemplate.query("CALL getMembersByEventId(?)", BeanPropertyRowMapper.newInstance(EventMember.class), eventId);
    }

    @Override
    public int save(EventMember member) {
        return jdbcTemplate.update(
                "CALL createMember(?, ?, ?, ?)",
                member.getId(), member.getEventId(), member.getUserId(), member.getStatus());
    }

    @Override
    public int update(EventMember member) {
        return jdbcTemplate.update(
                "CALL updateMember(?, ?, ?, ?)",
                member.getEventId(), member.getUserId(), member.getStatus(), member.getId());
    }

    @Override
    public int delete(EventMember member) {
        return jdbcTemplate.update("CALL deleteMember(?)", member.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("CALL deleteMember(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<EventMember> getById(Integer id) {
        try {
            EventMember member = jdbcTemplate.queryForObject("CALL getMemberById(?)", BeanPropertyRowMapper.newInstance(EventMember.class), id);
            return Optional.of(member);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<EventMember> getMembersByUserId(Integer userId) {
        return jdbcTemplate.query("CALL getAllMembersByuserId(?)", BeanPropertyRowMapper.newInstance(EventMember.class), userId);
    }

}
