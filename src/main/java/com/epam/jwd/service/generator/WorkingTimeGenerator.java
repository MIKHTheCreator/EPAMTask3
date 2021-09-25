package com.epam.jwd.service.generator;

import java.util.Random;

public class WorkingTimeGenerator {
    private final Random random;

    public WorkingTimeGenerator() {
        this.random = new Random();
    }

    public int generateTime() {
        return random.nextInt(10000);
    }
}
