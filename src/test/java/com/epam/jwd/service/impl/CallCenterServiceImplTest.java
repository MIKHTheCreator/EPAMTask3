package com.epam.jwd.service.impl;

import com.epam.jwd.repository.entity.Gender;
import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.api.CallCenterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CallCenterServiceImplTest {

    private CallCenterService callCenterService;
    private User testUser;
    private User testUser1;

    @BeforeEach
    void setUp() {
        this.callCenterService = new CallCenterServiceImpl();
        this.testUser = new User("sgdgs", "Misha", 20, Gender.MALE, "Test aim", false);
        this.testUser1 = new User("sgdgs1", "Maveric", 20, Gender.COMMON, "Test aim", true);
    }

    @Test
    void saveUser() throws InterruptedException {
        callCenterService.saveUser(testUser);
        assertTrue(callCenterService.containsUser(testUser));
    }

    @Test
    void getUserFromTheQueue() throws InterruptedException {
        callCenterService.saveUser(testUser);
        callCenterService.saveUser(testUser1);
        assertEquals(testUser, callCenterService.getUserFromTheQueue());
    }

    @Test
    void addUserToUserCache() throws InterruptedException {
        callCenterService.addUserToUserCache(testUser1);
        assertEquals(testUser1, callCenterService.takeUserFromUserCache());
    }

    @Test
    void takeUserFromUserCache() throws InterruptedException {
        callCenterService.addUserToUserCache(testUser1);

        assertEquals(testUser1, callCenterService.takeUserFromUserCache());
    }

    @Test
    void containsExistingUser() throws InterruptedException {
        callCenterService.saveUser(testUser);
        assertTrue(callCenterService.containsUser(testUser));
    }

    @Test
    void containsNotExistingUser() throws InterruptedException {
        callCenterService.saveUser(testUser);
        assertFalse(callCenterService.containsUser(testUser1));
    }
}