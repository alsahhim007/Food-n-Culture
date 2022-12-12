package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.User;

import java.util.Optional;

public interface IUserOperation extends ICrudOperation <User, Integer> {
    public Optional<User> getByUserName(String name);
    public Optional<User> getByEmail(String email);
}
