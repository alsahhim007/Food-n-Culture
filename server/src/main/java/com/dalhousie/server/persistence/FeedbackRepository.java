package com.dalhousie.server.persistence;

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
        return jdbcTemplate.query("CALL getAllFeedbacks()", BeanPropertyRowMapper.newInstance(Feedback.class));
    }

    @Override
    public int save(Feedback feedback) {
        return jdbcTemplate.update(
                "CALL createFeedback(?, ?, ?, ?, ?, ?)",
                feedback.getId(), feedback.getMemberId(), feedback.getComment(), feedback.getStars(), feedback.getUpdatedAt(),
                feedback.getCreatedAt());
    }

    @Override
    public int update(Feedback feedback) {
        return jdbcTemplate.update(
                "CALL updateFeedback(?, ?, ?, ?)",
                feedback.getMemberId(), feedback.getComment(), feedback.getStars(), feedback.getId());
    }

    @Override
    public int delete(Feedback feedback) {
        return jdbcTemplate.update("CALL deleteFeedback(?)", feedback.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("CALL deleteFeedback(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Feedback> getById(Integer id) {
        try {
            Feedback feedback = jdbcTemplate.queryForObject("CALL getFeedbackById(?)", BeanPropertyRowMapper.newInstance(Feedback.class), id);
            return Optional.of(feedback);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Feedback> getFeedbackByEventId(Integer eventId) {
        return jdbcTemplate.query("CALL getFeedbackByEventId(?)", BeanPropertyRowMapper.newInstance(Feedback.class), eventId);
    }

}
