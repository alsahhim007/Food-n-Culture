package com.dalhousie.server.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.lang.Nullable;
import org.springframework.jdbc.core.RowMapper;

import com.dalhousie.server.model.Venues;

public class VenueRowMapper implements RowMapper<Venues> {
    
    @Override
    @Nullable
    public Venues mapRow(ResultSet rs, int rowNum) throws SQLException {
        Venues venues = new Venues();
        venues.setId(rs.getInt("id"));
        venues.setUserId(rs.getInt("user_id"));
        venues.setName(rs.getString("name"));
        venues.setStatus(rs.getString("status"));
        venues.setAddressLine1(rs.getString("address_line1"));
        venues.setAddressLine2(rs.getString("address_line2"));
        venues.setCreatedAt(rs.getString("created_at"));
        venues.setUpdatedAt(rs.getString("updated_at"));
        return venues;
    }
    
}
