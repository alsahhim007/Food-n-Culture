package com.dalhousie.server.contract;

import com.dalhousie.server.model.Feedback;

import java.util.List;

public interface IFeedbackRepository extends ICrudRepository <Feedback, Integer>  {
    public List<Feedback> getFeedbackByEventId(Integer eventId);
}
