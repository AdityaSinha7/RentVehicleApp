package com.example.dao;

import java.util.Collection;
import java.util.Optional;

/**
 *
 */
public interface Dao <T,ID> {

    Optional<T> findById(ID id);

    T save(T entity);

    T update(T entity);

    Collection<T> getAll();
}
