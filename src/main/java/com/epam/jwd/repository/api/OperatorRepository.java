package com.epam.jwd.repository.api;

import com.epam.jwd.repository.entity.Person;

public interface OperatorRepository<V, T extends Person<V>> {

    T findById(V id);
    T findByName(String name);
    boolean haveAvailableOperators();
    boolean removeOperator(String name);
    void save(T operator);
}
