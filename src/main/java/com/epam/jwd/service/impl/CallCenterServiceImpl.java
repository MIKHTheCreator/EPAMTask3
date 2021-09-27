package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.UserCache;
import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.entity.User;
import com.epam.jwd.repository.impl.UserCacheImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.service.api.CallCenterService;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class CallCenterServiceImpl implements CallCenterService {

    private final UserRepository<String, User> userQueue = UserRepositoryImpl.getInstance();
    private final UserCache<String, User> userCache = UserCacheImpl.getInstance();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    @Override
    public void saveUser(User user) throws InterruptedException {

        userQueue.save(user);
    }

    @Override
    public User getUserFromTheQueue()
            throws InterruptedException {

        return userQueue.removeUser();
    }

    @Override
    public void addUserToUserCache(User user) {
        lock.lock();

        if (user.isRecall()) {
            userCache.addToUserCache(user);
            condition.signalAll();
        }

        lock.unlock();
    }

    @Override
    public User takeUserFromUserCache() throws InterruptedException {
        lock.lock();

        if (userCache.isEmpty()) {
            condition.await();
        }

        User user = userCache.removeFromUserCache();
        user.setRecall(false);
        lock.unlock();

        return user;
    }

    @Override
    public boolean containsUserInQueue(User user) {
        return userQueue.containsUser(user);
    }

    @Override
    public boolean removeUserFromQueue(User user) {
        return userQueue.removeUser(user);
    }
}
