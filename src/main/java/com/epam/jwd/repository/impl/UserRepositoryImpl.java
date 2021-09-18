package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository<String, User> {

    private static UserRepositoryImpl instance;
    private final List<User> userList = new ArrayList<>();

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if(instance == null) {
            instance = new UserRepositoryImpl();
            return instance;
        }

        return instance;
    }

    @Override
    public User findById(String id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public User findByName(String name) {
        return userList.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .get();
    }

    @Override
    public boolean removeUser(String name) {
        return userList.remove(userList.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .get());
    }

    @Override
    public void save(User user) {
        userList.add(user);
    }

    @Override
    public List<User> findByGender(String gender) {
        return userList.stream()
                .filter(user -> user.getGender().toString().equals(gender))
                .collect(Collectors.toList());
    }
}
