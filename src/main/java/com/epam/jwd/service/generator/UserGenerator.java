package com.epam.jwd.service.generator;

import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.api.CallCenterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class UserGenerator implements Runnable{

    private final CallCenterService callCenterService;
    private final int userCount;
    private final Random random;

    private static final Logger log = LogManager.getLogger(UserGenerator.class);
    private static final String GENERATOR_MULTI_USER_LOG_MESSAGE = "User has been generated";
    private static final String THREAD_INTERRUPTED_EXCEPTION_LOG_MESSAGE = "Thread has been interrupted";

    public UserGenerator(CallCenterService callCenterService, int userCount) {
        this.callCenterService = callCenterService;
        this.userCount = userCount;
        this.random = new Random();
    }

    @Override
    public void run() {

        for(int i = 0; i < userCount; i++) {
            try {
                callCenterService.saveUser(new User(Generator.generateId(), Generator.generateName(random),
                        Generator.generateAge(random), Generator.generateGender(random),
                        Generator.generateVisitAim(random)));
                log.debug(GENERATOR_MULTI_USER_LOG_MESSAGE);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(THREAD_INTERRUPTED_EXCEPTION_LOG_MESSAGE, e);
            }
        }
    }

}
