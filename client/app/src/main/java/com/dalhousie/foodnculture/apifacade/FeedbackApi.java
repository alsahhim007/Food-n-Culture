package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Feedback;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FeedbackApi implements IFeedbackOperation {
    private final IRequest request;
    private String baseUrl = "/api/feedbacks";

    public FeedbackApi(IRequest<Feedback> request) {
        this.request = request;
        this.baseUrl = ConfigProvider.getApiUrl() + baseUrl;
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

        try {
            StringBuffer buffer = this.request.doPut(baseUrl + "/" + object.getId(), Mapper.mapToJson(object));
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Feedback object) {
        return deleteById(object.getId());
    }

    @Override
    public int deleteById(Integer id) {
        try {
            StringBuffer buffer = this.request.doDelete(baseUrl + "/" + id);
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean exists(Integer id) {
        return getById(id).isPresent();
    }

    @Override
    public Optional<Feedback> getById(Integer id) {
        Feedback feedback = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + id);
            feedback = Mapper.mapFromJson(buffer.toString(), Feedback.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(feedback);
    }

    @Override
    public List<Feedback> getFeedbackByEventId(Integer eventId) {
        Feedback[] feedbacks = new Feedback[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/events/" + eventId);
            feedbacks = Mapper.mapFromJson(buffer.toString(), Feedback[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(feedbacks);
    }
}
