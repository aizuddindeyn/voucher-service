/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author aizuddindeyn
 */
public class MapperResolver {

    private static ObjectMapper instance;

    private MapperResolver() {
        // Default ctx
    }

    public static ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapper();
            instance.registerModule(new JavaTimeModule());
        }

        return instance;
    }
}
