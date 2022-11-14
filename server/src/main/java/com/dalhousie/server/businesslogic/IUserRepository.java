package com.dalhousie.server.businesslogic;

import com.dalhousie.server.model.User;

public interface IUserRepository extends ICrudRepository <User, Integer> {
    public User getByName(String name);
    public User getByEmail(String email);
}
