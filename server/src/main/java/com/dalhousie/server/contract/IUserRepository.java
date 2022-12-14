package com.dalhousie.server.contract;

import com.dalhousie.server.model.User;

import java.util.Optional;

public interface IUserRepository extends ICrudRepository <User, Integer> {
    public Optional<User> getByUserName(String name);
    public Optional<User> getByEmail(String email);
}
