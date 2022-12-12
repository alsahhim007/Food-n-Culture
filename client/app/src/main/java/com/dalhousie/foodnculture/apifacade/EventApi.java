package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Event;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EventApi implements IEventOperation {
    private final IRequest request;
    private String baseUrl = "/api/events";

    public EventApi(IRequest<Event> request) {
        this.request = request;
        this.baseUrl = ConfigProvider.getApiUrl() + baseUrl;
    }

    @Override
    public List<Event> findAll() {
        Event[] events = new Event[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            events = Mapper.mapFromJson(buffer.toString(), Event[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(events);
    }

    @Override
    public int save(Event object) {
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
    public int update(Event object) {
        return 0;
    }

    @Override
    public int delete(Event object) {
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
    public Optional<Event> getById(Integer integer) {
        return null;
    }
}
