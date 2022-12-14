package com.dalhousie.server.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.lang.Nullable;
import org.springframework.jdbc.core.RowMapper;

import com.dalhousie.server.model.Feedback;

public class FeedbackRowMapper implements RowMapper<Feedback> {
    
    @Override
    @Nullable
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getInt("id"));
        feedback.setMemberId(rs.getInt("member_id"));
        feedback.setComment(rs.getString("comment"));
        feedback.setStars(rs.getString("stars"));
        feedback.setCreatedAt(rs.getString("created_at"));
        feedback.setUpdatedAt(rs.getString("updated_at"));
        return feedback;
    }

}
