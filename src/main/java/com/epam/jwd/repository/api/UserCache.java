package com.epam.jwd.repository.api;

import com.epam.jwd.repository.entity.Person;

public interface UserCache <V, T extends Person<V>> {

    boolean addToUserCache(T t);
    boolean removeFromUserCache(T t);
}
