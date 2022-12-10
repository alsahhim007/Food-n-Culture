package com.dalhousie.foodnculture.apifacade;

import java.util.List;
import java.util.Optional;

public interface ICrudOperation<T, ID> {
    List<T> findAll();
    int save(T object) throws Exception;
    int update(T object);
    int delete(T object);
    int deleteById(ID id);
    boolean exists(ID id);
    Optional<T> getById(ID id);
}
