package com.jay.emaildigest.collect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public enum JSON {

    MAPPER;

    private ObjectMapper mapper;
    private static final Logger LOG = LoggerFactory.getLogger(JSON.class);

    JSON() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());
    }

    public <T> T toObject(byte[] payload, Class<T> type) {
        try {
            return mapper.readValue(payload, type);
        } catch (IOException e) {
            LOG.error(String.format("Unable to transform byte[] to given type %s", type.getName()), e);
            throw new IllegalArgumentException(String.format("Unable to transform byte[] to given type %s", type.getName()));
        }
    }
}
