package com.dalhousie.server.businesslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.Amenities;

@Component
public class AmenitiesRepository implements IAmenitiesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Amenities> findAll() {
        return jdbcTemplate.query("SELECT * FROM amenities", BeanPropertyRowMapper.newInstance(Amenities.class));
    }

    @Override
    public int save(Amenities amenities) {
        System.out.println(amenities.toString());
        return jdbcTemplate.update(
                "INSERT INTO amenities(id, name, category, updated_at, created_at) VALUES(?, ?, ?, ?, ?)",
                amenities.getId(), amenities.getName(), amenities.getCategory(), amenities.getUpdatedAt(), amenities.getCreatedAt());
    }

    @Override
    public int update(Amenities amenities) {
        return jdbcTemplate.update(
                "UPDATE amenities SET name=?, category=?, updated_at=?, created_at=? WHERE id=?",
                amenities.getName(), amenities.getCategory(), amenities.getUpdatedAt(), amenities.getCreatedAt(), amenities.getId());
    }

    @Override
    public int delete(Amenities amenities) {
        return jdbcTemplate.update("DELETE FROM amenities WHERE id=?", amenities.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM amenities WHERE id=?", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Amenities> getById(Integer id) {
        try {
            Amenities amenities = jdbcTemplate.queryForObject("SELECT * from amenities WHERE id=?", BeanPropertyRowMapper.newInstance(Amenities.class), id);
            return Optional.of(amenities);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Amenities> getAllAmenitiesByVenueId(Integer venueId) {
        return jdbcTemplate.query("SELECT * FROM amenities a, venues_has_amenities v WHERE a.id = v.amenities_id and v.venue_id=?", BeanPropertyRowMapper.newInstance(Amenities.class), venueId);
    }
    
}
