package com.dalhousie.server.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.lang.Nullable;
import org.springframework.jdbc.core.RowMapper;

import com.dalhousie.server.enums.EventType;
import com.dalhousie.server.model.Event;

public class EventRowMapper implements RowMapper<Event> {
    
    @Override
    @Nullable
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();
        event.setId(rs.getInt("id"));
        event.setTitle(rs.getString("title"));
        event.setDescription(rs.getString("description"));
        event.setStartDatetime(rs.getString("start_datetime"));
        event.setEndDatetime(rs.getString("end_datetime"));
        event.setStatus(rs.getString("status"));
        event.setVenue(rs.getString("venue"));
        event.setMaxCapacity(rs.getInt("max_capacity"));
        event.setEventType(EventType.valueOf(rs.getString("event_type")));
        event.setCreatedAt(rs.getString("created_at"));
        event.setUpdatedAt(rs.getString("updated_at"));
        return event;
    }

}
