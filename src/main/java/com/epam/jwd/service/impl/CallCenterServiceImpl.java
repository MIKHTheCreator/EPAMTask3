package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.entity.User;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.service.api.CallCenterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class CallCenterServiceImpl implements CallCenterService {

    private final AtomicInteger numOfServingUsers = new AtomicInteger(0);
    private final UserRepository<String, User> userQueue = UserRepositoryImpl.getInstance();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private static final Logger log = LogManager.getLogger(CallCenterServiceImpl.class);

    private static final int MAX_NUM_OF_PARALLEL_SERVED_USERS = 5;
    private static final int MIN_NUM_OF_SERVED_USERS = 0;
    private static final String USER_SERVED_LOG_MESSAGE = "User has been saved";
    private static final String AWAITING_LOG_MESSAGE = "Method awaits";
    private static final String REMOVE_USER_LOG_MESSAGE = "User has been removed";


    @Override
    public void saveUser(User user) throws InterruptedException {
//        lock.lock();

        if(numOfServingUsers.get() < MAX_NUM_OF_PARALLEL_SERVED_USERS) {
//            condition.signalAll();
            userQueue.save(user);
            numOfServingUsers.incrementAndGet();
            log.debug(USER_SERVED_LOG_MESSAGE);
        } else {
            log.debug(AWAITING_LOG_MESSAGE);
//            condition.await();
        }

//        lock.unlock();
    }

    @Override
    public User getUserFromTheQueue()
            throws InterruptedException {
//        lock.lock();

        if(numOfServingUsers.get() > MIN_NUM_OF_SERVED_USERS) {
//            condition.signalAll();
            log.debug(REMOVE_USER_LOG_MESSAGE);
            return userQueue.removeUser();
        }

//        condition.await();

//        lock.unlock();
        return null;
    }
}
