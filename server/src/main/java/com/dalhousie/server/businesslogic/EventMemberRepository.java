package com.dalhousie.server.businesslogic;

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
        return jdbcTemplate.query("SELECT * FROM members", BeanPropertyRowMapper.newInstance(EventMember.class));
    }

    @Override
    public List<EventMember> getMembersByEventId(Integer eventId) {
        return jdbcTemplate.query("SELECT * FROM members WHERE event_id=?", BeanPropertyRowMapper.newInstance(EventMember.class), eventId);
    }

    @Override
    public int save(EventMember member) {
        return jdbcTemplate.update(
                "INSERT INTO members(id, event_id, user_id, status, updated_at, created_at) VALUES(?, ?, ?, ?, ?, ?)",
                member.getId(), member.getEventId(), member.getUserId(), member.getStatus(), member.getUpdatedAt(),
                member.getCreatedAt());
    }

    @Override
    public int update(EventMember member) {
        return jdbcTemplate.update(
                "UPDATE members SET event_id=?, user_id=?, status=?, updated_at=?, created_at=? WHERE id=?",
                member.getEventId(), member.getUserId(), member.getStatus(), member.getUpdatedAt(),
                member.getCreatedAt(), member.getId());
    }

    @Override
    public int delete(EventMember member) {
        return jdbcTemplate.update("DELETE FROM members WHERE id=?", member.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM members WHERE id=?", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<EventMember> getById(Integer id) {
        try {
            EventMember member = jdbcTemplate.queryForObject("SELECT * from members WHERE id=?", BeanPropertyRowMapper.newInstance(EventMember.class), id);
            return Optional.of(member);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

}
