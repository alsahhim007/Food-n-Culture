package com.dalhousie.server.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.Venues;

@Component
public class VenueRepository implements IVenueRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Venues> findAll() {
        return jdbcTemplate.query("SELECT * FROM venues", BeanPropertyRowMapper.newInstance(Venues.class));
    }

    @Override
    public int save(Venues venue) {
        return jdbcTemplate.update(
                "CALL createVenue(?, ?, ?, ?, ?, ?)",
                venue.getId(), venue.getUserId(), venue.getName(), venue.getStatus(),
                venue.getAddressLine1(), venue.getAddressLine2());
    }

    @Override
    public int update(Venues venue) {
        return jdbcTemplate.update(
                "CALL udpateVenue(?, ?, ?, ?, ?, ?)",
                venue.getUserId(), venue.getName(), venue.getStatus(), venue.getAddressLine1(),
                venue.getAddressLine2(), venue.getId());
    }

    @Override
    public int delete(Venues venue) {
        return jdbcTemplate.update("CALL deleteVenue(?)", venue.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("CALL deleteVenue(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Venues> getById(Integer id) {
        try {
            Venues venue = jdbcTemplate.queryForObject("CALL getVenueById(?)", BeanPropertyRowMapper.newInstance(Venues.class), id);
            return Optional.of(venue);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Venues> getVenuesByUserId(Integer userId) {
        return jdbcTemplate.query("SELECT * FROM venues WHERE user_id=?", BeanPropertyRowMapper.newInstance(Venues.class), userId);
    }

}
