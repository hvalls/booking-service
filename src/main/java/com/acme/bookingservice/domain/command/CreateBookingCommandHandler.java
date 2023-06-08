package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.common.Id;
import com.acme.bookingservice.domain.common.BookingConfirmed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBookingCommandHandler {

    private final BookingRepository repository;
    private final EventPublisher publisher;

    @Autowired
    public CreateBookingCommandHandler(
            BookingRepository repository,
            EventPublisher publisher
    ) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public Id handle(CreateBookingCommand cmd) throws BookingAlreadyExistsException {
        if (repository.exists(cmd.bookingId())) {
            throw new BookingAlreadyExistsException();
        }
        // In case of more complex Booking instance creation, a factory could be used
        var booking = new Booking(cmd.bookingId(), cmd.resourceId());
        repository.add(booking);
        publishEvent(booking);
        return cmd.bookingId();
    }

    private void publishEvent(Booking booking) {
        var ev = new BookingConfirmed(booking);
        publisher.publish(ev.eventType(), ev.eventId().value(), ev.asJson());
    }

}
