package com.dalhousie.server.persistence;

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
        return jdbcTemplate.query("CALL getAllAmenities()", BeanPropertyRowMapper.newInstance(Amenities.class));
    }

    @Override
    public int save(Amenities amenities) {
        System.out.println(amenities.toString());
        return jdbcTemplate.update(
                "CALL createAmenities(?, ?, ?)",
                amenities.getId(), amenities.getName(), amenities.getCategory());
    }

    @Override
    public int update(Amenities amenities) {
        return jdbcTemplate.update(
                "CALL updateAmenities(?, ?, ?)",
                amenities.getId(), amenities.getName(), amenities.getCategory());
    }

    @Override
    public int delete(Amenities amenities) {
        return jdbcTemplate.update("CALL deleteAmenitiesById(?)", amenities.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("CALL deleteAmenitiesById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Amenities> getById(Integer id) {
        try {
            Amenities amenities = jdbcTemplate.queryForObject("CALL getAmenitiesById(?)", BeanPropertyRowMapper.newInstance(Amenities.class), id);
            return Optional.of(amenities);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Amenities> getAllAmenitiesByVenueId(Integer venueId) {
        return jdbcTemplate.query("CALL getAllAmenitiesByVenueId(?)", BeanPropertyRowMapper.newInstance(Amenities.class), venueId);
    }
    
}
