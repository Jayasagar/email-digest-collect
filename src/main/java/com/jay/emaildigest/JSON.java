package com.jay.emaildigest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public enum JSON {

    MAPPER;

    private ObjectMapper mapper;
    private static final Logger LOG = LoggerFactory.getLogger(JSON.class);

    JSON() {
        mapper = new ObjectMapper();
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
