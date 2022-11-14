package com.dalhousie.server.businesslogic;

import java.util.List;
import java.util.Optional;

public interface ICrudRepository<T, ID> {
    List<T> findAll();
    T save(T object);
    T update(T object);
    void delete(T object);
    void deleteById(ID id);
    boolean exists(ID id);
    Optional<T> getById(ID id);
}
