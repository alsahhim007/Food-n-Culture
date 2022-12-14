package com.dalhousie.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalhousie.server.contract.ICommunityRepository;
import com.dalhousie.server.model.Community;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.CommunityRowMapper;

@Component
public class CommunityRepository implements ICommunityRepository {

    @Autowired
    private IConnection dbConnection;
    
    @Override
    public List<Community> findAll() {
        return dbConnection.executeProcedure("CALL getAllCommunity()", new CommunityRowMapper());
    }

    @Override
    public int save(Community community) {
        return dbConnection.executeProcedure("CALL createCommunity(?, ?, ?)", community.getId(), community.getTitle(), community.getDescription());
    }

    @Override
    public int update(Community community) {
        return dbConnection.executeProcedure("CALL updateCommunity(?, ?, ?)", community.getId(), community.getTitle(), community.getDescription());
    }

    @Override
    public int delete(Community community) {
        return dbConnection.executeProcedure("CALL deleteCommunityById(?)", community.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteCommunityById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Community> getById(Integer id) {
        try {
            Community community = dbConnection.executeProcedureForObject("CALL getCommunityById(?)", new CommunityRowMapper(), id);
            return Optional.of(community);
        }catch(Exception e) {
            return Optional.empty();
        }
    }
}
