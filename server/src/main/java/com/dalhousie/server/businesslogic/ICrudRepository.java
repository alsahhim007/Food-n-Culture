package com.dalhousie.server.businesslogic;

import java.util.List;
import java.util.Optional;

public interface ICrudRepository<T, ID> {
    List<T> findAll();
    int save(T object);
    int update(T object);
    int delete(T object);
    int deleteById(ID id);
    boolean exists(ID id);
    Optional<T> getById(ID id);
}
