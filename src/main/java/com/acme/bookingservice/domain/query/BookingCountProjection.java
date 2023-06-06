package com.acme.bookingservice.domain.query;

import com.acme.bookingservice.domain.common.event.BookingConfirmed;
import com.acme.bookingservice.domain.common.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingCountProjection {

    private final DAO dao;

    @Autowired
    public BookingCountProjection( DAO dao, EventBus eventBus) {
        this.dao = dao;
        eventBus.subscribe(BookingConfirmed.TOPIC, this::onBookingConfirmed);
    }

    public int get() {
        return dao.getBookingCount();
    }

    private Void onBookingConfirmed(String jsonPayload) {
        // TODO: Check duplicated events
        dao.incrementBookingCount();
        return null;
    }

}
