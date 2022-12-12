package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Community;
import com.dalhousie.foodnculture.models.Friends;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FriendsApi implements IFriendOperation {
    private final IRequest request;
    private String baseUrl = "/api/friends";

    public FriendsApi(IRequest<Community> request) {
        this.request = request;
        this.baseUrl = ConfigProvider.getApiUrl() + baseUrl;
    }

    @Override
    public List<Friends> findAll() {
        Friends[] friends = new Friends[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            friends = Mapper.mapFromJson(buffer.toString(), Friends[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(friends);
    }

    @Override
    public int save(Friends object) throws Exception {
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
    public int update(Friends object) {
        try {
            StringBuffer buffer = this.request.doPut(baseUrl + "/", Mapper.mapToJson(object));
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Friends object) {
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
    public Optional<Friends> getById(Integer id) {
        Friends friend = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + id);
            friend = Mapper.mapFromJson(buffer.toString(), Friends.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(friend);
    }
}
