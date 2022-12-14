package com.dalhousie.server.repository;

import com.dalhousie.server.contract.IFeedbackRepository;
import com.dalhousie.server.model.Feedback;
import com.dalhousie.server.persistence.IConnection;
import com.dalhousie.server.persistence.mapper.FeedbackRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FeedbackRepository implements IFeedbackRepository {

    @Autowired
    private IConnection dbConnection;

    @Override
    public List<Feedback> findAll() {
        return dbConnection.executeProcedure("CALL getAllFeedbacks()", new FeedbackRowMapper());
    }

    @Override
    public int save(Feedback feedback) {
        return dbConnection.executeProcedure(
                "CALL createFeedback(?, ?, ?, ?)",
                feedback.getId(), feedback.getMemberId(), feedback.getComment(), feedback.getStars());
    }

    @Override
    public int update(Feedback feedback) {
        return dbConnection.executeProcedure(
                "CALL updateFeedback(?, ?, ?, ?)",
                feedback.getMemberId(), feedback.getComment(), feedback.getStars(), feedback.getId());
    }

    @Override
    public int delete(Feedback feedback) {
        return dbConnection.executeProcedure("CALL deleteFeedback(?)", feedback.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return dbConnection.executeProcedure("CALL deleteFeedback(?)", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Feedback> getById(Integer id) {
        try {
            Feedback feedback = dbConnection.executeProcedureForObject("CALL getFeedbackById(?)", new FeedbackRowMapper(), id);
            return Optional.of(feedback);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Feedback> getFeedbackByEventId(Integer eventId) {
        return dbConnection.executeProcedure("CALL getFeedbackByEventId(?)", new FeedbackRowMapper(), eventId);
    }

}
