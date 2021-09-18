package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.OperatorRepository;
import com.epam.jwd.repository.entity.Operator;

import java.util.ArrayList;
import java.util.List;

public class OperatorRepositoryImpl implements OperatorRepository<String, Operator> {


    private final List<Operator> operatorList = new ArrayList<>();
    private static OperatorRepositoryImpl instance;

    private OperatorRepositoryImpl() {
    }

    public static OperatorRepositoryImpl getInstance() {
        if(instance == null) {
            instance = new OperatorRepositoryImpl();
        }

        return instance;
    }

    @Override
    public Operator findById(String id) {
        return operatorList.stream()
                .filter(operator -> operator.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public Operator findByName(String name) {
        return operatorList.stream()
                .filter(operator -> operator.getName().equals(name))
                .findFirst()
                .get();
    }

    @Override
    public boolean removeOperator(String name) {
        return operatorList.remove(operatorList.stream()
                .filter(operator -> operator.getName().equals(name))
                .findFirst()
                .orElse(null));
    }

    @Override
    public void save(Operator operator) {
        operatorList.add(operator);
    }
}