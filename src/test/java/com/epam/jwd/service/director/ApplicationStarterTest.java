package com.epam.jwd.service.director;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApplicationStarterTest {

    private ApplicationStarter applicationStarter;

    @BeforeAll
    void beforeAll() {
        this.applicationStarter = new ApplicationStarter();
    }

    @BeforeEach
    void setUp() {
        applicationStarter.start(4);
    }

    @Test
    void startWithFourOperators() {
        assertEquals(8, Thread.activeCount());
    }

}