package com.epam.jwd.service.logic;

import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.api.CallCenterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OperatorLoader implements Runnable{

    private final CallCenterService callCenterService;
    private final String operatorName;

    private static final Logger log = LogManager.getLogger(OperatorLoader.class);

    private static final String SERVING_USER_INFO_MESSAGE = "User %s is serving by Operator=%s,\n his/her objective is %s,\n his/her age is %d,\n his/her gender is %s\n";
    private static final String SERVING_USER_LOG_MESSAGE = "User has been gotten from queue by operator";
    private static final String INTERRUPTED_EXCEPTION_LOG_MESSAGE = "Thread has been interrupted";


    public OperatorLoader(CallCenterService callCenterService, String operatorName) {
        this.callCenterService = callCenterService;
        this.operatorName = operatorName;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(10000);
                User user = callCenterService.getUserFromTheQueue();
                log.debug(SERVING_USER_LOG_MESSAGE);
                System.out.printf(SERVING_USER_INFO_MESSAGE, operatorName, user.getPersonName(), user.getVisitAim(),
                        user.getAge(), user.getGender().toString());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(INTERRUPTED_EXCEPTION_LOG_MESSAGE, e);
            }
        }
    }
}
