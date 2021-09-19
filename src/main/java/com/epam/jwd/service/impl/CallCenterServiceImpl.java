package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.OperatorRepository;
import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.entity.Operator;
import com.epam.jwd.repository.entity.User;
import com.epam.jwd.repository.impl.OperatorRepositoryImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.service.api.CallCenterService;

import java.util.List;

public class CallCenterServiceImpl implements CallCenterService {

    private final UserRepository<String, User> userRepository = UserRepositoryImpl.getInstance();
    private final OperatorRepository<String, Operator> operatorRepository = OperatorRepositoryImpl.getInstance();

    @Override
    public void putUserToQueue(User user) {
        userRepository.save(user);
    }

    @Override
    public List<Operator> getOperatorsList() {
        return operatorRepository.findAll();
    }

    @Override
    public Operator hireOperator(Operator operator) {
        operatorRepository.save(operator);
        return operator;
    }

    @Override
    public void dismissOperator(String name) {
        operatorRepository.removeOperator(name);
    }
}
