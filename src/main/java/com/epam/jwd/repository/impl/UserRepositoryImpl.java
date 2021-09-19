package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.entity.User;

import java.util.ArrayDeque;
import java.util.Queue;

public class UserRepositoryImpl implements UserRepository<String, User> {

    private static UserRepositoryImpl instance;
    private final Queue<User> userStorage = new ArrayDeque<>();

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if(instance == null) {
            instance = new UserRepositoryImpl();
            return instance;
        }

        return instance;
    }

    @Override
    public User removeUser(String name) {
        return userStorage.poll();
    }

    @Override
    public void save(User user) {
        userStorage.offer(user);
    }
}
