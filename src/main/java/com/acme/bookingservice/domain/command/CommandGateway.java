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

    public void createBooking(String resourceId) {
        var cmd = new CreateBookingCommand(new Id(resourceId));
        createBookingCommandHandler.handle(cmd);
    }

}
