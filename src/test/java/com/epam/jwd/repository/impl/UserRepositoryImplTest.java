package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.entity.Gender;
import com.epam.jwd.repository.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryImplTest {

    private UserRepository<String, User> userRepository;
    private User testUser;
    private User testUser1;

    @BeforeAll
    void beforeAll(){
        this.userRepository = UserRepositoryImpl.getInstance();

    }

    @BeforeEach
    void setUp() throws InterruptedException {
        this.testUser = new User("sgdgs", "Misha", 20, Gender.MALE, "Test aim", false);
        this.testUser1 = new User("sgdgs1", "Maveric", 20, Gender.COMMON, "Test aim", true);
        userRepository.save(testUser);
        userRepository.save(testUser1);
    }

    @Test
    void getInstance() {
        UserRepository<String, User> newObject = UserRepositoryImpl.getInstance();
        assertSame(userRepository, newObject);
    }

    @Test
    void removeUser() throws InterruptedException {
        assertEquals(testUser1, userRepository.removeUser());
    }

    @Test
    void save() throws InterruptedException {
        userRepository.removeUser();
        userRepository.save(testUser);
        assertTrue(userRepository.containsUser(testUser));
    }

    @Test
    void containsExistingUser() {
        assertTrue(userRepository.containsUser(testUser));
    }

    @Test
    void containsNotExistingUser() {
        assertFalse(userRepository.containsUser(new User("sgdgs211", "Maveric", 20, Gender.COMMON, "Test aim", true)));
    }
}