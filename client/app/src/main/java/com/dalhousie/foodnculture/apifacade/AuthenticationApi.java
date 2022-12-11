package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Authentication;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AuthenticationApi implements IAuthenticationOperation {

    private final IRequest request;
    private final String baseUrl = "http://localhost:8080/api/authentication"; // TODO? find better place for me

    public AuthenticationApi(IRequest<Authentication> request) {
        this.request = request;
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
        StringBuffer buffer = this.request.doPost(baseUrl + "/", Mapper.mapToJson(object));
        if (buffer.length() > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Authentication object) {
        return 0;
    }

    @Override
    public int delete(Authentication object) {
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
    public Optional<Authentication> getById(Integer integer) {
        return null;
    }
}
