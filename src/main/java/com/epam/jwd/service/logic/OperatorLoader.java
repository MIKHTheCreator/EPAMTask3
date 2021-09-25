package com.epam.jwd.service.logic;

import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.api.CallCenterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OperatorLoader implements Runnable{

    private final CallCenterService callCenterService;

    private static final Logger log = LogManager.getLogger(OperatorLoader.class);

    private static final String SERVING_USER_INFO_MESSAGE = "User %s is serving,\n his/her objective is %s,\n his/her age is %d,\n his/her gender is %s\n";
    private static final String SERVING_USER_LOG_MESSAGE = "User has been getten from queue by operator";
    private static final String INTERRUPTED_EXCEPTION_LOG_MESSAGE = "Thread has been interrupted";
    private static final String UNAVAILABLE_OBJECT_EXCEPTION_LOG_MESSAGE = "Unavailable object exception has been handled";


    public OperatorLoader(CallCenterService callCenterService) {
        this.callCenterService = callCenterService;
    }

    @Override
    public void run() {
        while(true) {
            try {
                User user = callCenterService.getUserFromTheQueue();
                if(user != null){
                    log.debug(SERVING_USER_LOG_MESSAGE);
                    System.out.printf(SERVING_USER_INFO_MESSAGE, user.getPersonName(), user.getVisitAim(),
                            user.getAge(), user.getGender().toString());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(INTERRUPTED_EXCEPTION_LOG_MESSAGE, e);
            }
        }
    }
}
