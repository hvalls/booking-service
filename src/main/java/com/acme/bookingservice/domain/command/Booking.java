package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.common.Id;

public class Booking {

    public final Id id;
    private final Id resourceId;

    public Booking(Id id, Id resourceId) {
        this.id = id;
        this.resourceId = resourceId;
    }

    public Id getResourceId() {
        return resourceId;
    }
}
