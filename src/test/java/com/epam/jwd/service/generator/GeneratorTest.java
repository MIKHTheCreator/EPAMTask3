package com.epam.jwd.service.generator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GeneratorTest {

    private Generator generator;

    @BeforeAll
    void beforeAll() {
        this.generator = new Generator(new Random());
    }

    @Test
    void generateId() {
        assertNotEquals(generator.generateId(), generator.generateId());
    }
}