package com.epam.jwd.repository.api;

import com.epam.jwd.repository.entity.Person;
import com.epam.jwd.repository.entity.User;

public interface UserCache <V, T extends Person<V>> {

    boolean addToUserCache(T t);
    User removeFromUserCache();
    boolean isEmpty();
}
