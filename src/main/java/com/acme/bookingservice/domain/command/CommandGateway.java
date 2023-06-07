package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.common.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandGateway {

    private final CreateBookingCommandHandler createBookingCommandHandler;

    @Autowired
    public CommandGateway(CreateBookingCommandHandler createBookingCommandHandler) {
        this.createBookingCommandHandler = createBookingCommandHandler;
    }

    public Id createBooking(String resourceId) throws BookingAlreadyExistsException {
        var cmd = CreateBookingCommand.create(resourceId);
        return createBookingCommandHandler.handle(cmd);
    }

}
