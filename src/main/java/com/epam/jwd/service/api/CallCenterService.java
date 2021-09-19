package com.epam.jwd.service.api;

import com.epam.jwd.repository.entity.Operator;
import com.epam.jwd.repository.entity.User;

import java.util.List;

public interface CallCenterService {

    void putUserToQueue(User user);
    List<Operator> getOperatorsList();
    Operator hireOperator(Operator operator);
    void dismissOperator(String name);
}
