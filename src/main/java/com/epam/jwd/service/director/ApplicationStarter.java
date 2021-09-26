package com.epam.jwd.service.director;

import com.epam.jwd.service.api.CallCenterService;
import com.epam.jwd.service.impl.CallCenterServiceImpl;
import com.epam.jwd.service.logic.consumer.OperatorLoader;
import com.epam.jwd.service.logic.producer.UserProducer;
import com.epam.jwd.service.logic.producer.UserRecallProducer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationStarter {

    private final CallCenterService callCenterService;
    private final UserProducer userGenerator;
    private final UserRecallProducer userRecallProducer;
    private final ExecutorService service;

    private static final int NUM_OF_CREATED_USERS = 30;

    public ApplicationStarter() {
        this.callCenterService = new CallCenterServiceImpl();
        this.userGenerator = new UserProducer(callCenterService, NUM_OF_CREATED_USERS);
        this.userRecallProducer = new UserRecallProducer(callCenterService);
        this.service = Executors.newCachedThreadPool();
    }

    public void start(int numOfOperators) {
        service.execute(userGenerator);
        service.execute(userRecallProducer);
        createOperators(numOfOperators).forEach(service::execute);

    }

    private List<OperatorLoader> createOperators(int numOfOperators) {
        List<OperatorLoader> list = new ArrayList<>();

        for (int i = 1; i <= numOfOperators; i++) {
            list.add(new OperatorLoader(callCenterService, "ROBO-" + i));
        }

        return list;
    }
}
