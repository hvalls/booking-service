package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.command.handler.CreateBookingCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandGateway {

    private final CreateBookingCommandHandler createBookingCommandHandler;

    @Autowired
    public CommandGateway(CreateBookingCommandHandler createBookingCommandHandler) {
        this.createBookingCommandHandler = createBookingCommandHandler;
    }

    public void createBooking(String resourceId) {
        createBookingCommandHandler.handle(new CreateBookingCommand(resourceId));
    }

}
