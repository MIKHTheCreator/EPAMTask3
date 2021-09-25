package com.epam.jwd.repository.api;

import com.epam.jwd.repository.entity.Person;

public interface UserRepository<V, T extends Person<V>> {

    T removeUser() throws InterruptedException;
    void save(T user) throws InterruptedException;
}
