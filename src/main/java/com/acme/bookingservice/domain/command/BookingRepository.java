package com.acme.bookingservice.domain.command;

import com.acme.bookingservice.domain.common.Id;

public interface BookingRepository {

    void add(Booking booking);

    boolean exists(Id bookingId);

}
