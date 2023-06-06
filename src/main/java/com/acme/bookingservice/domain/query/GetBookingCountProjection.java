package com.acme.bookingservice.domain.query;

import com.acme.bookingservice.domain.common.event.BookingConfirmed;
import com.acme.bookingservice.domain.common.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetBookingCountProjection {

    private final EventBus eventBus;

    @Autowired
    public GetBookingCountProjection(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.subscribe(BookingConfirmed.TOPIC, this::onBookingConfirmed);
    }

    private Void onBookingConfirmed(String jsonPayload) {
        System.out.println("Booking confirmed! " + jsonPayload);
        return null;
    }

}
