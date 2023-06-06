package com.acme.bookingservice.domain.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryGateway {

    private final BookingCountProjection bookingCountProjection;

    @Autowired
    public QueryGateway(BookingCountProjection bookingCountProjection) {
        this.bookingCountProjection = bookingCountProjection;
    }

    public int getBookingCount() {
        return bookingCountProjection.get();
    }

}
