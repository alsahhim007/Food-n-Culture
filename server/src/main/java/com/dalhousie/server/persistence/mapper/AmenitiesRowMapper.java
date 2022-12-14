package com.dalhousie.server.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.lang.Nullable;
import org.springframework.jdbc.core.RowMapper;

import com.dalhousie.server.model.Amenities;

public class AmenitiesRowMapper implements RowMapper<Amenities> {
    
    @Override
    @Nullable
    public Amenities mapRow(ResultSet rs, int rowNum) throws SQLException {
        Amenities amenities = new Amenities();
        amenities.setId(rs.getInt("id"));
        amenities.setName(rs.getString("name"));
        amenities.setCategory(rs.getString("category"));
        amenities.setCreatedAt(rs.getString("created_at"));
        amenities.setUpdatedAt(rs.getString("updated_at"));
        return amenities;
    }
    
}
