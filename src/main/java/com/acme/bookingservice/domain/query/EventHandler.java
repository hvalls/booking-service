package com.acme.bookingservice.domain.query;

import com.acme.bookingservice.domain.common.event.BookingConfirmed;
import com.acme.bookingservice.domain.common.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    private final BookingCountProjection bookingCountProjection;

    @Autowired
    public EventHandler(
            BookingCountProjection bookingCountProjection,
            EventBus eventBus
    ) {
        this.bookingCountProjection = bookingCountProjection;
        eventBus.subscribe(BookingConfirmed.EVENT_TYPE, this::onBookingConfirmed);
    }

    private Void onBookingConfirmed(String eventId, String jsonPayload) {
        var ev = BookingConfirmed.fromJsonPayload(jsonPayload);
        return this.bookingCountProjection.project(ev);
    }

}
