package com.epam.jwd.controller;

import com.epam.jwd.service.api.CallCenterService;
import com.epam.jwd.service.generator.UserGenerator;
import com.epam.jwd.service.impl.CallCenterServiceImpl;
import com.epam.jwd.service.logic.OperatorLoader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    public static void main(String[] args) {
        CallCenterService callCenterService = new CallCenterServiceImpl();

        UserGenerator userGenerator = new UserGenerator(callCenterService, 10);

        OperatorLoader loader = new OperatorLoader(callCenterService);
        OperatorLoader loader1 = new OperatorLoader(callCenterService);
        OperatorLoader loader2 = new OperatorLoader(callCenterService);


        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(userGenerator);

        service.execute(loader);
        service.execute(loader1);
        service.execute(loader2);

        service.shutdown();
    }
}
