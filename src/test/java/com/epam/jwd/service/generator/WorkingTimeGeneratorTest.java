package com.epam.jwd.service.generator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WorkingTimeGeneratorTest {

    private WorkingTimeGenerator generator;

    @BeforeAll
    void beforeAll() {
        this.generator = new WorkingTimeGenerator();
    }

    @Test
    void isGeneratedTimeUnderMaxAvailable() {
        assertTrue(generator.generateTime() < 10000);
    }

    @Test
    void isGeneratedTimeBelowMinAvailable() {
        assertTrue(generator.generateTime() > 3000);
    }
}