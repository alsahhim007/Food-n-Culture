package com.dalhousie.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalhousie.server.contract.IAmenitiesRepository;
import com.dalhousie.server.model.Amenities;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.AmenitiesRowMapper;

@Component
public class AmenitiesRepository implements IAmenitiesRepository {

    @Autowired
    private IConnection dbConnection;
    
    @Override
    public List<Amenities> findAll() {
        return dbConnection.executeProcedure("CALL getAllAmenities()", new AmenitiesRowMapper());
    }

    @Override
    public int save(Amenities amenities) {
        return dbConnection.executeProcedure(
                "CALL createAmenities(?, ?, ?)",
                amenities.getId(), amenities.getName(), amenities.getCategory());
    }

    @Override
    public int update(Amenities amenities) {
        return dbConnection.executeProcedure(
                "CALL updateAmenities(?, ?, ?)",
                amenities.getId(), amenities.getName(), amenities.getCategory());
    }

    @Override
    public int delete(Amenities amenities) {
        return dbConnection.executeProcedure("CALL deleteAmenitiesById(?)", amenities.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteAmenitiesById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Amenities> getById(Integer id) {
        try {
            Amenities amenities = dbConnection.executeProcedureForObject("CALL getAmenitiesById(?)", new AmenitiesRowMapper(), id);
            return Optional.of(amenities);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Amenities> getAllAmenitiesByVenueId(Integer venueId) {
        return dbConnection.executeProcedure("CALL getAllAmenitiesByVenueId(?)", new AmenitiesRowMapper(), venueId);
    }
    
}
