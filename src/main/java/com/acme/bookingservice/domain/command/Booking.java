package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.common.Id;

public class Booking {

    public final Id id;
    private final String resourceId;

    public Booking(Id id, String resourceId) {
        this.id = id;
        this.resourceId = resourceId;
    }

}
