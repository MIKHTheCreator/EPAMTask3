package com.epam.jwd.service.logic.producer;

import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.api.CallCenterService;
import com.epam.jwd.service.generator.WorkingTimeGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class UserRecallProducer implements Runnable{

    private final CallCenterService callCenterService;
    private final Lock lock = new ReentrantLock();
    private final WorkingTimeGenerator generator;

    private static final Logger log = LogManager.getLogger(UserRecallProducer.class);
    private static final String INTERRUPTED_EXCEPTION_LOG_MESSAGE = "Thread ha been interrupted";

    public UserRecallProducer(CallCenterService callCenterService) {
        this.callCenterService = callCenterService;
        this.generator = new WorkingTimeGenerator();
    }

    @Override
    public void run() {

        while (true) {
            try {
                lock.lock();
                User user = callCenterService.takeUserFromUserCache();
                if(!callCenterService.containsUser(user)){
                    user.recall();
                    callCenterService.saveUser(user);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(INTERRUPTED_EXCEPTION_LOG_MESSAGE, e);
            } finally {
                lock.unlock();
            }
        }
    }
}
