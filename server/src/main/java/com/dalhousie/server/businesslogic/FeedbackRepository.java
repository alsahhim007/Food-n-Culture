package com.dalhousie.server.businesslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.Feedback;

@Component
public class FeedbackRepository implements IFeedbackRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Feedback> findAll() {
        return jdbcTemplate.query("SELECT * FROM feedback", BeanPropertyRowMapper.newInstance(Feedback.class));
    }

    @Override
    public int save(Feedback feedback) {
        return jdbcTemplate.update(
                "INSERT INTO feedback(id, member_id, comment, stars, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?)",
                feedback.getId(), feedback.getMemberId(), feedback.getComment(), feedback.getStars(), feedback.getUpdatedAt(),
                feedback.getCreatedAt());
    }

    @Override
    public int update(Feedback feedback) {
        return jdbcTemplate.update(
                "UPDATE feedback SET member_id=?, comment=?, stars=?, created_at=?, updated_at=? WHERE id=?",
                feedback.getMemberId(), feedback.getComment(), feedback.getStars(), feedback.getCreatedAt(),
                feedback.getUpdatedAt(), feedback.getId());
    }

    @Override
    public int delete(Feedback feedback) {
        return jdbcTemplate.update("DELETE FROM feedback WHERE id=?", feedback.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM feedback WHERE id=?", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Feedback> getById(Integer id) {
        try {
            Feedback feedback = jdbcTemplate.queryForObject("SELECT * from feedback WHERE id=?", BeanPropertyRowMapper.newInstance(Feedback.class), id);
            return Optional.of(feedback);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Feedback> getFeedbackByEventId(Integer eventId) {
        return jdbcTemplate.query("SELECT * FROM feedback f, members m WHERE m.id = f.member_id and m.event_id=?", BeanPropertyRowMapper.newInstance(Feedback.class), eventId);
    }

}
