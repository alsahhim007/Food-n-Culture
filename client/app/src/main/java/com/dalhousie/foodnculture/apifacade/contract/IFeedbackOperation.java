package com.dalhousie.foodnculture.apifacade.contract;

import java.util.List;

import com.dalhousie.foodnculture.models.Feedback;

public interface IFeedbackOperation extends ICrudOperation<Feedback, Integer> {
    public List<Feedback> getFeedbackByEventId(Integer eventId);
}
