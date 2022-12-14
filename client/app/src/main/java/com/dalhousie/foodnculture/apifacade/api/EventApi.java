package com.dalhousie.foodnculture.apifacade.api;

import com.dalhousie.foodnculture.apifacade.contract.IEventOperation;
import com.dalhousie.foodnculture.apifacade.contract.IRequest;
import com.dalhousie.foodnculture.models.Event;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EventApi implements IEventOperation {
    private final IRequest<Event> request;
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
    public int delete(Event object) {
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
    public Optional<Event> getById(Integer id) {
        Event event = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + id);
            event = Mapper.mapFromJson(buffer.toString(), Event.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(event);
    }
}
