package com.epam.jwd.controller;

import com.epam.jwd.service.api.CallCenterService;
import com.epam.jwd.service.logic.producer.UserProducer;
import com.epam.jwd.service.impl.CallCenterServiceImpl;
import com.epam.jwd.service.logic.consumer.OperatorLoader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    public static void main(String[] args) {
        CallCenterService callCenterService = new CallCenterServiceImpl();

        UserProducer userGenerator = new UserProducer(callCenterService, 100);

        OperatorLoader loader = new OperatorLoader(callCenterService, "ORBO-1");
        OperatorLoader loader1 = new OperatorLoader(callCenterService, "ORBO-2");
        OperatorLoader loader2 = new OperatorLoader(callCenterService, "ORBO-3");


        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(userGenerator);

        service.execute(loader);
        service.execute(loader1);
        service.execute(loader2);

        service.shutdown();
    }
}
