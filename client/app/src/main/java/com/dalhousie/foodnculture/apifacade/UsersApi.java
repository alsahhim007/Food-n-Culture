package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.User;
import com.dalhousie.foodnculture.utilities.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UsersApi implements IUserOperation {

    private final IRequest request;
    private final String baseUrl = "http://localhost:8080/api/users"; // TODO? find better place for me

    public UsersApi(IRequest<User> request) {
        this.request = request;
    }

    public int registerUser(User object) {
        return save(object);
    }

    @Override
    public List<User> findAll() {
        User[] userList = new User[]{};
        try {
            StringBuffer buffer =  this.request.doGet(baseUrl + "/");
            userList =  Mapper.mapFromJson(buffer.toString(), User[].class);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(userList);
    }

    @Override
    public int save(User object) {
        int isSuccess = 0;
        try {
            StringBuffer buffer =  this.request.doPost(baseUrl + "/", Mapper.mapToJson(object));
            System.out.println(buffer.toString());
            isSuccess = 0;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    @Override
    public int update(User object) {
        return 0;
    }

    @Override
    public int delete(User object) {
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
    public Optional<User> getById(Integer integer) {
        return null;
    }

    @Override
    public Optional<User> getByUserName(String name) {
        return null;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return null;
    }
}
