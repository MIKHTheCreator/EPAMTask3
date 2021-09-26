package com.epam.jwd.service.logic.producer;

import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.api.CallCenterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserRecallProducer implements Runnable{

    private final CallCenterService callCenterService;

    private static final Logger log = LogManager.getLogger(UserRecallProducer.class);
    private static final String INTERRUPTED_EXCEPTION_LOG_MESSAGE = "Thread ha been interrupted";

    public UserRecallProducer(CallCenterService callCenterService) {
        this.callCenterService = callCenterService;
    }

    @Override
    public void run() {

        while (true) {
            try {
                User user = callCenterService.takeUserFromUserCache();
                if(!callCenterService.containsUser(user)){
                    user.recall();
                    callCenterService.saveUser(user);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(INTERRUPTED_EXCEPTION_LOG_MESSAGE, e);
            }
        }
    }
}
