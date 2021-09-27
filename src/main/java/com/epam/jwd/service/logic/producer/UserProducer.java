package com.epam.jwd.service.logic.producer;

import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.api.CallCenterService;
import com.epam.jwd.service.generator.Generator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class UserProducer implements Runnable {

    private final CallCenterService callCenterService;
    private final int userCount;
    private final Generator generator;

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

        for (int i = 0; i < userCount; i++) {
            try {
                User user = new User(generator.generateId(), generator.generateName(),
                        generator.generateAge(), generator.generateGender(),
                        generator.generateVisitAim(), generator.generateRecallChance());
                callCenterService.saveUser(user);

                processUsers(user);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(THREAD_INTERRUPTED_EXCEPTION_LOG_MESSAGE, e);
            }
        }
    }

    private void processUsers(User user)
            throws InterruptedException {
        if (callCenterService.containsUserInQueue(user) && user.isRecall()
                && user.getPersonName().equals(RECALLER_NAME)) {
            user.endCall();
            callCenterService.addUserToUserCache(user);
            callCenterService.removeUserFromQueue(user);
        }
    }
}
