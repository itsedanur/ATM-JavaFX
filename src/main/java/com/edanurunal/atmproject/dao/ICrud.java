package com.enesuzun.ibb_ecodation_javafx.dao;

import java.util.List;
import java.util.Optional;

public interface ICrud <T> {
    //create
    Optional<T> create(T entity);
    //LİST
    Optional<List<T>> list();
    //findBy
    Optional<T> findByName(String name);
    Optional<T> findById(int id);

    //Update
    Optional<T> update(int id, T entity);
    //DELETE
    Optional<T> delete(int id);

}
