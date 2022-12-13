package com.dalhousie.foodnculture.apifacade.api;

import com.dalhousie.foodnculture.apifacade.contract.IEventMemberOperation;
import com.dalhousie.foodnculture.apifacade.contract.IRequest;
import com.dalhousie.foodnculture.models.EventMember;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EventMemberApi implements IEventMemberOperation {
    private final IRequest request;
    private String baseUrl = "/api/members";

    public EventMemberApi(IRequest<EventMember> request) {
        this.request = request;
        this.baseUrl = ConfigProvider.getApiUrl() + baseUrl;
    }

    @Override
    public List<EventMember> findAll() {
        EventMember[] eventMembers = new EventMember[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            eventMembers = Mapper.mapFromJson(buffer.toString(), EventMember[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(eventMembers);
    }

    @Override
    public int save(EventMember object) {
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
    public int update(EventMember object) {

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
    public int delete(EventMember object) {
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
    public Optional<EventMember> getById(Integer id) {
        EventMember eventMember = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + id);
            eventMember = Mapper.mapFromJson(buffer.toString(), EventMember.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(eventMember);
    }

    @Override
    public List<EventMember> getMembersByEventId(Integer eventId) {
        EventMember[] members = new EventMember[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/events/" + eventId);
            members = Mapper.mapFromJson(buffer.toString(), EventMember[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(members);
    }

}
