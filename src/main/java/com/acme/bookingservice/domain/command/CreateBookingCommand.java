package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.common.Id;

public record CreateBookingCommand(Id bookingId, Id resourceId) {

    public static CreateBookingCommand create(String resourceId) {
        return new CreateBookingCommand(Id.random(), new Id(resourceId));
    }

}
