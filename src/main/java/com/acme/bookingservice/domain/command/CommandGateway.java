package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.command.handler.CreateBookingCommandHandler;
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
        var bookingId = Id.random();
        var resId = new Id(resourceId);
        var cmd = new CreateBookingCommand(bookingId, resId);
        return createBookingCommandHandler.handle(cmd);
    }

}
