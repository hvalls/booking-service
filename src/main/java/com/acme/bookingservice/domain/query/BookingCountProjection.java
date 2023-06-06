package com.acme.bookingservice.domain.query;

import com.acme.bookingservice.domain.common.event.BookingConfirmed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingCountProjection {

    private final DAO dao;

    @Autowired
    public BookingCountProjection(DAO dao) {
        this.dao = dao;
    }

    public int get() {
        return dao.getBookingCount();
    }

    public Void project(BookingConfirmed bookingConfirmed) {
        dao.incrementBookingCount();
        return null;
    }

}
