package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.UserCache;
import com.epam.jwd.repository.entity.User;

import java.util.LinkedList;
import java.util.List;

public class UserCacheImpl implements UserCache<String, User> {

    private static UserCacheImpl instance = new UserCacheImpl();
    private final List<User> userCache = new LinkedList<>();

    private static final int FIRST_USER_IN_CACHE = 0;

    private UserCacheImpl() {
    }

    public static UserCacheImpl getInstance() {
        if (instance == null) {
            instance = new UserCacheImpl();
        }

        return instance;
    }

    @Override
    public boolean addToUserCache(User user) {
        return userCache.add(user);
    }

    @Override
    public User removeFromUserCache() {
        return userCache.remove(FIRST_USER_IN_CACHE);
    }

    @Override
    public boolean isEmpty() {
        return userCache.isEmpty();
    }
}
