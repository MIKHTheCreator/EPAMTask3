package com.epam.jwd.service.logic.consumer;

import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.api.CallCenterService;
import com.epam.jwd.service.generator.WorkingTimeGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OperatorLoader implements Runnable{

    private final CallCenterService callCenterService;
    private final String operatorName;
    private final WorkingTimeGenerator generator;

    private static final Logger log = LogManager.getLogger(OperatorLoader.class);

    private static final String SERVING_USER_INFO_MESSAGE = """
            User %s is served by OperatorName = %s(Call time: %d seconds),
             his/her objective was %s,
             his/her age was %d,
             his/her gender was %s

            """;
    private static final String SERVING_USER_LOG_MESSAGE = "User has been gotten from queue by operator";
    private static final String INTERRUPTED_EXCEPTION_LOG_MESSAGE = "Thread has been interrupted";
    private static final int MILLISECONDS_TO_SECONDS_DELIMITER = 1000;

    public OperatorLoader(CallCenterService callCenterService, String operatorName) {
        this.callCenterService = callCenterService;
        this.operatorName = operatorName;
        this.generator = new WorkingTimeGenerator();
    }

    @Override
    public void run() {
        while(true) {
            try {
                int workingTime = generator.generateTime();

                Thread.sleep(workingTime);

                log.debug(SERVING_USER_LOG_MESSAGE);
                User user = callCenterService.getUserFromTheQueue();
                System.out.printf(SERVING_USER_INFO_MESSAGE, user.getPersonName(), operatorName, getSeconds(workingTime) , user.getVisitAim(),
                        user.getAge(), user.getGender().toString());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(INTERRUPTED_EXCEPTION_LOG_MESSAGE, e);
            }
        }
    }

    private int getSeconds(int timeInMillis) {
        return  timeInMillis / MILLISECONDS_TO_SECONDS_DELIMITER;
    }
}
