package com.payments.backend;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;

public enum Status {
    CREATED, SENT, ACCEPTED, REJECTED;
    @JsonCreator
    public static Status setValue(String key) {
        return Arrays.stream(Status.values())
            .filter(exampleEnum -> exampleEnum.toString().equals(key.toUpperCase()))
            .findAny()
            .orElse(null);
    }
}
