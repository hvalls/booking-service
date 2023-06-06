package com.acme.bookingservice.domain.command.handler;

import com.acme.bookingservice.domain.command.BookingFactory;
import com.acme.bookingservice.domain.command.BookingRepository;
import com.acme.bookingservice.domain.command.CreateBookingCommand;
import com.acme.bookingservice.domain.command.event.EventDispatcher;
import com.acme.bookingservice.domain.common.event.BookingConfirmed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBookingCommandHandler {

    private final BookingFactory factory;
    private final BookingRepository repository;
    private final EventDispatcher dispatcher;

    @Autowired
    public CreateBookingCommandHandler(
            BookingFactory factory,
            BookingRepository repository,
            EventDispatcher dispatcher
    ) {
        this.factory = factory;
        this.repository = repository;
        this.dispatcher = dispatcher;
    }

    public void handle(CreateBookingCommand cmd) {
        var booking = factory.create(cmd.resourceId());
        repository.add(booking);
        dispatcher.dispatch(
                new BookingConfirmed(booking)
        );
    }

}
