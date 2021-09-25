package com.epam.jwd.service.generator;

import java.util.Random;

public class WorkingTimeGenerator {
    private final Random random;

    public WorkingTimeGenerator(Random random) {
        this.random = random;
    }

    public int generateTime() {
        return random.nextInt(10000);
    }
}
