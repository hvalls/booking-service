package com.acme.bookingservice.domain.common;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public record Id(String value) {
    public static Id random()  {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String randomIdValue = new String(array, StandardCharsets.UTF_8);
        return new Id(randomIdValue);
    }

}
