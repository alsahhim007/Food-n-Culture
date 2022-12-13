package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Authentication;
import com.dalhousie.foodnculture.utilities.ConfigProvider;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AuthenticationApi implements IAuthenticationOperation {

    private final IRequest request;
    private String baseUrl = "/api/authentication";

    public AuthenticationApi(IRequest<Authentication> request) {
        this.request = request;
        this.baseUrl = ConfigProvider.getApiUrl() + baseUrl;
    }

    @Override
    public Authentication getOTPByUserId(Integer userId) {
        Authentication authentication = new Authentication();
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/users/" + userId);
            authentication = Mapper.mapFromJson(buffer.toString(), Authentication.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return authentication;
    }

    @Override
    public List<Authentication> findAll() {
        Authentication[] authentications = new Authentication[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            authentications = Mapper.mapFromJson(buffer.toString(), Authentication[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(authentications);
    }

    @Override
    public int save(Authentication object) throws Exception {
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
    public int update(Authentication object) {
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
    public int delete(Authentication object) {
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
    public Optional<Authentication> getById(Integer id) {
        Authentication authentication = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/" + id);
            authentication = Mapper.mapFromJson(buffer.toString(), Authentication.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(authentication);
    }
}
