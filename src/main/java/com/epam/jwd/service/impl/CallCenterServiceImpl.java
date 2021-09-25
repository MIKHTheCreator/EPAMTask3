package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.entity.User;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.service.api.CallCenterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CallCenterServiceImpl implements CallCenterService {

    private final UserRepository<String, User> userQueue = UserRepositoryImpl.getInstance();

    private static final Logger log = LogManager.getLogger(CallCenterServiceImpl.class);

    private static final String USER_SERVED_LOG_MESSAGE = "User has been saved";
    private static final String REMOVE_USER_LOG_MESSAGE = "User has been removed";


    @Override
    public void saveUser(User user) throws InterruptedException {

            userQueue.save(user);
            log.debug(USER_SERVED_LOG_MESSAGE);
    }

    @Override
    public User getUserFromTheQueue()
            throws InterruptedException {

        log.debug(REMOVE_USER_LOG_MESSAGE);
        return userQueue.removeUser();
    }
}
