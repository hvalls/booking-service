package com.acme.bookingservice.domain.command.handler;

import com.acme.bookingservice.domain.command.Booking;
import com.acme.bookingservice.domain.command.BookingAlreadyExistsException;
import com.acme.bookingservice.domain.command.BookingRepository;
import com.acme.bookingservice.domain.command.CreateBookingCommand;
import com.acme.bookingservice.domain.command.event.EventDispatcher;
import com.acme.bookingservice.domain.common.Id;
import com.acme.bookingservice.domain.common.event.BookingConfirmed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBookingCommandHandler {

    private final BookingRepository repository;
    private final EventDispatcher dispatcher;

    @Autowired
    public CreateBookingCommandHandler(
            BookingRepository repository,
            EventDispatcher dispatcher
    ) {
        this.repository = repository;
        this.dispatcher = dispatcher;
    }

    public Id handle(CreateBookingCommand cmd) throws BookingAlreadyExistsException {
        // In case of more complex Booking instance creation, a factory could be used
        if (repository.exists(cmd.bookingId())) {
            throw new BookingAlreadyExistsException();
        }
        var booking = new Booking(cmd.bookingId(), cmd.resourceId());
        repository.add(booking);
        dispatcher.dispatch(
                new BookingConfirmed(booking)
        );
        return cmd.bookingId();
    }

}
