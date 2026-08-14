package com.touristagency.touristagency.interfaces.serviceInterface;

import java.util.List;
import java.util.Optional;

public interface CrudServiceInterface<T>{
    List<T> getAll();

    boolean existsById(Integer id);

    Optional<T> findById(int id);

    T create(T t);

    Optional<T> update(T t, int id);

    void deleteById(int id);
}
