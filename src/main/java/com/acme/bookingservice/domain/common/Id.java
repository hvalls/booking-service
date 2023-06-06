package com.acme.bookingservice.domain.common;

import java.util.UUID;

public record Id(String value) {
    public static Id random()  {
        return new Id(UUID.randomUUID().toString());
    }

}
