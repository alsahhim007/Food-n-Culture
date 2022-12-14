package com.dalhousie.server.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.lang.Nullable;
import org.springframework.jdbc.core.RowMapper;

import com.dalhousie.server.model.EventMember;

public class EventMemberRowMapper implements RowMapper<EventMember> {
    
    @Override
    @Nullable
    public EventMember mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventMember eventMem = new EventMember();
        eventMem.setId(rs.getInt("id"));
        eventMem.setEventId(rs.getInt("event_id"));
        eventMem.setUserId(rs.getInt("user_id"));
        eventMem.setStatus(rs.getString("status"));
        eventMem.setCreatedAt(rs.getString("created_at"));
        eventMem.setUpdatedAt(rs.getString("updated_at"));
        return eventMem;
    }

}
