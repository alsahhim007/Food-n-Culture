package com.dalhousie.foodnculture.apifacade;

import android.os.Build;

import com.dalhousie.foodnculture.exceptions.UserAlreadyExist;
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

    @Override
    public List<User> findAll() {
        User[] userList = new User[]{};
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/");
            userList = Mapper.mapFromJson(buffer.toString(), User[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(userList);
    }

    @Override
    public int save(User object) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (getByUserName(object.getUserName()).isPresent()) {
                throw new UserAlreadyExist("User with username already exists");
            } else if (getByEmail(object.getEmail()).isPresent()) {
                throw new UserAlreadyExist("User with email already exists");
            } else {
                StringBuffer buffer = this.request.doPost(baseUrl + "/", Mapper.mapToJson(object));
                if (buffer.length() > 0) {
                    return 1;
                }
            }
        }
        return 0;
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
        User user = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/username/" + name);
            user = Mapper.mapFromJson(buffer.toString(), User.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Optional.ofNullable(user);
        } else {
            return null;
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        User user = null;
        try {
            StringBuffer buffer = this.request.doGet(baseUrl + "/email/" + email);
            user = Mapper.mapFromJson(buffer.toString(), User.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Optional.ofNullable(user);
        } else {
            return null;
        }
    }
}
