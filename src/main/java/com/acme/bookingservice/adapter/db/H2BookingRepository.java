package com.acme.bookingservice.adapter.db;

import com.acme.bookingservice.domain.command.Booking;
import com.acme.bookingservice.domain.command.BookingRepository;
import com.acme.bookingservice.domain.common.Id;
import org.springframework.beans.factory.annotation.Autowired;

public class H2BookingRepository implements BookingRepository {

    private final JPABookingRepository repository;

    @Autowired
    public H2BookingRepository(JPABookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Booking booking) {
        var jpaBooking = new JPABooking(booking.id().value(), booking.resourceId().value());
        repository.save(jpaBooking);
    }

    @Override
    public boolean exists(Id bookingId) {
        return repository.existsById(bookingId.value());
    }

}
