package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.command.handler.CreateBookingCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandGateway {

    @Autowired
    CreateBookingCommandHandler createBookingCommandHandler;

    public void createBooking(String resourceId) {
        createBookingCommandHandler.handle(new CreateBookingCommand(resourceId));
    }

}
