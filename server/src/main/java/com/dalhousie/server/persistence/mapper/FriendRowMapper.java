package com.dalhousie.server.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.lang.Nullable;
import org.springframework.jdbc.core.RowMapper;

import com.dalhousie.server.model.Friends;

public class FriendRowMapper implements RowMapper<Friends> {
    
    @Override
    @Nullable
    public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
        Friends friends = new Friends();
        friends.setId(rs.getInt("id"));
        friends.setUserId(rs.getInt("user_id"));
        friends.setTargetUserId(rs.getInt("target_user_id"));
        friends.setCreatedAt(rs.getString("created_at"));
        friends.setUpdatedAt(rs.getString("updated_at"));
        return friends;
    }

}
