package com.dalhousie.server.persistence;

import java.util.Optional;

import com.dalhousie.server.model.User;

public interface IUserRepository extends ICrudRepository <User, Integer> {
    public Optional<User> getByUserName(String name);
    public Optional<User> getByEmail(String email);
}
