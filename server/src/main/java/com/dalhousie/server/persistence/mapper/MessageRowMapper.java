package com.dalhousie.server.persistence.mapper;

import com.dalhousie.server.model.Messages;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Messages> {
    
    @Override
    @Nullable
    public Messages mapRow(ResultSet rs, int rowNum) throws SQLException {
        Messages messages = new Messages();
        messages.setId(rs.getInt("id"));
        messages.setUserId(rs.getInt("user_id"));
        messages.setTargetUserId(rs.getInt("target_user_id"));
        messages.setContent(rs.getString("content"));
        messages.setRead(rs.getBoolean("is_read"));
        messages.setCreatedAt(rs.getString("created_at"));
        messages.setUpdatedAt(rs.getString("updated_at"));
        return messages;
    }

}
