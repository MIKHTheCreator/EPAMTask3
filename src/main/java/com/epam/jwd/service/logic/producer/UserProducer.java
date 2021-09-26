package com.epam.jwd.service.logic.producer;

import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.api.CallCenterService;
import com.epam.jwd.service.generator.Generator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserProducer implements Runnable{

    private final CallCenterService callCenterService;
    private final int userCount;
    private final Generator generator;
    private final Lock lock = new ReentrantLock();
    private  final Condition condition = lock.newCondition();

    private static final Logger log = LogManager.getLogger(UserProducer.class);
    private static final String THREAD_INTERRUPTED_EXCEPTION_LOG_MESSAGE = "Thread has been interrupted";
    private static final String RECALLER_NAME = "Kirkorov";

    public UserProducer(CallCenterService callCenterService, int userCount) {
        this.callCenterService = callCenterService;
        this.userCount = userCount;
        this.generator = new Generator(new Random());
    }

    @Override
    public void run() {

        for(int i = 0; i < userCount; i++) {
            try {
                User user = new User(generator.generateId(), generator.generateName(),
                        generator.generateAge(), generator.generateGender(),
                        generator.generateVisitAim(), generator.generateRecallChance());
                lock.lock();
                callCenterService.saveUser(user);

                deleteRecaller(user);
                condition.signalAll();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(THREAD_INTERRUPTED_EXCEPTION_LOG_MESSAGE, e);
            } finally {
                lock.unlock();
            }
        }
    }

    private void deleteRecaller(User user)
            throws InterruptedException {
        if(callCenterService.containsUser(user) && user.isRecall()
                && user.getPersonName().equals(RECALLER_NAME)) {
            user.endCall();
            User deletedUser = callCenterService.getUserFromTheQueue();
            callCenterService.addUserToUserCache(deletedUser);
        }
    }
}
