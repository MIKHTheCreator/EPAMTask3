package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.UserCache;
import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.entity.User;
import com.epam.jwd.repository.impl.UserCacheImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.service.api.CallCenterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class CallCenterServiceImpl implements CallCenterService {

    private final UserRepository<String, User> userQueue = UserRepositoryImpl.getInstance();
    private final UserCache<String, User> userCache = UserCacheImpl.getInstance();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private static final Logger log = LogManager.getLogger(CallCenterServiceImpl.class);

    private static final String USER_SERVED_LOG_MESSAGE = "User has been saved";
    private static final String REMOVE_USER_LOG_MESSAGE = "User has been removed";


    @Override
    public void saveUser(User user) throws InterruptedException {

            userQueue.save(user);
            log.debug(USER_SERVED_LOG_MESSAGE);
    }

    @Override
    public User getUserFromTheQueue()
            throws InterruptedException {

        log.debug(REMOVE_USER_LOG_MESSAGE);
        return userQueue.removeUser();
    }

    @Override
    public void addUserToUserCache(User user) {
        lock.lock();

        if(user.isRecall()) {
            userCache.addToUserCache(user);
            condition.signalAll();
        }

        lock.unlock();
    }

    @Override
    public User takeUserFromUserCache() throws InterruptedException {
        lock.lock();

        if(userCache.isEmpty()) {
            condition.await();
        }

        User user = userCache.removeFromUserCache();
        user.setRecall(false);
        lock.unlock();

        return user;
    }
}
