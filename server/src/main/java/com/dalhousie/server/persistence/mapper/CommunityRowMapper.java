package com.dalhousie.server.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.lang.Nullable;
import org.springframework.jdbc.core.RowMapper;

import com.dalhousie.server.model.Community;

public class CommunityRowMapper implements RowMapper<Community> {
    
    @Override
    @Nullable
    public Community mapRow(ResultSet rs, int rowNum) throws SQLException {
        Community community = new Community();
        community.setId(rs.getInt("id"));
        community.setTitle(rs.getString("title"));
        community.setDescription(rs.getString("description"));
        community.setCreatedAt(rs.getString("createdAt"));
        community.setUpdatedAt(rs.getString("updatedAt"));
        return community;
    }

}
