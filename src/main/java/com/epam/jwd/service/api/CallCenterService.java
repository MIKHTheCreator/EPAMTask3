package com.epam.jwd.service.api;

import com.epam.jwd.repository.entity.User;


public interface CallCenterService {

    User getUserFromTheQueue() throws InterruptedException;
    void saveUser(User user) throws InterruptedException;
    void addUserToUserCache(User user);
    User takeUserFromUserCache(User user);
}
