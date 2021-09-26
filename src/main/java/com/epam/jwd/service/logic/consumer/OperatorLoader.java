package com.epam.jwd.service.logic.consumer;

import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.api.CallCenterService;
import com.epam.jwd.service.generator.WorkingTimeGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OperatorLoader implements Runnable{

    private final CallCenterService callCenterService;
    private final String operatorName;
    private final WorkingTimeGenerator generator;
    private final Lock lock = new ReentrantLock();

    private static final Logger log = LogManager.getLogger(OperatorLoader.class);

    private static final String SERVING_USER_INFO_MESSAGE = """
            User %s is served by OperatorName = %s(Call time: %d seconds),
             his/her objective was %s,
             his/her age was %d,
             his/her gender was %s
             
            """;
    private static final String INTERRUPTED_EXCEPTION_LOG_MESSAGE = "Thread has been interrupted";
    private static final int MILLISECONDS_TO_SECONDS_DELIMITER = 1000;
    private static final AtomicInteger num = new AtomicInteger(0);

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

                lock.lock();
                User user = callCenterService.getUserFromTheQueue();
                System.out.printf(SERVING_USER_INFO_MESSAGE, user.getPersonName(),
                        operatorName, getSeconds(workingTime) , user.getVisitAim(),
                        user.getAge(), user.getGender().toString());
                System.out.println(num.addAndGet(1));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(INTERRUPTED_EXCEPTION_LOG_MESSAGE, e);
            } finally {
                lock.unlock();
            }
        }
    }

    private int getSeconds(int timeInMillis) {
        return  timeInMillis / MILLISECONDS_TO_SECONDS_DELIMITER;
    }
}
