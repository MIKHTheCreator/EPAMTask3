package com.epam.jwd.service.id_generator;

import java.util.UUID;

public class StringIdGenerator {

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
