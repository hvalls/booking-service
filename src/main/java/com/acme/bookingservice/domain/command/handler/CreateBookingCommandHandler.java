package com.acme.bookingservice.domain.command.handler;

import com.acme.bookingservice.domain.command.BookingFactory;
import com.acme.bookingservice.domain.command.BookingRepository;
import com.acme.bookingservice.domain.command.CreateBookingCommand;
import com.acme.bookingservice.domain.command.event.BookingConfirmed;
import com.acme.bookingservice.domain.command.event.EventDispatcher;
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
        var resourceId = cmd.resourceId();
        var booking = factory.create(resourceId);
        repository.add(booking);
        var event = new BookingConfirmed(booking.id.value(), resourceId, BookingConfirmed.EVENT_VERSION);
        dispatcher.dispatch(event);
    }

}
