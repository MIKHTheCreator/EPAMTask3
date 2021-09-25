package com.epam.jwd.service.generator;

import java.util.Random;

public class WorkingTimeGenerator {
    private final Random random;

    private static final int MAX_WORKING_TIME = 10000;
    private static final int MIN_WORKING_TIME = 3000;

    public WorkingTimeGenerator() {
        this.random = new Random();
    }

    public int generateTime() {
        int generatedValue = random.nextInt(MAX_WORKING_TIME);

        if(generatedValue < MIN_WORKING_TIME) {
            generatedValue += MIN_WORKING_TIME;
        }

        return generatedValue;
    }
}
