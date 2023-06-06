package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.common.Id;
import org.springframework.stereotype.Component;

@Component
public class BookingFactory {

    public Booking create(Id resourceId) {
        return new Booking(Id.random(), resourceId);
    }

}
