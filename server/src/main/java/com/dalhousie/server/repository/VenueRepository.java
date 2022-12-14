package com.dalhousie.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalhousie.server.contract.IVenueRepository;
import com.dalhousie.server.model.Venues;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.VenueRowMapper;

@Component
public class VenueRepository implements IVenueRepository {

    @Autowired
    private IConnection dbConnection;

    @Override
    public List<Venues> findAll() {
        return dbConnection.executeProcedure("SELECT * FROM venues", new VenueRowMapper());
    }

    @Override
    public int save(Venues venue) {
        return dbConnection.executeProcedure(
                "CALL createVenue(?, ?, ?, ?, ?, ?)",
                venue.getId(), venue.getUserId(), venue.getName(), venue.getStatus(),
                venue.getAddressLine1(), venue.getAddressLine2());
    }

    @Override
    public int update(Venues venue) {
        return dbConnection.executeProcedure(
                "CALL udpateVenue(?, ?, ?, ?, ?, ?)",
                venue.getUserId(), venue.getName(), venue.getStatus(), venue.getAddressLine1(),
                venue.getAddressLine2(), venue.getId());
    }

    @Override
    public int delete(Venues venue) {
        return dbConnection.executeProcedure("CALL deleteVenue(?)", venue.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteVenue(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Venues> getById(Integer id) {
        try {
            Venues venue = dbConnection.executeProcedureForObject("CALL getVenueById(?)", new VenueRowMapper(), id);
            return Optional.of(venue);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Venues> getVenuesByUserId(Integer userId) {
        return dbConnection.executeProcedure("SELECT * FROM venues WHERE user_id=?", new VenueRowMapper(), userId);
    }

}
