package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.entity.User;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UserRepositoryImpl implements UserRepository<String, User> {

    private static UserRepositoryImpl instance;
    private final BlockingQueue<User> userStorage = new ArrayBlockingQueue<>(USER_QUEUE_CAPACITY);

    private static final int USER_QUEUE_CAPACITY = 10;

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
            return instance;
        }

        return instance;
    }

    @Override
    public User removeUser() throws InterruptedException {
        return userStorage.take();
    }

    @Override
    public void save(User user) throws InterruptedException {
        userStorage.put(user);
    }

    @Override
    public boolean containsUser(User user) {
        return userStorage.contains(user);
    }

    @Override
    public boolean removeUser(User user) {
        return userStorage.remove(user);
    }
}
