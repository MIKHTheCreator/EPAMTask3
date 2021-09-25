package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UserRepositoryImpl implements UserRepository<String, User> {

    private static UserRepositoryImpl instance;
    private final BlockingQueue<User> userStorage = new ArrayBlockingQueue<>(10, true);

    private static final Logger log = LogManager.getLogger(UserRepositoryImpl.class);
    private static final String REMOVE_USER_LOG_MESSAGE = "User has been removed from Queue";
    private static final String SAVE_USER_LOG_MESSAGE = "User has been saved from Queue";

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
    public User removeUser() {
        log.debug(REMOVE_USER_LOG_MESSAGE);
        return userStorage.poll();
    }

    @Override
    public void save(User user) {
        log.debug(SAVE_USER_LOG_MESSAGE);
        userStorage.offer(user);
    }

}
