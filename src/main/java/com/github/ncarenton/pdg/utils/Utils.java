package com.github.ncarenton.pdg.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Utils {

    public static ObjectMapper commonObjectMapper() {
        return new ObjectMapper()
            .registerModules(new JavaTimeModule());
    }

}
