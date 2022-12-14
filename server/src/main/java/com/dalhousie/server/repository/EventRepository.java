package com.dalhousie.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalhousie.server.contract.IEventRepository;
import com.dalhousie.server.model.Event;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.EventRowMapper;

@Component
public class EventRepository implements IEventRepository {

    @Autowired
    private IConnection dbConnection;

    @Override
    public List<Event> findAll() {
        return dbConnection.executeProcedure("CALL getAllEvents()", new EventRowMapper());
    }

    @Override
    public int save(Event event) {
        return dbConnection.executeProcedure("CALL createEvent(?, ?, ?, ?, ?, ?, ?, ?, ?)", event.getId(), event.getTitle(), event.getDescription(), event.getEventType().toString(), event.getStatus(),
        event.getStartDatetime(), event.getEndDatetime(), event.getVenue(), event.getMaxCapacity());
    }

    @Override
    public int update(Event event) {
        return dbConnection.executeProcedure(
                "CALL updateEvent(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                event.getTitle(), event.getDescription(), event.getEventType().toString(), event.getStatus(),
                event.getStartDatetime(), event.getEndDatetime(), event.getVenue(), event.getMaxCapacity(), event.getId());
    }

    @Override
    public int delete(Event event) {
        return dbConnection.executeProcedure("CALL deleteEventById(?)", event.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteEventById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Event> getById(Integer id) {
        try {
            Event event = dbConnection.executeProcedureForObject("CALL getEventById(?)", new EventRowMapper(), id);
            return Optional.of(event);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

}
