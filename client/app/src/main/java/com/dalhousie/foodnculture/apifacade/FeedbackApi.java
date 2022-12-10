package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Feedback;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FeedbackApi implements IFeedbackOperation {
    private final IRequest request;
    private final String baseUrl = "http://localhost:8080/api/feedbacks"; // TODO? find better place for me

    public FeedbackApi(IRequest<Feedback> request) {
        this.request = request;
    }

    @Override
    public List<Feedback> findAll() {
        Feedback[] feedbacks = new Feedback[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            feedbacks = Mapper.mapFromJson(buffer.toString(), Feedback[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(feedbacks);
    }

    @Override
    public int save(Feedback object) {
        try {
            StringBuffer buffer = this.request.doPost(baseUrl + "/", Mapper.mapToJson(object));
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Feedback object) {
        return 0;
    }

    @Override
    public int delete(Feedback object) {
        return 0;
    }

    @Override
    public int deleteById(Integer integer) {
        return 0;
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Optional<Feedback> getById(Integer integer) {
        return null;
    }

    @Override
    public List<Feedback> getFeedbackByEventId(Integer eventId) {
        return null;
    }
}
