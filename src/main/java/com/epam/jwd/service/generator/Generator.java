package com.epam.jwd.service.generator;

import com.epam.jwd.repository.entity.Gender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.UUID;

public class Generator {

    private final Random random;

    private static final Logger log = LogManager.getLogger(Generator.class);
    private static final String[] names;
    private static final String[] aims;
    public static final String GENERATE_ID_LOG_MESSAGE = "Generating id...";
    public static final String GENERATE_NAME_LOG_MESSAGE = "Generating name...";
    public static final String GENERATE_AGE_LOG_MESSAGE = "Generating age...";
    public static final String GENERATE_GENDER_LOG_MESSAGE = "Generating gender...";
    public static final String GENERATE_VISIT_AIM_LOG_MESSAGE = "Generating visit aim...";
    private static final int MAX_PERSON_AGE = 100;

    static {
        names = new String[]{"Superman", "Mario", "Mister X", "Batman", "Spider-Man", "Iron Man", "Kirkorov"};
        aims = new String[]{"'Selling problems'", "'Payment method problems'", "'Bad connection'", "'Resource not found'"};
    }

    public Generator(Random random) {
        this.random = random;
    }

    public String generateId() {
        log.debug(GENERATE_ID_LOG_MESSAGE);
        return UUID.randomUUID().toString();
    }

    public String generateName() {
        log.debug(GENERATE_NAME_LOG_MESSAGE);
        return names[random.nextInt(names.length)];
    }

    public int generateAge() {
        log.debug(GENERATE_AGE_LOG_MESSAGE);
        return random.nextInt(MAX_PERSON_AGE);
    }

    public Gender generateGender() {
        log.debug(GENERATE_GENDER_LOG_MESSAGE);
        return Gender.values()[random.nextInt(Gender.values().length)];
    }

    public String generateVisitAim() {
        log.debug(GENERATE_VISIT_AIM_LOG_MESSAGE);
        return aims[random.nextInt(aims.length)];
    }

    public boolean generateRecallChance() {
        return random.nextBoolean();
    }
}
