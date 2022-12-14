package com.dalhousie.foodnculture.apifacade.contract;

import com.dalhousie.foodnculture.models.Feedback;

import java.util.List;

public interface IFeedbackOperation extends ICrudOperation<Feedback, Integer> {
    List<Feedback> getFeedbackByEventId(Integer eventId);
}
