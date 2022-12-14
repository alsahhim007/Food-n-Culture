package com.dalhousie.server.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.Event;

@Component
public class EventRepository implements IEventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Event> findAll() {
        return jdbcTemplate.query("CALL getAllEvents()", BeanPropertyRowMapper.newInstance(Event.class));
    }

    @Override
    public int save(Event event) {
        return jdbcTemplate.update("CALL createEvent(?, ?, ?, ?, ?, ?, ?, ?, ?)", event.getId(), event.getTitle(), event.getDescription(), event.getEventType().toString(), event.getStatus(),
        event.getStartDatetime(), event.getEndDatetime(), event.getVenue(), event.getMaxCapacity());
    }

    @Override
    public int update(Event event) {
        return jdbcTemplate.update(
                "CALL updateEvent(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                event.getTitle(), event.getDescription(), event.getEventType().toString(), event.getStatus(),
                event.getStartDatetime(), event.getEndDatetime(), event.getVenue(), event.getMaxCapacity(), event.getId());
    }

    @Override
    public int delete(Event event) {
        return jdbcTemplate.update("CALL deleteEventById(?)", event.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("CALL deleteEventById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Event> getById(Integer id) {
        try {
            Event event = jdbcTemplate.queryForObject("CALL getEventById(?)", BeanPropertyRowMapper.newInstance(Event.class), id);
            return Optional.of(event);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

}
