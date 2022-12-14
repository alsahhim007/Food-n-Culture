package com.dalhousie.server.contract;

import java.util.List;

import com.dalhousie.server.model.Feedback;

public interface IFeedbackRepository extends ICrudRepository <Feedback, Integer>  {
    public List<Feedback> getFeedbackByEventId(Integer eventId);
}
