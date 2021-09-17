package com.epam.jwd.repository.api;

import com.epam.jwd.repository.entity.Person;

import java.util.List;

public interface UserRepository<V, T extends Person<V>> {

    T findById(V id);
    T findByName(String name);
    boolean removeUser(String name);
    void save(T user);
    List<T> findByGender(String gender);
}
