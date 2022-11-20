package com.dalhousie.server.businesslogic;

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
                "INSERT INTO venues(id, user_id, name, status, address_line1, address_line2, updated_at, created_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                venue.getId(), venue.getUserId(), venue.getName(), venue.getAddressLine1(), venue.getAddressLine2(),
                venue.getUpdatedAt(), venue.getCreatedAt());
    }

    @Override
    public int update(Venues venue) {
        return jdbcTemplate.update(
                "UPDATE venues SET user_id=?, name=?, status=?, address_line1=?, address_line2=?, updated_at=?, created_at=? WHERE id=?",
                venue.getName(), venue.getAddressLine1(), venue.getAddressLine2(),
                venue.getUpdatedAt(), venue.getCreatedAt(), venue.getUserId());
    }

    @Override
    public int delete(Venues venue) {
        return jdbcTemplate.update("DELETE FROM venues WHERE id=?", venue.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM venues WHERE id=?", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Venues> getById(Integer id) {
        try {
            Venues venue = jdbcTemplate.queryForObject("SELECT * from venues WHERE id=?", BeanPropertyRowMapper.newInstance(Venues.class), id);
            return Optional.of(venue);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Venues> getVenuesByUserId(Integer userId) {
        return jdbcTemplate.query("SELECT * FROM venues WHERE user_id=?", BeanPropertyRowMapper.newInstance(Venues.class), userId);
    }

}
