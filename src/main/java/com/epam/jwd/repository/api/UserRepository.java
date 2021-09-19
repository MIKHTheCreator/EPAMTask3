package com.epam.jwd.repository.api;

import com.epam.jwd.repository.entity.Person;
import com.epam.jwd.repository.entity.User;

public interface UserRepository<V, T extends Person<V>> {

    User removeUser(String name);
    void save(T user);
}
