package com.dalhousie.foodnculture.apifacade.contract;

import com.dalhousie.foodnculture.models.User;

import java.util.Optional;

public interface IUserOperation extends ICrudOperation<User, Integer> {
    Optional<User> getByUserName(String name);

    Optional<User> getByEmail(String email);
}
