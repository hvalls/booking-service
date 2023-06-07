package com.acme.bookingservice.util;

import com.acme.bookingservice.domain.command.Booking;
import com.acme.bookingservice.domain.command.BookingRepository;
import com.acme.bookingservice.domain.common.Id;

import java.util.function.Function;

/**
 * Of course, we can use an external library for construct mock classes.
 * However, in this case, since the behavior is quite simple and there are
 * just a few classes, I decided not to import another dependency and do it "by hand".
 */
public class MockBookingRepository implements BookingRepository {

    private final Function<Booking, Void> onAdd;
    private final Function<Id, Boolean> onExists;

    public boolean addCalled = false;

    public MockBookingRepository(Function<Booking, Void> onAdd, Function<Id, Boolean> onExists) {
        this.onAdd = onAdd;
        this.onExists = onExists;
    }

    @Override
    public void add(Booking booking) {
        this.onAdd.apply(booking);
        addCalled = true;
    }

    @Override
    public boolean exists(Id bookingId) {
        return this.onExists.apply(bookingId);
    }
}
