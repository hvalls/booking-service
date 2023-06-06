package com.acme.bookingservice.domain.query;

import com.acme.bookingservice.domain.common.event.BookingConfirmed;
import com.acme.bookingservice.domain.common.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingCountProjection {

    private final EventBus eventBus;

    // TODO: This state goes to the database
    private int bookingCount = 0;

    @Autowired
    public BookingCountProjection(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.subscribe(BookingConfirmed.TOPIC, this::onBookingConfirmed);
    }

    public int get() {
        return bookingCount;
    }

    private Void onBookingConfirmed(String jsonPayload) {
        // TODO: Check duplicated events
        bookingCount++;
        return null;
    }

}
