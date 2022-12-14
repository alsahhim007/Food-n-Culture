package com.dalhousie.foodnculture.apifacade.api;

import com.dalhousie.foodnculture.apifacade.contract.ICommunityOperation;
import com.dalhousie.foodnculture.apifacade.contract.IRequest;
import com.dalhousie.foodnculture.models.Community;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommunityApi implements ICommunityOperation {
    private final IRequest<Community> request;
    private String baseUrl = "/api/community";

    public CommunityApi(IRequest<Community> request) {
        this.request = request;
        this.baseUrl = ConfigProvider.getApiUrl() + baseUrl;
    }

    @Override
    public List<Community> findAll() {
        Community[] communities = new Community[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            communities = Mapper.mapFromJson(buffer.toString(), Community[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(communities);
    }

    @Override
    public int save(Community object) throws Exception {
        try {
            StringBuffer buffer = this.request.doPost(baseUrl + "/" + object.getId(), Mapper.mapToJson(object));
            if (buffer.length() > 0) {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Community object) {
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
    public int delete(Community object) {
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
    public Optional<Community> getById(Integer id) {
        Community community = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + id);
            community = Mapper.mapFromJson(buffer.toString(), Community.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(community);
    }
}
