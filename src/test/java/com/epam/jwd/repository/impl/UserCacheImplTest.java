package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.UserCache;
import com.epam.jwd.repository.entity.Gender;
import com.epam.jwd.repository.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserCacheImplTest {

    private UserCache<String, User> userCache;
    private User testUser;
    private User testUser1;

    @BeforeAll
    void beforeAll() {
        this.userCache = UserCacheImpl.getInstance();
        this.testUser = new User("sgdgs", "Misha", 20, Gender.MALE, "Test aim", false);
        this.testUser1 = new User("sgdgs1", "Maveric", 20, Gender.COMMON, "Test aim", true);
        userCache.addToUserCache(testUser);
        userCache.addToUserCache(testUser1);
    }
    @Test
    void getInstance() {
        UserCache<String, User> newObject = UserCacheImpl.getInstance();
        assertSame(newObject, userCache);
    }

    @Test
    void addToUserCache() {
        assertTrue(userCache.addToUserCache(testUser));
    }

    @Test
    void removeFromUserCache() {
        assertEquals(userCache.removeFromUserCache(), testUser);
    }

    @Test
    void isEmpty() {
        assertFalse(userCache.isEmpty());
        userCache.removeFromUserCache();
        userCache.removeFromUserCache();
        assertTrue(userCache.isEmpty());
    }
}