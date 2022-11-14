package com.dalhousie.server.businesslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dalhousie.server.database.IConnection;
import com.dalhousie.server.model.User;

public class UserRepository implements IUserRepository {

    @Autowired
    private IConnection connection; 

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User object) {
        return null;
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public void delete(User object) {
    }

    @Override
    public void deleteById(Integer id) {
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<User> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public User getByName(String name) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    

}
