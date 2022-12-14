package com.dalhousie.server.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.Community;

@Component
public class CommunityRepository implements ICommunityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Community> findAll() {
        return jdbcTemplate.query("CALL getAllCommunity()", BeanPropertyRowMapper.newInstance(Community.class));
    }

    @Override
    public int save(Community community) {
        return jdbcTemplate.update("CALL createCommunity(?, ?, ?)", community.getId(), community.getTitle(), community.getDescription());
    }

    @Override
    public int update(Community community) {
        return jdbcTemplate.update("CALL updateCommunity(?, ?, ?)", community.getId(), community.getTitle(), community.getDescription());
    }

    @Override
    public int delete(Community community) {
        return jdbcTemplate.update("CALL deleteCommunityById(?)", community.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("CALL deleteCommunityById(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Community> getById(Integer id) {
        try {
            Community community = jdbcTemplate.queryForObject("CALL getCommunityById(?)", BeanPropertyRowMapper.newInstance(Community.class), id);
            return Optional.of(community);
        }catch(Exception e) {
            return Optional.empty();
        }
    }
}
