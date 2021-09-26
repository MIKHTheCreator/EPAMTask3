package com.epam.jwd.service.generator;

import com.epam.jwd.repository.entity.Gender;

import java.util.Random;
import java.util.UUID;

public class Generator {

    private final Random random;

    private static final String[] names;
    private static final String[] aims;
    private static final int MAX_PERSON_AGE = 100;

    static {
        names = new String[]{"Superman", "Mario", "Mister X", "Batman", "Spider-Man", "Iron Man", "Kirkorov"};
        aims = new String[]{"'Selling problems'", "'Payment method problems'", "'Bad connection'", "'Resource not found'"};
    }

    public Generator(Random random) {
        this.random = random;
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }

    public String generateName() {
        return names[random.nextInt(names.length)];
    }

    public int generateAge() {
        return random.nextInt(MAX_PERSON_AGE);
    }

    public Gender generateGender() {
        return Gender.values()[random.nextInt(Gender.values().length)];
    }

    public String generateVisitAim() {
        return aims[random.nextInt(aims.length)];
    }

    public boolean generateRecallChance() {
        return random.nextBoolean();
    }
}
